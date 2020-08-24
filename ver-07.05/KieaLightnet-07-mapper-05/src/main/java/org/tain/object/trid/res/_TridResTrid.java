package org.tain.object.trid.res;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _TridResTrid extends AbstractStream {

	@StreamAnnotation(length = 10)
	private String command = "trid.res";
	
	@StreamAnnotation(length = 50, useNullSpace = true)
	private String tridCode;
}
