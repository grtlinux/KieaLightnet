package org.tain.object._validate2.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResValidateReceiver {

	@StreamAnnotation(length = 35)
	private String accountId;
	
	@StreamAnnotation(length = 11)
	private String bankCode;
	
	@StreamAnnotation(length = 80)
	private String firstName;
	
	@StreamAnnotation(length = 80)
	private String lastName;
	
	@StreamAnnotation
	private _ResValidateAddress address = new _ResValidateAddress();
	
	@StreamAnnotation
	private _ResValidateMobilePhone mobilePhone = new _ResValidateMobilePhone();
}
