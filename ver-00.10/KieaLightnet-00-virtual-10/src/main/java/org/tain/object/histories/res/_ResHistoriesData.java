package org.tain.object.histories.res;

import java.util.ArrayList;
import java.util.List;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResHistoriesData {

	@StreamAnnotation(length = 50)
	@JsonProperty(value = "status")
	private String status;
	
	@StreamAnnotation(length = 50)
	@JsonProperty(value = "message")
	private String message;
	
	@JsonProperty(value = "_metadata")
	private _ResHistoriesMetadata metadata;
	
	@StreamAnnotation
	@JsonProperty(value = "data")
	private List<_ResHistoriesSubData> data = new ArrayList<>();
}
