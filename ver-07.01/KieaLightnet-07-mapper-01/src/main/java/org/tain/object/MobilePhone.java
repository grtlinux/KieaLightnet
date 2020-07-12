package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MobilePhone {

	private String number;
	private String countryCode;
	
	@Builder
	public MobilePhone(
			String number,
			String countryCode
			) {
		this.number = number;
		this.countryCode = countryCode;
	}
}
