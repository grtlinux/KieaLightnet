package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValidateRes {

	private String status;
	private String message;
	
	private ValidateReq data;
	
	@Builder
	public ValidateRes(
			String status,
			String message,
			ValidateReq data
			) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
}
