package org.tain.object._validate2.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResValidateSender {

	@StreamAnnotation
	private _ResValidateAddress address = new _ResValidateAddress();
	
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
	private _ResValidateMobilePhone mobilePhone = new _ResValidateMobilePhone();
	
	@StreamAnnotation(length = 3)
	private String nationalityCountryCode;
	
	@StreamAnnotation(length = 50)
	private String purpose;
}
