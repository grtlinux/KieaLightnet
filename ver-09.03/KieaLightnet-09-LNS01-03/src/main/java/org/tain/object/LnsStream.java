package org.tain.object;

import java.sql.Timestamp;

import org.tain.utils.CurrentInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@Slf4j
public class LnsStream implements Cloneable {

	private String data;
	private int length;
	
	private String strLength;     // 4
	private String division;      // 4
	private String divisionType;  // 3 REQ/RES
	private String trid;          // 20 transaction id (16)
	private String content;
	
	public LnsStream(String data) {
		this.data   = data;
		this.length = data.length();
		
		this.strLength    = data.substring(0, 4);
		this.division     = data.substring(4, 8);
		this.divisionType = data.substring(8, 11);
		this.trid         = data.substring(11, 31);
		this.content      = data.substring(31);
	}
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	//@CreationTimestamp  // use when repo.save
	private Timestamp creDate = new Timestamp(System.currentTimeMillis());

	////////////////////////////////////////////////////////////////////////
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	////////////////////////////////////////////////////////////////////////
	
	public String combind() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("%-4s", this.division));
		sb.append(String.format("%-3s", this.divisionType));
		sb.append(String.format("%-20s", this.trid));
		sb.append(this.content);
		
		this.length = sb.length() + 4;
		sb.insert(0, String.format("%04d", this.length));
		
		this.data = sb.toString();
		
		return this.data;
	}
	////////////////////////////////////////////////////////////////////////
	
	public String toJson() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			log.info("ERROR[{}]: {}", CurrentInfo.get(), e.getMessage());
		}
		return "{}";
	}
	
	public String toPrettyJson() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (Exception e) {
			log.info("ERROR[{}]: {}", CurrentInfo.get(), e.getMessage());
		}
		return "{}";
	}
}
