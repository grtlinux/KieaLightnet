package org.tain.object.commit.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResCommitSubData {

	@StreamAnnotation(length = 50)
	@JsonProperty(value = "transactionId")
	private String transactionId;
	
	@StreamAnnotation(length = 40)
	@JsonProperty(value = "status")
	private String status;
	
	@StreamAnnotation
	@JsonProperty(value = "additionalInfo")
	private _ResCommitAdditionalInfo additionalInfo = new _ResCommitAdditionalInfo();
	
	@StreamAnnotation
	@JsonProperty(value = "destination")
	private _ResCommitDestination destination = new _ResCommitDestination();
	
	@StreamAnnotation
	@JsonProperty(value = "source")
	private _ResCommitSource source = new _ResCommitSource();
	
	@StreamAnnotation
	@JsonProperty(value = "sender")
	private _ResCommitSender sender = new _ResCommitSender();
	
	@StreamAnnotation
	@JsonProperty(value = "receiver")
	private _ResCommitReceiver receiver = new _ResCommitReceiver();
	
	@StreamAnnotation
	@JsonProperty(value = "fee")
	private _ResCommitFee fee = new _ResCommitFee();
	
	@StreamAnnotation
	@JsonProperty(value = "rate")
	private _ResCommitRate rate = new _ResCommitRate();
	
	@StreamAnnotation
	@JsonProperty(value = "promotions")
	private _ResCommitPromotion[] promotions;
}
