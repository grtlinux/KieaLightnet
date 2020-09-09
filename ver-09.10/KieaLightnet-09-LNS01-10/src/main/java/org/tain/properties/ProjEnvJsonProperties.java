package org.tain.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.json")
@Data
public class ProjEnvJsonProperties {

	private String name;
	
	private String trxName;
	
	private Map<String,String> file;
}
