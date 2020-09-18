package org.tain.object._list11.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Deprecated
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResList11Metadata {

	private int totalRecords;
	
	private int totalPages;

	private String next;
	
	private String self;
	
	private String prev;
}
