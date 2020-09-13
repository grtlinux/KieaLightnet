package org.tain.object.commit.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResCommitRate {

	@StreamAnnotation
	private _ResCommitMoney from = new _ResCommitMoney();
	
	@StreamAnnotation
	private _ResCommitMoney to = new _ResCommitMoney();
}
