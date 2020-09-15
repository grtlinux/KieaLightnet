package org.tain.object.commit.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ResCommitPromotion {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "code")
	private String code;
	
	@StreamAnnotation
	@JsonProperty(value = "discount")
	private _ResCommitMoney discount = new _ResCommitMoney();
}
