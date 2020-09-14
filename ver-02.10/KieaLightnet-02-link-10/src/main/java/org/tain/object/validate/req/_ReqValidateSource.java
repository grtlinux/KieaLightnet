package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqValidateSource {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "transactionId")
	private String transactionId = "4324682635157306";
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "operatorCode")
	private String operatorCode;

	@StreamAnnotation(length = 3)
	@JsonProperty(value = "country")
	private String country = "KOR";
	
	@StreamAnnotation
	@JsonProperty(value = "send")
	private _ReqValidateMoney send = new _ReqValidateMoney();
}
