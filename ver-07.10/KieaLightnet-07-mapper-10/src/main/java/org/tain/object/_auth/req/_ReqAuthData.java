package org.tain.object._auth.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Deprecated
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ReqAuthData {

	@JsonProperty(value = "clientId")
	private String clientId;

	@JsonProperty(value = "secret")
	private String secret;
}
