package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqValidateNotification {

	@StreamAnnotation(length = 5)
	@JsonProperty(value = "transactionSMSOptIn")
	private boolean transactionSMSOptIn = false;

	@StreamAnnotation(length = 5)
	@JsonProperty(value = "transactionEmailOptIn")
	private boolean transactionEmailOptIn = false;
	
	@StreamAnnotation(length = 5)
	@JsonProperty(value = "marketingSMSOptIn")
	private boolean marketingSMSOptIn = false;

	@StreamAnnotation(length = 5)
	@JsonProperty(value = "marketingEmailOptIn")
	private boolean marketingEmailOptIn = false;
}
