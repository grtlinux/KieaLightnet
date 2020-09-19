package org.tain.object.detail.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResDetailSubData {

	@StreamAnnotation(length = 50)
	@JsonProperty(value = "transactionId")
	private String transactionId;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "deliveryMethod")
	private String deliveryMethod;
	
	/*
	@StreamAnnotation
	@JsonProperty(value = "source")
	private _ResHistoriesSource source = new _ResHistoriesSource();
	
	@StreamAnnotation
	@JsonProperty(value = "destination")
	private _ResHistoriesDestination destination = new _ResHistoriesDestination();
	
	@StreamAnnotation
	@JsonProperty(value = "fee")
	private _ResHistoriesFee fee = new _ResHistoriesFee();
	
	@StreamAnnotation
	@JsonProperty(value = "rate")
	private _ResHistoriesRate rate = new _ResHistoriesRate();
	
	@StreamAnnotation
	@JsonProperty(value = "sender")
	private _ResHistoriesSender sender = new _ResHistoriesSender();
	
	@StreamAnnotation
	@JsonProperty(value = "receiver")
	private _ResHistoriesReceiver receiver = new _ResHistoriesReceiver();
	
	@StreamAnnotation(length = 40)
	@JsonProperty(value = "status")
	private String status;

	@StreamAnnotation(length = 10, usable = false)
	@JsonProperty(value = "remittanceType")
	private String remittanceType;
	*/
}
