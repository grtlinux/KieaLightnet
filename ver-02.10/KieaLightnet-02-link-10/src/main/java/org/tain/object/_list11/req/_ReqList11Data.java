package org.tain.object._list11.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
//@JsonIgnoreProperties(value = {"idLong", "idInt"})  // to the below
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class _ReqList11Data {

	@StreamAnnotation(length = 40, useNullSpace = true)
	private String operatorCode = "";
	
	@StreamAnnotation(length = 4)
	private int offset = 0;
	
	@StreamAnnotation(length = 4)
	private int limit = 20;
	
	@StreamAnnotation(length = 20)
	private String from;
	
	@StreamAnnotation(length = 20)
	private String to;
}
