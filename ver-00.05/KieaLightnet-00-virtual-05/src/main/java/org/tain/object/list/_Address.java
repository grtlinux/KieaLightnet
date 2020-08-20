package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class _Address extends AbstractToString {

	@StreamAnnotation(length = 10)
	private String address;

	@StreamAnnotation(length = 10)
	private String city;
	
	@StreamAnnotation(length = 10)
	private String countryCode;
	
	@StreamAnnotation(length = 10)
	private String postalCode;
}
