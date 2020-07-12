package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommitRes {

	private CommitReq data;
	
	private String status;
	private String message;
	
	@Builder
	public CommitRes(
			CommitReq data,
			String status,
			String message
			) {
		this.data = data;
		this.status = status;
		this.message = message;
	}
}
