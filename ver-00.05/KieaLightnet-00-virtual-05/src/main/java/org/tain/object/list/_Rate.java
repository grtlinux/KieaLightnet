package org.tain.object.list;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import lombok.Getter;

@Getter
public class _Rate extends AbstractStream {

	@StreamAnnotation
	private _Money from = new _Money();
	
	@StreamAnnotation
	private _Money to = new _Money();
}
