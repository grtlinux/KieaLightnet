package org.tain.object._list11.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResList11Sender {

	@StreamAnnotation
	private _ResList11Address address = new _ResList11Address();
	
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
	private _ResList11MobilePhone mobilePhone = new _ResList11MobilePhone();
	
	@StreamAnnotation(length = 3)
	private String nationalityCountryCode;
	
	@StreamAnnotation(length = 50)
	private String purpose;
}
