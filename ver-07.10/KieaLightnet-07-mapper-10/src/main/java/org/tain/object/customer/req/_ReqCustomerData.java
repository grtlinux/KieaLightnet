package org.tain.object.customer.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqCustomerData {

	@StreamAnnotation(length = 10)
	@JsonProperty(value = "destinationOperatorCode")
	private String destinationOperatorCode;

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "senderHomePhone")
	private String senderHomePhone;

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "senderCardNumber")
	private String senderCardNumber;
}
