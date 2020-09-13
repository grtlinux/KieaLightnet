package org.tain.object.detail.res;

import java.util.ArrayList;
import java.util.List;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
//@JsonIgnoreProperties(value = {"idLong", "idInt"})  // to the below
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class _ResDetailData {

	@StreamAnnotation(length = 50)
	@JsonProperty(value = "status")
	private String status;
	
	@StreamAnnotation(length = 50)
	@JsonProperty(value = "message")
	private String message;
	
	@StreamAnnotation
	@JsonProperty(value = "data")
	private List<_ResDetailSubData> data = new ArrayList<>();
}
