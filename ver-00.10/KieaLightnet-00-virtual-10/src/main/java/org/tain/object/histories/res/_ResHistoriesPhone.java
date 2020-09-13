package org.tain.object.histories.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResHistoriesPhone {

	@StreamAnnotation(length = 4)
	@JsonProperty(value = "countryCode")
	private String countryCode;

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "number")
	private String number;
}
