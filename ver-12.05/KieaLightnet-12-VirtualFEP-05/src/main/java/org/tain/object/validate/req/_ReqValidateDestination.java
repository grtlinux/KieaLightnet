package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqValidateDestination {

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
	@JsonProperty(value = "receive")
	private _ReqValidateMoney receive = new _ReqValidateMoney();
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "withdrawalId")
	private String withdrawalId;
}
