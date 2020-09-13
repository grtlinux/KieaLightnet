package org.tain.object.histories.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResHistoriesRate {

	@StreamAnnotation
	@JsonProperty(value = "from")
	private _ResHistoriesMoney from = new _ResHistoriesMoney();
	
	@StreamAnnotation
	@JsonProperty(value = "to")
	private _ResHistoriesMoney to = new _ResHistoriesMoney();
}
