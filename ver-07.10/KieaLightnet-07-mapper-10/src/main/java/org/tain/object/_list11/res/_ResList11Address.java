package org.tain.object._list11.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Deprecated
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResList11Address {

	@StreamAnnotation(length = 40)
	private String address;

	@StreamAnnotation(length = 40)
	private String city;
	
	@StreamAnnotation(length = 10)
	private String postalCode;
	
	@StreamAnnotation(length = 100)
	private String line1;
	
	@StreamAnnotation(length = 40)
	private String state;

	@StreamAnnotation(length = 10, usable = false)
	private String countryCode;
}
