package org.tain.object.validate.res;

import org.tain.annotation.StreamAnnotation;

public class _ResValidateSubData {

	@StreamAnnotation(length = 50)
	private String transactionId;
	
	@StreamAnnotation(length = 20)
	private String deliveryMethod;
	
	@StreamAnnotation
	private _ResValidateSource source = new _ResValidateSource();
	
	@StreamAnnotation
	private _ResValidateDestination destination = new _ResValidateDestination();
	
	@StreamAnnotation
	private _ResValidateFee fee = new _ResValidateFee();
	
	@StreamAnnotation
	private _ResValidateRate rate = new _ResValidateRate();
	
	@StreamAnnotation
	private _ResValidateSender sender = new _ResValidateSender();
	
	@StreamAnnotation
	private _ResValidateReceiver receiver = new _ResValidateReceiver();

	@StreamAnnotation(length = 100)
	private String remark;
	
	@StreamAnnotation(length = 40)
	private String status;
}
