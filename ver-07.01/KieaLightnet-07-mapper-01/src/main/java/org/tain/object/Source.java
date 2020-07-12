package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Source {

	private String transactionId;
	private String operatorCode;
	private String country;
	
	private Currency send;
	
	@Builder
	public Source(
			String transactionId,
			String operatorCode,
			String country,
			Currency send
			) {
		this.transactionId = transactionId;
		this.operatorCode = operatorCode;
		this.country = country;
		this.send = send;
	}
}
