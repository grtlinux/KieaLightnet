package org.tain.object.detail.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
//@JsonIgnoreProperties(value = {"idLong", "idInt"})  // to the below
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class _ReqDetailData {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "sourceCountry")
	private String sourceCountry;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "sourceSendCurrency")
	private String sourceSendCurrency;
	
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "destinationCountry")
	private String destinationCountry;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "destinationReceiveAmount")
	private String destinationReceiveAmount;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "destinationReceiveCurrency")
	private String destinationReceiveCurrency;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "destinationOperatorCode")
	private String destinationOperatorCode;
	
	////////////////////////////////////////////////////////////
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "withdrawalId")
	private String withdrawalId;  //  = "82409098";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "terminalName")
	private String terminalName;  //  = "terminalName";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "deliveryMethod")
	private String deliveryMethod;  //  = "cash"; "account_deposit"
}
