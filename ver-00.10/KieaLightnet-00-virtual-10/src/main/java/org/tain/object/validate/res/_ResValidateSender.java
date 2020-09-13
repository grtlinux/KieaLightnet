package org.tain.object.validate.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ResValidateSender {

	@StreamAnnotation(length = 8)
	@JsonProperty(value = "idType")
	private String idType;
	
	@StreamAnnotation(length = 8)
	@JsonProperty(value = "idCountryCode")
	private String idCountryCode;
	
	@StreamAnnotation(length = 15)
	@JsonProperty(value = "idNumber")
	private String idNumber;
	
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "dob")
	private String dob;
	
	@StreamAnnotation(length = 50)
	@JsonProperty(value = "purpose")
	private String purpose;
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "birthCountryCode")
	private String birthCountryCode;
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "relationshipToReceiver")
	private String relationshipToReceiver;
	
	@StreamAnnotation(length = 5)
	@JsonProperty(value = "citizenshipCountryCode")
	private String citizenshipCountryCode;
	
	
	@StreamAnnotation(length = 30)
	@JsonProperty(value = "email")
	private String email;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "firstName")
	private String firstName;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "middleName")
	private String middleName;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "lastName")
	private String lastName;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "secondLastName")
	private String secondLastName;
	
	
	@StreamAnnotation
	@JsonProperty(value = "notification")
	private _ResValidateNotification notification = new _ResValidateNotification();
	
	@StreamAnnotation
	@JsonProperty(value = "address")
	private _ResValidateAddress address = new _ResValidateAddress();
	
	@StreamAnnotation
	@JsonProperty(value = "homePhone")
	private _ResValidatePhone phone = new _ResValidatePhone();
}
