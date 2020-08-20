package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@SuppressWarnings("unused")
public class _Destination extends AbstractToString {

	@StreamAnnotation(length = 10)
	private String transactionId;

	@StreamAnnotation(length = 10)
	private String operatorCode;
	
	@StreamAnnotation(length = 10)
	private String country;
	
	private _Money receive;
}
