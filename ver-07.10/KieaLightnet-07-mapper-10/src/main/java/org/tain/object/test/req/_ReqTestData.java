package org.tain.object.test.req;

import java.util.List;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqTestData {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "title")
	private String title;  // no-used
	
	@StreamAnnotation(length = 30)
	@JsonProperty(value = "terminalName")
	private String terminalName;
	
	@StreamAnnotation(length = 50)
	@JsonProperty(value = "transactionId")
	private String transactionId;
	
	@StreamAnnotation
	@JsonProperty(value = "receiver")
	private _ReqTestName receiver; // = new _ReqTestName();
	
	//@StreamAnnotation(length = 20)
	@JsonProperty(value = "message")
	private String message;  // no-used
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "status")
	private String status;  // no-used
	
	@JsonProperty(value = "strings")
	private List<String> strings;
}
