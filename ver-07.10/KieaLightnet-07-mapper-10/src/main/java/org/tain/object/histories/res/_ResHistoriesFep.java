package org.tain.object.histories.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResHistoriesFep {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "name")
	private String name = "BATCH.LIST.RES";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "status")
	private String status = "success";
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "totalRecords")
	private int totalRecords = 103;
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "totalPages")
	private int totalPages = 21;
}
