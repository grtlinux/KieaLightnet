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
	
	@Deprecated
	private String workUrl;        // scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]
	@Deprecated
	private String division;       // trid/validate/commit/list/detail/callback
	@Deprecated
	private String divisionType;   // REQ / RES
	
	private String trType;         // 0200(REQ) / 0210(RES)
	private String trCode;         // 100(trid), 200(detail), 300(validate), 400(commit), 500(callback), 700(list), 900(auth)
	private String svrCode;        // 
	
	private String reqStrData;     // request stream data
	private String reqJsonData;    // request json data
	private String resStrData;     // response stream data
	private String resJsonData;    // response json data
	
	@Deprecated
	private String resCode;           // 00000: SUCCESS, 99999: FAIL
	@Deprecated
	private String resMessage;        // return message
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	//@CreationTimestamp  // use when repo.save
	private Timestamp creDate = new Timestamp(System.currentTimeMillis());

	@Builder
	public LnsJson(
			String name,
			String title,
			String trType,
			String trCode,
			String svrCode,
			String reqStrData,
			String reqJsonData,
			String resStrData,
			String resJsonData
			) {
		this.name = name;
		this.title = title;
		this.trType = trType;
		this.trCode = trCode;
		this.svrCode = svrCode;
		this.reqStrData = reqStrData;
		this.reqJsonData = reqJsonData;
		this.resStrData = resStrData;
		this.resJsonData = resJsonData;
	}
	
	////////////////////////////////////////////////////////////////////////
	
	@Override
	public LnsJson clone() throws CloneNotSupportedException {
		return (LnsJson) super.clone();
	}
}
