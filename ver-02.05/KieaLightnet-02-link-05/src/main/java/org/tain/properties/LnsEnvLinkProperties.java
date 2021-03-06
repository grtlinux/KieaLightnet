package org.tain.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "lns-env.link")
@Data
public class LnsEnvLinkProperties {

	private String[] lightnetUrl;
	private int lightnetStartIdx;
	private String authUrl;
	private String callbackUrl;
}
