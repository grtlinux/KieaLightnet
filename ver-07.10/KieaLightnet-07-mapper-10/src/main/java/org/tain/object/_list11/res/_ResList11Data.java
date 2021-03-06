package org.tain.object._list11.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Deprecated
@Data
//@JsonIgnoreProperties(value = {"idLong", "idInt"})  // to the below
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class _ResList11Data {

	private String status;
	
	private String message;
	
	@JsonProperty(value = "_metadata")
	private _ResList11Metadata metadata;
	
	@StreamAnnotation
	private _ResList11SubData[] data;
}
