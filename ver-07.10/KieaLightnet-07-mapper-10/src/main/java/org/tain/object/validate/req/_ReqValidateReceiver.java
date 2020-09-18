package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ReqValidateReceiver {

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
	private _ReqValidateNotification notification = new _ReqValidateNotification();
	
	@StreamAnnotation
	@JsonProperty(value = "address")
	private _ReqValidateAddress address = new _ReqValidateAddress();
	
	@StreamAnnotation
	@JsonProperty(value = "phone")
	private _ReqValidatePhone phone = new _ReqValidatePhone();
}
