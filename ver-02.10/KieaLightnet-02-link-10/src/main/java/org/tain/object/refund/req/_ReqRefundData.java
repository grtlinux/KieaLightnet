package org.tain.object.refund.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqRefundData {

	@StreamAnnotation(length = 30)
	@JsonProperty(value = "terminalName")
	private String terminalName = "Testterminal-01";

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "reason")
	private String reason = "NO_RCV_LOC";

	@StreamAnnotation(length = 5)
	@JsonProperty(value = "feeRefFund")
	private String feeRefFund = "Y";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "reversalType")
	private String reversalType = "cancel";

	@StreamAnnotation(length = 30)
	@JsonProperty(value = "transactionId")
	private String transactionId = "4324682635157306";
}
