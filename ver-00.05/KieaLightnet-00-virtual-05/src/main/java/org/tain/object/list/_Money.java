package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class _Money extends AbstractToString {

	@StreamAnnotation(length = 10)
	private String amount;
	
	@StreamAnnotation(length = 10)
	private String currency;
	
	//private String dummy;
}
