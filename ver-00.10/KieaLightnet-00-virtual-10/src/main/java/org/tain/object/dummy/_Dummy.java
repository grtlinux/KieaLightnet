package org.tain.object.dummy;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
//@JsonIgnoreProperties(value = {"idLong", "idInt"})  // to the below
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class _Dummy {

	@StreamAnnotation(length = 30)
	private String amount = "123.456";

	@StreamAnnotation(length = 3, useNullSpace = true)
	private String currency = "KRW";
	
	@StreamAnnotation
	private _SubDummy subDummy = new _SubDummy();
	
	@StreamAnnotation(length = 20)
	private String model = "ACCEPT";
	
	@StreamAnnotation(length = 20)
	private long lval = 1234567L;
	
	@StreamAnnotation(length = 20)
	private int ival = 2025;
	
	@StreamAnnotation(length = 20)
	private double dval = 123000000.456;
	
	@StreamAnnotation(length = 20)
	private float fval = (float) 1234.56700;
}
