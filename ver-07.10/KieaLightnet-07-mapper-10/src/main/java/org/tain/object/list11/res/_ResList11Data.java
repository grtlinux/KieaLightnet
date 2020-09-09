package org.tain.object.list11.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
//@JsonIgnoreProperties(value = {"idLong", "idInt"})  // to the below
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class _ResList11Data {

	@StreamAnnotation(length = 50)
	private String transactionId;
	
	@StreamAnnotation(length = 20)
	private String deliveryMethod;
	
	@StreamAnnotation
	private _ResList11Source source = new _ResList11Source();
	
	@StreamAnnotation
	private _ResList11Destination destination = new _ResList11Destination();
	
	@StreamAnnotation
	private _ResList11Fee fee = new _ResList11Fee();
	
	@StreamAnnotation
	private _ResList11Rate rate = new _ResList11Rate();
	
	@StreamAnnotation
	private _ResList11Sender sender = new _ResList11Sender();
	
	@StreamAnnotation
	private _ResList11Receiver receiver = new _ResList11Receiver();
	
	@StreamAnnotation(length = 40)
	private String status;

	@StreamAnnotation(length = 10, usable = false)
	private String remittanceType;
}
