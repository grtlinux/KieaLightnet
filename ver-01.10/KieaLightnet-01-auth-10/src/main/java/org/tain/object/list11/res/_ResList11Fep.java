package org.tain.object.list11.res;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResList11Fep {

	@StreamAnnotation(length = 20)
	private String name = "BATCH.LIST.RES";
	
	@StreamAnnotation(length = 20)
	private String status = "success";
	
	@StreamAnnotation(length = 10)
	private int totalRecords = 103;
	
	@StreamAnnotation(length = 10)
	private int totalPages = 21;
}
