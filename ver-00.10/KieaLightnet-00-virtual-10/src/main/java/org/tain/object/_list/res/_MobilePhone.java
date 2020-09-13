package org.tain.object._list.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _MobilePhone {

	@StreamAnnotation(length = 4)
	private String countryCode;

	@StreamAnnotation(length = 20)
	private String number;
}
