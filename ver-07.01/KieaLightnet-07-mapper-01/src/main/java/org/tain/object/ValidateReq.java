package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValidateReq {

	private Source source;
	private Sender sender;
	private Destination destination;
	private Receiver receiver;
	
	private String deliveryMethod;
	private String remark;
	
	private Currency fee;
	private Rate rate;
	
	private String transactionId;
	private String status;
	
	@Builder
	public ValidateReq(
			Source source,
			Sender sender,
			Destination destination,
			Receiver receiver,
			String deliveryMethod,
			String remark,
			Currency fee,
			Rate rate,
			String transactionId,
			String status
			) {
		this.source = source;
		this.sender = sender;
		this.destination = destination;
		this.receiver = receiver;
		this.deliveryMethod = deliveryMethod;
		this.remark = remark;
		this.fee = fee;
		this.rate = rate;
		this.transactionId = transactionId;
		this.status = status;
	}
}
