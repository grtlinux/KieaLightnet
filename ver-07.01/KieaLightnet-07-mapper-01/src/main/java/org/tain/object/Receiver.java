package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Receiver {

	private String firstName;
	private String lastName;
	private String bankCode;
	private String accountId;
	
	@Builder
	public Receiver(
			String firstName,
			String lastName,
			String bankCode,
			String accountId
			) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.bankCode = bankCode;
		this.accountId = accountId;
	}
}
