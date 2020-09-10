package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

public class _ReqValidateReceiver {

	@StreamAnnotation(length = 35)
	private String accountId;
	
	@StreamAnnotation(length = 11)
	private String bankCode;
	
	@StreamAnnotation(length = 80)
	private String firstName;
	
	@StreamAnnotation(length = 80)
	private String lastName;
	
}
