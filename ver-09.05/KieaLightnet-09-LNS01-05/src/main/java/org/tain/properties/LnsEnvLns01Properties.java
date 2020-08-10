package org.tain.properties;

import java.sql.Timestamp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "lns-env.lns01")
@Data
public class LnsEnvLns01Properties {

	private String onlineHost;
	private Integer onlinePort;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());
}
