package org.tain.object.validate.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ResValidatePromotion {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "code")
	private String code;
	
	@StreamAnnotation
	@JsonProperty(value = "discount")
	private _ResValidateMoney discount = new _ResValidateMoney();
}
