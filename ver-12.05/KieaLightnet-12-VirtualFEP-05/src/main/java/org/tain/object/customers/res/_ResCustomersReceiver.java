package org.tain.object.customers.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ResCustomersReceiver {

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
	private _ResCustomersDestination destination = new _ResCustomersDestination();
	
	@StreamAnnotation
	@JsonProperty(value = "source")
	private _ResCustomersSource source = new _ResCustomersSource();
}
