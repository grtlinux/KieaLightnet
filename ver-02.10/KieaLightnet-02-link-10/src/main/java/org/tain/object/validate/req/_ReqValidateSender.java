package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ReqValidateSender {

	@StreamAnnotation(length = 8)
	@JsonProperty(value = "idType")
	private String idType = "GOV";
	
	@StreamAnnotation(length = 8)
	@JsonProperty(value = "idCountryCode")
	private String idCountryCode = "MYS";
	
	@StreamAnnotation(length = 15)
	@JsonProperty(value = "idNumber")
	private String idNumber = "P83245384";
	
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "dob")
	private String dob = "1992-03-23";
	
	@StreamAnnotation(length = 50)
	@JsonProperty(value = "purpose")
	private String purpose = "PURCHASE_GOODS";
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "birthCountryCode")
	private String birthCountryCode = "THA";
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "relationshipToReceiver")
	private String relationshipToReceiver = "BUSINESS_PARTNER";
	
	@StreamAnnotation(length = 5)
	@JsonProperty(value = "citizenshipCountryCode")
	private String citizenshipCountryCode = "THA";
	
	
	@StreamAnnotation(length = 30)
	@JsonProperty(value = "email")
	private String email = "senderEmail@test.com";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "firstName")
	private String firstName = "IQLZSO";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "middleName")
	private String middleName = "senderMiddleName";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "lastName")
	private String lastName = "senderLastName";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "secondLastName")
	private String secondLastName = "senderSecondLastName";
	
	
	@StreamAnnotation
	@JsonProperty(value = "notification")
	private _ReqValidateNotification notification = new _ReqValidateNotification();
	
	@StreamAnnotation
	@JsonProperty(value = "address")
	private _ReqValidateAddress address = new _ReqValidateAddress();
	
	@StreamAnnotation
	@JsonProperty(value = "homePhone")
	private _ReqValidatePhone phone = new _ReqValidatePhone();
}
