package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.Getter;

@Getter
public class _Data extends AbstractToString {

	@StreamAnnotation(length = 50)
	private String transactionId;
	
	@StreamAnnotation(length = 20)
	private String deliveryMethod;
	
	@StreamAnnotation
	private _Source source = new _Source();
	
	@StreamAnnotation
	private _Destination destination = new _Destination();
	
	@StreamAnnotation
	private _Fee fee = new _Fee();
	
	@StreamAnnotation
	private _Rate rate = new _Rate();
	
	@StreamAnnotation
	private _Sender sender = new _Sender();
	
	@StreamAnnotation
	private _Receiver receiver = new _Receiver();
	
	@StreamAnnotation(length = 40)
	private String status;

	//@StreamAnnotation(length = 10)
	private String remittanceType;
}