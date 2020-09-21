package org.tain.object.customer.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ResCustomerReceiver {

	@StreamAnnotation(length = 10)
	@JsonProperty(value = "deliveryMethod")
	private String deliveryMethod;
	
	@StreamAnnotation(length = 5)
	@JsonProperty(value = "payoutCurrency")
	private String payoutCurrency;
	
	
	@StreamAnnotation(length = 30)
	@JsonProperty(value = "firstName")
	private String firstName;
	
	@StreamAnnotation(length = 30)
	@JsonProperty(value = "middleName")
	private String middleName;
	
	@StreamAnnotation(length = 30)
	@JsonProperty(value = "lastName")
	private String lastName;
	
	@StreamAnnotation(length = 30)
	@JsonProperty(value = "secondLastName")
	private String secondLastName;
	
	
	@StreamAnnotation
	@JsonProperty(value = "destination")
	private _ResCustomerDestination destination = new _ResCustomerDestination();
	
	@StreamAnnotation
	@JsonProperty(value = "source")
	private _ResCustomerSource source = new _ResCustomerSource();
}
