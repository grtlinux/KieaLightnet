package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@SuppressWarnings("unused")
public class _Data extends AbstractToString {

	@StreamAnnotation(length = 10)
	private String transactionId;
	
	@StreamAnnotation(length = 10)
	private String deliveryMethod;
	
	private _Source source;
	private _Destination destination;
	
	private _Fee fee;
	
	private _Rate rate;
	
	private _Sender sender;
	private _Receiver receiver;
	
	@StreamAnnotation(length = 10)
	private String status;

	@StreamAnnotation(length = 10)
	private String remittanceType;
}
