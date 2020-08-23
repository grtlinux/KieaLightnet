package org.tain.object.list;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _Receiver extends AbstractStream {

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
