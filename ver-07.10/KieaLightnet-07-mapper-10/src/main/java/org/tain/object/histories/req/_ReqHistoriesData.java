package org.tain.object.histories.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqHistoriesData {

	@StreamAnnotation(length = 50)
	@JsonProperty(value = "from")
	private String from;

	@StreamAnnotation(length = 50)
	@JsonProperty(value = "to")
	private String to;
	
	@StreamAnnotation(length = 5)
	@JsonProperty(value = "offset")
	private int offset;

	@StreamAnnotation(length = 5)
	@JsonProperty(value = "limit")
	private int limit;
}
