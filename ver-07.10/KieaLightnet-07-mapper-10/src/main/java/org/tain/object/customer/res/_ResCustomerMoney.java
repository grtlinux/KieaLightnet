package org.tain.object.customer.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResCustomerMoney {

	@StreamAnnotation(length = 30)
	@JsonProperty(value = "amount")
	private String amount;
	
	@StreamAnnotation(length = 3)
	@JsonProperty(value = "currency")
	private String currency;
}
