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
public class LnsJson {

	private String name;
	
	private String httpUrl;        // scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]
	private String httpMethod;     // trid/validate/commit/list/detail/callback
	
	private String reqStrData;     // request stream data
	private String reqJsonData;    // request json data
	private String resStrData;     // response stream data
	private String resJsonData;    // response json data
	private String batData;        // batch data for file
	
	private String code;           // 00000, 99999
	private String status;         // SUCCESS, FAIL
	private String msgJson;        // {"":"", "":""}
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	//@CreationTimestamp  // use when repo.save
	private Timestamp creDate = new Timestamp(System.currentTimeMillis());
}
