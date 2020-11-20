package org.tain.data;

import org.springframework.stereotype.Component;

@Component
public class LnsData {

	private String accessToken = null;
	
	public synchronized void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public synchronized String getAccessToken() {
		return this.accessToken;
	}
}
