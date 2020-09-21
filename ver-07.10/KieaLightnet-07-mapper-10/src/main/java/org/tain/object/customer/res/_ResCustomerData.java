package org.tain.object.customer.res;

import java.util.ArrayList;
import java.util.List;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResCustomerData {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "status")
	private String status = "success";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "message")
	private String message = "OK";
	
	@StreamAnnotation
	@JsonProperty(value = "data")
	private List<_ResCustomerSubData> data = new ArrayList<>();
}
