package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Currency {

	private String amount;
	private String currency;
	private String model;
	
	@Builder
	public Currency(
			String amount,
			String currency,
			String model
			) {
		this.amount = amount;
		this.currency = currency;
		this.model = model;
	}
}
