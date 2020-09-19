package org.tain.object.commit.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class _ResCommitAdditionalInfo {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "destinationCountryDisplayName")
	private String destinationCountryDisplayName;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "receiveAmountsReceiveFeesAreEstimated")
	private String receiveAmountsReceiveFeesAreEstimated;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "receiveAmountsReceiveTaxesAreEstimated")
	private String receiveAmountsReceiveTaxesAreEstimated;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "receiveAmountsTotalReceiveTaxes")
	private String receiveAmountsTotalReceiveTaxes;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "receiveAmountsValidCurrencyIndicator")
	private String receiveAmountsValidCurrencyIndicator;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "receiveFeeDisclosureText")
	private String receiveFeeDisclosureText;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "receiveTaxDisclosureText")
	private String receiveTaxDisclosureText;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "sendAmountsSendAmount")
	private String sendAmountsSendAmount;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "sendAmountsTotalSendTaxes")
	private String sendAmountsTotalSendTaxes;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "senderPurposeDisplayName")
	private String senderPurposeDisplayName;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "totalReceiveFees")
	private String totalReceiveFees;
	
}
