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
	private String sourceCountry = "KOR";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "destinationCountry")
	private String destinationCountry = "KHM";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "destinationOperatorCode")
	private String destinationOperatorCode = "lyhour";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "withDrawableAmount")
	private String withDrawableAmount = "1.500";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "transactionCurrency")
	private String transactionCurrency = "USD";
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "deliveryMethod")
	private String deliveryMethod = "cash";
}
