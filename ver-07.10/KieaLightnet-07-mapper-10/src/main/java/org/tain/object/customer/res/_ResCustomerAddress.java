package org.tain.object.customer.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResCustomerAddress {

	@StreamAnnotation(length = 40)
	@JsonProperty(value = "address")
	private String address;

	@StreamAnnotation(length = 40)
	@JsonProperty(value = "city")
	private String city;
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "postalCode")
	private String postalCode;
	
	@StreamAnnotation(length = 100)
	@JsonProperty(value = "line1")
	private String line1;
	
	@StreamAnnotation(length = 40)
	@JsonProperty(value = "state")
	private String state;

	@StreamAnnotation(length = 10, usable = false)
	@JsonProperty(value = "countryCode")
	private String countryCode;
}
