package org.tain.object.test.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResData {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "title")
	private String title;
	
	@StreamAnnotation
	@JsonProperty(value = "res_name")
	private _ResName name = new _ResName();
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "message")
	private String message;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "status")
	private String status;
}
