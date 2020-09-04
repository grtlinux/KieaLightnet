package org.tain.object.dummy;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _SubDummy {

	@StreamAnnotation(length = 30)
	private String name = "SUBDUMMY";
	
	@StreamAnnotation(length = 20)
	private long lval = 1234567L;
	
	@StreamAnnotation(length = 20)
	private int ival = 2025;
	
	@StreamAnnotation(length = 20)
	private double dval = 123000000.456;
	
	@StreamAnnotation(length = 20)
	private float fval = (float) 1234.56700;
}
