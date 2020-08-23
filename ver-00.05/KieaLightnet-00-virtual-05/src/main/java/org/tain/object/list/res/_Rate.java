package org.tain.object.list.res;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _Rate extends AbstractStream {

	@StreamAnnotation
	private _Money from = new _Money();
	
	@StreamAnnotation
	private _Money to = new _Money();
}
