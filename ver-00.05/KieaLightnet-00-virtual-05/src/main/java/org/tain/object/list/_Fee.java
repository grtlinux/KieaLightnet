package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class _Fee extends AbstractToString {

	@StreamAnnotation(length = 10)
	private String amount;

	@StreamAnnotation(length = 10)
	private String currency;
	
	@StreamAnnotation(length = 10)
	private String model;
}
