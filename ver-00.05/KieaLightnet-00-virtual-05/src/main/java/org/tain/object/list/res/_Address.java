package org.tain.object.list.res;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _Address extends AbstractStream {

	@StreamAnnotation(length = 40)
	private String address;

	@StreamAnnotation(length = 40)
	private String city;
	
	@StreamAnnotation(length = 10)
	private String postalCode;
	
	@StreamAnnotation(length = 100)
	private String line1;
	
	@StreamAnnotation(length = 40)
	private String state;

	@StreamAnnotation(length = 10, usable = false)
	private String countryCode;
}
