package org.tain.data;

public class LnsData {

	private static LnsData instance = null;
	
	public static LnsData getInstance() {
		if (instance == null) {
			instance = new LnsData();
		}
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private String accessToken = null;
	
	public synchronized void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public synchronized String getAccessToken() {
		return this.accessToken;
	}
	
	///////////////////////////////////////////////////////////////////////////
}
