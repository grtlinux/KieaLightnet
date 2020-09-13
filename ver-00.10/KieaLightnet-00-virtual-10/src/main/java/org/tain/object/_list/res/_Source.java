package org.tain.object._list.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _Source {

	@StreamAnnotation(length = 20)
	private String transactionId;
	
	@StreamAnnotation(length = 10)
	private String operatorCode;

	@StreamAnnotation(length = 3)
	private String country;
	
	@StreamAnnotation
	private _Money send = new _Money();
}
