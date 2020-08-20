package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@SuppressWarnings("unused")
public class _Sender extends AbstractToString {

	private _Address address;
	
	@StreamAnnotation(length = 10)
	private String firstName;
	
	@StreamAnnotation(length = 10)
	private String idNumber;

	@StreamAnnotation(length = 10)
	private String lastName;
	
	private _MobilePhone mobilePhone;
	
	@StreamAnnotation(length = 10)
	private String nationalityCountryCode;
}
