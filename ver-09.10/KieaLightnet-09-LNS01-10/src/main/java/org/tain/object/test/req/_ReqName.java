package org.tain.object.test.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqName {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "first_name")
	private String firstName;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "middle_name")
	private String middleName;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "last_name")
	private String lastName;
	
	@StreamAnnotation(length = 5)
	@JsonProperty(value = "sex")
	private String sex;
}
