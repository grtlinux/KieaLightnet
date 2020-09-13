package org.tain.object.histories.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResHistoriesMetadata {

	@JsonProperty(value = "totalRecords")
	private int totalRecords;
	
	@JsonProperty(value = "totalPages")
	private int totalPages;

	@JsonProperty(value = "next")
	private String next;
	
	@JsonProperty(value = "self")
	private String self;
	
	@JsonProperty(value = "prev")
	private String prev;
}
