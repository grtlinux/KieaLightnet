package org.tain.object.customer.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ResCustomerSource {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "transactionId")
	private String transactionId;
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "operatorCode")
	private String operatorCode;

	@StreamAnnotation(length = 3)
	@JsonProperty(value = "country")
	private String country;
	
	@StreamAnnotation
	@JsonProperty(value = "transaction")
	private _ResCustomerMoney transaction = new _ResCustomerMoney();
}
