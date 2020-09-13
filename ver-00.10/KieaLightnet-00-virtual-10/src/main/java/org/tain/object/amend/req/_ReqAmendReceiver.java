package org.tain.object.amend.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqAmendReceiver {

	@StreamAnnotation(length = 30)
	@JsonProperty(value = "firstName")
	private String firstName = "TestFirstName";
	
	@StreamAnnotation(length = 30)
	@JsonProperty(value = "middleName")
	private String middleName = "TestMiddleName";
	
	@StreamAnnotation(length = 30)
	@JsonProperty(value = "lastName")
	private String lastName = "TestLastName";
	
	@StreamAnnotation(length = 30)
	@JsonProperty(value = "secondLastName")
	private String secondLastName = "TestSecondLastName";
}
