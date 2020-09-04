package org.tain.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.base")
@Data
public class ProjEnvBaseProperties {

	private String name;
	
	private String program;
	private String version;
	private String comment;
}
