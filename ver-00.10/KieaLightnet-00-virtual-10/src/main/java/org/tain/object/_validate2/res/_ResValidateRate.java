package org.tain.object._validate2.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResValidateRate {

	@StreamAnnotation
	private _ResValidateMoney from = new _ResValidateMoney();
	
	@StreamAnnotation
	private _ResValidateMoney to = new _ResValidateMoney();
}
