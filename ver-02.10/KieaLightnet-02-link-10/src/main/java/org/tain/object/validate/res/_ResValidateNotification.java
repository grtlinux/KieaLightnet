package org.tain.object.validate.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResValidateNotification {

	@StreamAnnotation(length = 5)
	@JsonProperty(value = "transactionSMSOptIn")
	private boolean transactionSMSOptIn;

	@StreamAnnotation(length = 5)
	@JsonProperty(value = "transactionEmailOptIn")
	private boolean transactionEmailOptIn;
	
	@StreamAnnotation(length = 5)
	@JsonProperty(value = "marketingSMSOptIn")
	private boolean marketingSMSOptIn;

	@StreamAnnotation(length = 5)
	@JsonProperty(value = "marketingEmailOptIn")
	private boolean marketingEmailOptIn;
}
