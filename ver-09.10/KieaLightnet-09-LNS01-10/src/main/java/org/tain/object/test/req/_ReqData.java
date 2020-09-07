package org.tain.object.test.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqData {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "title")
	private String title;
	
	@StreamAnnotation
	@JsonProperty(value = "name")
	private _ReqName name = new _ReqName();
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "message")
	private String message;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "status")
	private String status;
}
