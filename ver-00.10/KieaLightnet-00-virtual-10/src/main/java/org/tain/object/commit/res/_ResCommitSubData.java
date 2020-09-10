package org.tain.object.commit.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResCommitSubData {

	@StreamAnnotation(length = 50)
	private String transactionId;
	
	@StreamAnnotation
	private _ResCommitDestination destination = new _ResCommitDestination();
	
	@StreamAnnotation(length = 40)
	private String status;
}
