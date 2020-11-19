package org.tain.object.lns;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class LnsStream implements Cloneable {

	private String data;
	private int length;           // data_length
	
	private String strLength;     // 4 = (length - 4) = (7 + content)
	private String content;
	
	public LnsStream(String data) {
		this.data   = data;
		this.length = data.length();  // data_length
		
		this.strLength    = data.substring(0, 4);   // info_length = data_length - 4
		this.content      = data.substring(4);     // to transfer stream to json
	}
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	//@CreationTimestamp  // use when repo.save
	private Timestamp creDate = new Timestamp(System.currentTimeMillis());
}
