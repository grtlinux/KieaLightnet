package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.Getter;

@Getter
public class _Fee extends AbstractToString {

	@StreamAnnotation(length = 30)
	private String amount;

	@StreamAnnotation(length = 3)
	private String currency;
	
	@StreamAnnotation(length = 20)
	private String model;
}
