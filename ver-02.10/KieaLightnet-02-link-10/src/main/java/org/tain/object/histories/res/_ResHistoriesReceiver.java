package org.tain.object.histories.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResHistoriesReceiver {

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
	private _ResHistoriesNotification notification = new _ResHistoriesNotification();
	
	@StreamAnnotation
	@JsonProperty(value = "address")
	private _ResHistoriesAddress address = new _ResHistoriesAddress();
	
	@StreamAnnotation
	@JsonProperty(value = "phone")
	private _ResHistoriesPhone phone = new _ResHistoriesPhone();
}
