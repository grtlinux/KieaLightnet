package org.tain.object.validate.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResValidateFee {

	@StreamAnnotation(length = 30)
	private String amount;

	@StreamAnnotation(length = 3, useNullSpace = true)
	private String currency;
	
	@StreamAnnotation(length = 20)
	private String model;
}