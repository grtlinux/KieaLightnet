package org.tain.object.auth.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResAuthData {

	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "access_token")
	private String accessToken;
}
