package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqValidateAddress {

	@StreamAnnotation(length = 40)
	@JsonProperty(value = "address")
	private String address;

	@StreamAnnotation(length = 40)
	@JsonProperty(value = "city")
	private String city = "Bangkok";
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "postalCode")
	private String postalCode = "10400";
	
	@StreamAnnotation(length = 100)
	@JsonProperty(value = "line1")
	private String line1 = "MG-0012345";
	
	@StreamAnnotation(length = 40)
	@JsonProperty(value = "state")
	private String state;

	@StreamAnnotation(length = 10, usable = false)
	@JsonProperty(value = "countryCode")
	private String countryCode;
}
