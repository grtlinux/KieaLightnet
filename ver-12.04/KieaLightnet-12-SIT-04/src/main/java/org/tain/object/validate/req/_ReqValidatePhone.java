package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqValidatePhone {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "number")
	private String number = "345364566";

	@StreamAnnotation(length = 3)
	@JsonProperty(value = "countryCode")
	private String countryCode = "33";
}
