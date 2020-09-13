package org.tain.object._commit2.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
//@JsonIgnoreProperties(value = {"idLong", "idInt"})  // to the below
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class _ReqCommitData {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "transactionId")
	private String transactionId = "62bbb4b8-fe03-40d2-6f30-92baf54da82d";
}
