package org.tain.object.customers.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqCustomersData {

	@StreamAnnotation(length = 10)
	@JsonProperty(value = "destinationOperatorCode")
	private String destinationOperatorCode = "mgi";

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "senderHomePhone")
	private String senderHomePhone = "45645645666";

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "senderCardNumber")
	private String senderCardNumber;  // = "204515304846";
}
