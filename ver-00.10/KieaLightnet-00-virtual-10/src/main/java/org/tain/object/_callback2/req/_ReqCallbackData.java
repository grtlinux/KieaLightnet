package org.tain.object._callback2.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Deprecated
@Data
//@JsonIgnoreProperties(value = {"idLong", "idInt"})  // to the below
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class _ReqCallbackData {

	private String counterpartyTransactionId = "82622468196393";
	
	private String reason = "remit completed";
	
	private String sourceOperatorCode = "airpay";
	
	private String status = "RECEIVED";
	
	private String transactionId = "702c74a5-ecea-4545-ae23-9afa9d8b73d1";
}
