package org.tain.object._validate2.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResValidateMobilePhone {

	@StreamAnnotation(length = 4)
	private String countryCode;

	@StreamAnnotation(length = 20)
	private String number;
}
