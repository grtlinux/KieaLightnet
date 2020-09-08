package org.tain.object.lns;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class LnsJson implements Cloneable {

	private String name;
	private String title;
	
	//private String workUrl;        // scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]
	//private String division;       // trid/validate/commit/list/detail/callback
	//private String divisionType;   // REQ / RES
	
	//private String dataType;       // STREAM / JSON / ALL
	private String reqStrData;     // request stream data
	private String reqJsonData;    // request json data
	private String resStrData;     // response stream data
	private String resJsonData;    // response json data
	
	private String code;           // 00000: SUCCESS, 99999: FAIL
	private String message;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	//@CreationTimestamp  // use when repo.save
	private Timestamp creDate = new Timestamp(System.currentTimeMillis());

	@Builder
	public LnsJson(
			String name,
			String title,
			//String workUrl,
			//String division,
			//String divisionType,
			//String dataType,
			String reqStrData,
			String reqJsonData,
			String resStrData,
			String resJsonData,
			String code,
			String message
			) {
		this.name = name;
		this.title = title;
		//this.workUrl = workUrl;
		//this.division = division;
		//this.divisionType = divisionType;
		//this.dataType = dataType;
		this.reqStrData = reqStrData;
		this.reqJsonData = reqJsonData;
		this.resStrData = resStrData;
		this.resJsonData = resJsonData;
		this.code = code;
		this.message = message;
	}
	
	////////////////////////////////////////////////////////////////////////
	
	@Override
	public LnsJson clone() throws CloneNotSupportedException {
		return (LnsJson) super.clone();
	}
}
