package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;
import org.tain.object.list11.res._ResList11Money;

public class _ReqValidateDestination {

	@StreamAnnotation(length = 20)
	private String transactionId;

	@StreamAnnotation(length = 10)
	private String operatorCode;
	
	@StreamAnnotation(length = 3)
	private String country;
	
	@StreamAnnotation
	private _ResList11Money receive = new _ResList11Money();
	
	@StreamAnnotation(length = 10)
	private String withdrawalId;
}
