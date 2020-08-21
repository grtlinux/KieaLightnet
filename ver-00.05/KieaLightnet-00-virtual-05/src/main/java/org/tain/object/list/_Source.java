package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.Getter;

@Getter
public class _Source extends AbstractToString {

	@StreamAnnotation(length = 20)
	private String transactionId;
	
	@StreamAnnotation(length = 10)
	private String operatorCode;

	@StreamAnnotation(length = 3)
	private String country;
	
	@StreamAnnotation
	private _Money send = new _Money();
}
