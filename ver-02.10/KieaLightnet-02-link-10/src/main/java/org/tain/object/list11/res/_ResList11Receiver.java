package org.tain.object.list11.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResList11Receiver {

	@StreamAnnotation(length = 35)
	private String accountId;
	
	@StreamAnnotation(length = 11)
	private String bankCode;
	
	@StreamAnnotation(length = 80)
	private String firstName;
	
	@StreamAnnotation(length = 80)
	private String lastName;
	
	@StreamAnnotation
	private _ResList11Address address = new _ResList11Address();
	
	@StreamAnnotation
	private _ResList11MobilePhone mobilePhone = new _ResList11MobilePhone();
}
