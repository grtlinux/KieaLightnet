package org.tain.properties;

import java.sql.Timestamp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "lns-env.auth")
@Data
public class LnsEnvAuthProperties {

	@JsonIgnore
	private String clientId;
	@JsonIgnore
	private String secret;
	
	private String accessToken;
	private String[] lightnetUrl;
	private int lightnetStartIdx;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());
}
