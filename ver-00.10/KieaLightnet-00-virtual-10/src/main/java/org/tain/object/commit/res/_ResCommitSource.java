package org.tain.object.commit.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ResCommitSource {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "transactionId")
	private String transactionId;
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "operatorCode")
	private String operatorCode;

	@StreamAnnotation(length = 3)
	@JsonProperty(value = "country")
	private String country;
	
	@StreamAnnotation
	@JsonProperty(value = "send")
	private _ResCommitMoney send = new _ResCommitMoney();
}
