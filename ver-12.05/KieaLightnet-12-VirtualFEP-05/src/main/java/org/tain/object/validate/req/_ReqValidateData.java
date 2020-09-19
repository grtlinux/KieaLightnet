package org.tain.object.validate.req;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
//@JsonIgnoreProperties(value = {"idLong", "idInt"})  // to the below
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class _ReqValidateData {

	@StreamAnnotation(length = 20)
	@JsonProperty(value = "deliveryMethod")
	private String deliveryMethod;
	
	@StreamAnnotation(length = 50)
	@JsonProperty(value = "remark")
	private String remark;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "saveReport")
	private String saveReport;
	
	// TODO: issue for meeting
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "promotionCodes")
	private String[] promotionCodes = {"FLATFEE"};
	
	@StreamAnnotation
	@JsonProperty(value = "receiver")
	private _ReqValidateReceiver receiver = new _ReqValidateReceiver();
	
	@StreamAnnotation
	@JsonProperty(value = "sender")
	private _ReqValidateSender sender = new _ReqValidateSender();
	
	@StreamAnnotation
	@JsonProperty(value = "destination")
	private _ReqValidateDestination destination = new _ReqValidateDestination();
	
	@StreamAnnotation
	@JsonProperty(value = "source")
	private _ReqValidateSource source = new _ReqValidateSource();
}
