package org.tain.object;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LnsObject {

	private String name;
	private String title;
	
	private String division;
	private String type;
	
	private String data;
	
	private String retData;
	private String retCode;
	private String retMessage;
	
	@Builder
	public LnsObject(
			String name,
			String title,
			String division,
			String type,
			String data,
			String retData,
			String retCode,
			String retMessage
			) {
		this.name = name;
		this.title = title;
		this.division = division;
		this.type = type;
		this.data = data;
		this.retData = retData;
		this.retCode = retCode;
		this.retMessage = retMessage;
	}
}
