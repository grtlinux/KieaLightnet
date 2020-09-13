package org.tain.object._commit.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResCommitDestination {

	@StreamAnnotation(length = 20)
	private String transactionId;

	@StreamAnnotation(length = 10)
	private String withdrawalId;
}
