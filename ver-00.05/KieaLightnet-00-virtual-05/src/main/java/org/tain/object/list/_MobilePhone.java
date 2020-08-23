package org.tain.object.list;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _MobilePhone extends AbstractStream {

	@StreamAnnotation(length = 4)
	private String countryCode;

	@StreamAnnotation(length = 20)
	private String number;
}
