package org.tain.object;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class LnsPacket {

	private String data;
	private int length;
	
	private String strLength;
	private String division;
	private String type;
	private String tid;        // transaction id
	private String content;
	
	public LnsPacket(String data) {
		this.data      = data;
		this.length    = data.length();
		
		this.strLength = data.substring( 0,  4);
		this.division  = data.substring( 4,  8);
		this.type      = data.substring( 8, 11);
		this.tid       = data.substring(11, 31);
		this.content   = data.substring(31);
	}
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	//@CreationTimestamp
	private Timestamp creatDate = new Timestamp(System.currentTimeMillis());
}
