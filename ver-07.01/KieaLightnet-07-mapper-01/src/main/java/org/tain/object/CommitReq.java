package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommitReq {

	private Destination destination;
	
	private String transactionId;
	private String status;
	
	@Builder
	public CommitReq(
			Destination destination,
			String transactionId,
			String status
			) {
		this.destination = destination;
		this.transactionId = transactionId;
		this.status = status;
	}
}
