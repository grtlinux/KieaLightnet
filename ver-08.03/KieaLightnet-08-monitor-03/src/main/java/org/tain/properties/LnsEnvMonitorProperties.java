package org.tain.properties;

import java.sql.Timestamp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "lns-env.monitor.json-file")
@Data
public class LnsEnvMonitorProperties {

	private String stmtPath;
	private String kuserPath;
	private String linePath;
	
	private String adapterPath;
	private String lns01Path;
	private String linkPath;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());
	
	///////////////////////////////////////////////////////////////////
	
	public String toJson() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			return "{}";
		}
	}
	
	public String toPrettyJson() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (Exception e) {
			return "{}";
		}
	}
}
