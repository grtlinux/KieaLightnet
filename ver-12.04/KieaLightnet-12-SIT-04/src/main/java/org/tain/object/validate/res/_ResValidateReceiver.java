package org.tain.object.validate.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ResValidateReceiver {

	@StreamAnnotation(length = 40)
	@JsonProperty(value = "accountNumber")
	private String accountNumber;
	
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
	@JsonProperty(value = "phone")
	private _ResValidatePhone phone = new _ResValidatePhone();
}
