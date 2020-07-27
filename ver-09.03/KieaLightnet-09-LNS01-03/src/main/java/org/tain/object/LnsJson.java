package org.tain.object;

import java.sql.Timestamp;

import org.tain.utils.CurrentInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
public class LnsJson {

	private String name;
	private String title;
	
	private String workUrl;
	private String division;
	private String divisionType;
	
	private String dataType;
	private String reqData;
	private String resData;
	
	private String code;
	private String message;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	//@CreationTimestamp  // use when repo.save
	private Timestamp creDate = new Timestamp(System.currentTimeMillis());

	@Builder
	public LnsJson(
			String name,
			String title,
			String workUrl,
			String division,
			String divisionType,
			String dataType,
			String reqData,
			String resData,
			String code,
			String message
			) {
		this.name = name;
		this.title = title;
		this.workUrl = workUrl;
		this.division = division;
		this.divisionType = divisionType;
		this.dataType = dataType;
		this.reqData = reqData;
		this.resData = resData;
		this.code = code;
		this.message = message;
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
