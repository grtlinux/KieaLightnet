package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

public class _ReqValidateSender {

	@StreamAnnotation
	private _ReqValidateAddress address = new _ReqValidateAddress();
	
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
	private _ReqValidateMobilePhone mobilePhone = new _ReqValidateMobilePhone();
	
	@StreamAnnotation(length = 3)
	private String nationalityCountryCode;
	
	@StreamAnnotation(length = 50)
	private String purpose;
}
