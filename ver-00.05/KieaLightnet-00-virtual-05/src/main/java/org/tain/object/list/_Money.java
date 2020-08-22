package org.tain.object.list;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import lombok.Getter;

@Getter
public class _Money extends AbstractStream {

	@StreamAnnotation(length = 30)
	private String amount;
	
	@StreamAnnotation(length = 3)
	private String currency;
}
