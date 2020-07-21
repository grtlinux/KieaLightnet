package org.tain.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "lns-env.auth")
@Data
public class LnsEnvAuthProperties {

	private String accessToken;
	private String lightnetUrl;
}
