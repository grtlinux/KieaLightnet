package org.tain.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.param")
@Data
public class ProjEnvParamProperties {

	private String name;
	
	private String accessToken;
	private String commitFile;
	private String detailFile;
	private String list1File;
	private String list11File;
	private String validateFile;
}
