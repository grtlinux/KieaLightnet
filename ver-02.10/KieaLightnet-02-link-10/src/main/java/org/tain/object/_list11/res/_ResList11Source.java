package org.tain.object._list11.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResList11Source {

	@StreamAnnotation(length = 20)
	private String transactionId;
	
	@StreamAnnotation(length = 10)
	private String operatorCode;

	@StreamAnnotation(length = 3)
	private String country;
	
	@StreamAnnotation
	private _ResList11Money send = new _ResList11Money();
}
