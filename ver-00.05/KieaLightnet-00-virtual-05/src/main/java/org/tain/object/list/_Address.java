package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.Getter;

@Getter
public class _Address extends AbstractToString {

	@StreamAnnotation(length = 40)
	private String address;

	@StreamAnnotation(length = 40)
	private String city;
	
	//@StreamAnnotation(length = 10)
	private String line1;
	
	//@StreamAnnotation(length = 10)
	private String countryCode;
	
	@StreamAnnotation(length = 10)
	private String postalCode;
	
	//@StreamAnnotation(length = 10)
	private String state;
}
