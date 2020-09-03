package org.tain.object.list.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _Money {

	@StreamAnnotation(length = 30)
	private String amount;
	
	@StreamAnnotation(length = 3)
	private String currency;
}
