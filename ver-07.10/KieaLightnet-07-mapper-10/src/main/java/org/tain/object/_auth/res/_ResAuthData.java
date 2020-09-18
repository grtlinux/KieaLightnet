package org.tain.object._auth.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Deprecated
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResAuthData {

	@JsonProperty(value = "status")
	private String status;
	
	@JsonProperty(value = "message")
	private String message;
	
	@JsonProperty(value = "accessToken")
	private String accessToken;
}
