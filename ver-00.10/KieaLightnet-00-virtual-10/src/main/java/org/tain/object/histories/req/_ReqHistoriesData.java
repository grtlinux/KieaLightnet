package org.tain.object.histories.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqHistoriesData {

	@StreamAnnotation(length = 40)
	@JsonProperty(value = "clientId")
	private String clientId;

	@StreamAnnotation(length = 100)
	@JsonProperty(value = "secret")
	private String secret;
}
