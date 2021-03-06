package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValidateRes {

	private ValidateReq data;
	
	private String status;
	private String message;
	
	@Builder
	public ValidateRes(
			ValidateReq data,
			String status,
			String message
			) {
		this.data = data;
		this.status = status;
		this.message = message;
	}
}
