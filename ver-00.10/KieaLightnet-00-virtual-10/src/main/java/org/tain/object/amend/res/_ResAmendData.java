package org.tain.object.amend.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResAmendData {

	@StreamAnnotation(length = 50)
	@JsonProperty(value = "status")
	private String status;
	
	@StreamAnnotation(length = 50)
	@JsonProperty(value = "message")
	private String message;
	
	@StreamAnnotation(length = 40)
	@JsonProperty(value = "accessToken")
	private String accessToken;
}
