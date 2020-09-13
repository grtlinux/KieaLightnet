package org.tain.object._commit.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
//@JsonIgnoreProperties(value = {"idLong", "idInt"})  // to the below
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class _ResCommitData {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "status")
	private String status = "success";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "message")
	private String message = "OK";
	
	@StreamAnnotation
	@JsonProperty(value = "data")
	private _ResCommitSubData data = new _ResCommitSubData();
}
