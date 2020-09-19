package org.tain.object.validate.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ResValidateSubData {

	@StreamAnnotation(length = 50)
	@JsonProperty(value = "transactionId")
	private String transactionId;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "deliveryMethod")
	private String deliveryMethod;
	
	
	@StreamAnnotation
	@JsonProperty(value = "source")
	private _ResValidateSource source = new _ResValidateSource();
	
	@StreamAnnotation
	@JsonProperty(value = "destination")
	private _ResValidateDestination destination = new _ResValidateDestination();
	
	@StreamAnnotation
	@JsonProperty(value = "fee")
	private _ResValidateFee fee = new _ResValidateFee();
	
	@StreamAnnotation
	@JsonProperty(value = "rate")
	private _ResValidateRate rate = new _ResValidateRate();
	
	@StreamAnnotation
	@JsonProperty(value = "sender")
	private _ResValidateSender sender = new _ResValidateSender();
	
	@StreamAnnotation
	@JsonProperty(value = "receiver")
	private _ResValidateReceiver receiver = new _ResValidateReceiver();
	
	
	@StreamAnnotation(length = 100)
	@JsonProperty(value = "remark")
	private String remark;
	
	@StreamAnnotation(length = 40)
	@JsonProperty(value = "status")
	private String status;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "remittanceType")
	private String remittanceType;
	
	//@StreamAnnotation
	@JsonProperty(value = "promotions")
	private _ResValidatePromotion[] promotions;
}
