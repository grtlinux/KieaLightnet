package org.tain.object.list;

import org.tain.annotation.AbstractToString;
import org.tain.annotation.StreamAnnotation;

import lombok.Getter;

@Getter
public class _MobilePhone extends AbstractToString {

	@StreamAnnotation(length = 20)
	private String countryCode;

	@StreamAnnotation(length = 4)
	private String number;
}
