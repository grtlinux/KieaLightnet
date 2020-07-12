package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Rate {

	private Currency from;
	private Currency to;
	
	@Builder
	public Rate(
			Currency from,
			Currency to
			) {
		this.from = from;
		this.to = to;
	}
}
