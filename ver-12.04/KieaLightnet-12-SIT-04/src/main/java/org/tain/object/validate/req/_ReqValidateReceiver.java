package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqValidateReceiver {

	@StreamAnnotation(length = 40)
	@JsonProperty(value = "accountNumber")
	private String accountNumber = "MG-0012345";
	
	@StreamAnnotation(length = 30)
	@JsonProperty(value = "email")
	private String email = "receiverEmail@test.com";
	
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "firstName")
	private String firstName = "receiverFirstName";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "middleName")
	private String middleName;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "lastName")
	private String lastName = "receiverLastName";
	
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
