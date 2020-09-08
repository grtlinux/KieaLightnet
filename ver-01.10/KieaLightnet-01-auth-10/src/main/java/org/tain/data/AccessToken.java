package org.tain.data;

public class AccessToken {

	private static String accessToken = null;
	
	public synchronized static void set(String accessToken) {
		AccessToken.accessToken = accessToken;
	}
	
	public synchronized static String get() {
		return AccessToken.accessToken;
	}
}
