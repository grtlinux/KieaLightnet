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
	@JsonProperty(value = "name")
	private String sourceCountry = "KOR";
	private String destinationCountry = "KHM";
	private String destinationOperatorCode = "lyhour";
	private String withDrawableAmount = "1.500";
	private String transactionCurrency = "USD";
	private String deliveryMethod = "cash";
}
