package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Address {

	private String address;
	private String city;
	private String countryCode;
	private String postalCode;
	
	@Builder
	public Address(
			String address,
			String city,
			String countryCode,
			String postalCode
			) {
		this.address = address;
		this.city = city;
		this.countryCode = countryCode;
		this.postalCode = postalCode;
	}
}
