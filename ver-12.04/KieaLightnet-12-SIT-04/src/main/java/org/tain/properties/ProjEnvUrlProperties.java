package org.tain.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.url")
@Data
public class ProjEnvUrlProperties {

	private String name;
	
	private String localhost;
	private String lightnet;
	private String lightnet1;
	private String lightnet11;
	private String auth;
	private String link;
	private String mapper;
}
