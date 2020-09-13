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
	private String from = "2020-09-08T00:00:00Z";

	@StreamAnnotation(length = 50)
	@JsonProperty(value = "to")
	//private String to = "2020-09-08T13:00:00Z";
	private String to = "2020-09-08T23:59:59Z";
	
	@StreamAnnotation(length = 5)
	@JsonProperty(value = "offset")
	private int offset = 1;

	@StreamAnnotation(length = 5)
	@JsonProperty(value = "limit")
	private int limit = 20;
}
