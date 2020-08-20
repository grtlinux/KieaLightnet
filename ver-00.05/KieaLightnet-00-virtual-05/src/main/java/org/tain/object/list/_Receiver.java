package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class _Receiver extends AbstractToString {

	@StreamAnnotation(length = 10)
	private String accountId;

	@StreamAnnotation(length = 10)
	private String bankCode;
	
	@StreamAnnotation(length = 10)
	private String firstName;
	
	@StreamAnnotation(length = 10)
	private String lastName;
}
