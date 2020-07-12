package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Destination {

	private String transactionId;
	private String operatorCode;
	private String country;
	private String withdrawalId;
	
	private Currency receive;
	
	@Builder
	public Destination(
			String transactionId,
			String operatorCode,
			String country,
			String withdrawalId,
			Currency receive
			) {
		this.transactionId = transactionId;
		this.operatorCode = operatorCode;
		this.country = country;
		this.withdrawalId = withdrawalId;
		this.receive = receive;
	}
}
