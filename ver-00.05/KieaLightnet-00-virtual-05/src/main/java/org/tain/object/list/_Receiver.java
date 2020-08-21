package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.Getter;

@Getter
public class _Receiver extends AbstractToString {

	@StreamAnnotation(length = 35)
	private String accountId;
	
	@StreamAnnotation(length = 11)
	private String bankCode;
	
	@StreamAnnotation(length = 80)
	private String firstName;
	
	@StreamAnnotation(length = 80)
	private String lastName;
	
	@StreamAnnotation
	private _Address address = new _Address();
	
	@StreamAnnotation
	private _MobilePhone mobilePhone = new _MobilePhone();
}
