package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqValidateMoney {

	@StreamAnnotation(length = 30)
	private String amount;
	
	@StreamAnnotation(length = 3)
	private String currency;
}
