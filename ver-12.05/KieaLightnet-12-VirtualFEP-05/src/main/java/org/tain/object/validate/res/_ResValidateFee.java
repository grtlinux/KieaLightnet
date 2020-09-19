package org.tain.object.validate.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResValidateFee {

	@StreamAnnotation(length = 30)
	@JsonProperty(value = "amount")
	private String amount;

	@StreamAnnotation(length = 3, useNullSpace = true)
	@JsonProperty(value = "currency")
	private String currency;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "model")
	private String model;
}
