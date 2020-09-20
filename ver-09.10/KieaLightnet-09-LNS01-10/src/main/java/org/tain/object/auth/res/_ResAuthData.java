package org.tain.object.auth.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResAuthData {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "status")
	private String status = "success";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "message")
	private String message = "OK";
	
	//@StreamAnnotation(length = 40)
	@JsonProperty(value = "accessToken")
	private String accessToken;
}
