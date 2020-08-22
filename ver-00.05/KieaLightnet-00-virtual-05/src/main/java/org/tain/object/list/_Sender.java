package org.tain.object.list;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import lombok.Getter;

@Getter
public class _Sender extends AbstractStream {

	@StreamAnnotation
	private _Address address = new _Address();
	
	@StreamAnnotation(length = 10, usable = false)
	private String dob;
	
	@StreamAnnotation(length = 8)
	private String idNumber;
	
	@StreamAnnotation(length = 80)
	private String firstName;
	
	@StreamAnnotation(length = 80)
	private String lastName;
	
	@StreamAnnotation(length = 10)
	private String idIssueDate;
	
	@StreamAnnotation(length = 10)
	private String idExpirationDate;
	
	@StreamAnnotation
	private _MobilePhone mobilePhone = new _MobilePhone();
	
	@StreamAnnotation(length = 3)
	private String nationalityCountryCode;
	
	@StreamAnnotation(length = 50)
	private String purpose;
}
