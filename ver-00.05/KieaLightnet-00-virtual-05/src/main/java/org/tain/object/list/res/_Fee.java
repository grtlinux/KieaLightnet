package org.tain.object.list.res;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _Fee extends AbstractStream {

	@StreamAnnotation(length = 30)
	private String amount;

	@StreamAnnotation(length = 3, useNullSpace = true)
	private String currency;
	
	@StreamAnnotation(length = 20)
	private String model;
}
