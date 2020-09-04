package org.tain.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.job")
@Data
public class ProjEnvJobProperties {

	private String name;
	
	private String[] lightnetUrl;
	private int lightnetStartIdx;
	private String authUrl;
	private String callbackUrl;
	
	private String dummy;
}
