package org.tain.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.json")
@Data
public class ProjEnvJsonProperties {

	private String name;
	
	private String home;
	private String base;
	
	@Value("${proj-env.job.loading.source.path}")
	private String srcPath;
	@Value("${proj-env.job.loading.source.file}")
	private String srcFile;
	@Value("${proj-env.job.loading.target.path}")
	private String tgtPath;
	@Value("${proj-env.job.loading.target.file}")
	private String tgtFile;
	
	private String dummy;  // null
}
