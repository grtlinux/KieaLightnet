package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.Getter;

@Getter
public class _Money extends AbstractToString {

	@StreamAnnotation(length = 10)
	private String amount;
	
	@StreamAnnotation(length = 3)
	private String currency;
}
