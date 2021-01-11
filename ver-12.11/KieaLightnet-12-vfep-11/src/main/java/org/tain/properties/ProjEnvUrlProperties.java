package org.tain.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.url")
@Data
public class ProjEnvUrlProperties {

	private String name;  // default
	
	private String lightnet;
	
	private String online;
	
	private String mapper;
	
	private String lns01;
	
	private String dummy;  // null
}
