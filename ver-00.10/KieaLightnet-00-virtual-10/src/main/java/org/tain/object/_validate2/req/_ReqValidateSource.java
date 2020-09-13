package org.tain.object._validate2.req;

import org.tain.annotation.StreamAnnotation;

public class _ReqValidateSource {

	@StreamAnnotation(length = 20)
	private String transactionId;
	
	@StreamAnnotation(length = 10)
	private String operatorCode;

	@StreamAnnotation(length = 3)
	private String country;
	
	@StreamAnnotation
	private _ReqValidateMoney send = new _ReqValidateMoney();
}
