package org.tain.object.list;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;

@Getter
//@JsonIgnoreProperties(value = {"idLong", "idInt"})  // to the below
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class _Data extends AbstractStream {

	//@StreamAnnotation(length = 10)
	//private long idLong;
	
	//@StreamAnnotation(length = 3)
	//private int idInt;
	
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

	@StreamAnnotation(length = 10, usable = false)
	private String remittanceType;
}
