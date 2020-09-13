package org.tain.object._list11.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResList11Rate {

	@StreamAnnotation
	private _ResList11Money from = new _ResList11Money();
	
	@StreamAnnotation
	private _ResList11Money to = new _ResList11Money();
}
