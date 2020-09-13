package org.tain.object._test.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResTestData {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "title")
	private String title;
	
	@StreamAnnotation
	@JsonProperty(value = "name")
	private _ResTestName name = new _ResTestName();
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "message")
	private String message;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "status")
	private String status;
}
