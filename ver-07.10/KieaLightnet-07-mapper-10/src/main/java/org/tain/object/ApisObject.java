package org.tain.object;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApisObject {

	private String name;
	private String mapping;
	private String httpUrl;
	private String httpMethod;
	private String reqJson;
	private String resJson;
	private String batData;
}
