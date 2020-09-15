package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqValidateMoney {

	@StreamAnnotation(length = 30)
	@JsonProperty(value = "amount")
	private String amount = "43366.3500";
	
	@StreamAnnotation(length = 3)
	@JsonProperty(value = "currency")
	private String currency = "IDR";
}
