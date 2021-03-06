package org.tain.object.trid.req;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _TridReqTrid extends AbstractStream {

	@StreamAnnotation(length = 10)
	private String command = "trid.req";
	
	@StreamAnnotation(length = 50, useNullSpace = true)
	private String tridCode;
}
