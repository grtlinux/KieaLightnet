package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Sender {

	private String firstName;
	private String lastName;
	private String nationalityCountryCode;
	private String idNumber;
	
	private Address address;
	private MobilePhone mobilePhone;
	
	@Builder
	public Sender(
			String firstName,
			String lastName,
			String nationalityCountryCode,
			String idNumber,
			Address address,
			MobilePhone mobilePhone
			) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalityCountryCode = nationalityCountryCode;
		this.idNumber = idNumber;
		this.address = address;
		this.mobilePhone = mobilePhone;
	}
}
