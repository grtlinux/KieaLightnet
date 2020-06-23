package org.tain.data;

public class AccessToken {

	private static String accessToken = null;
	
	public static void set(String accessToken) {
		AccessToken.accessToken = accessToken;
	}
	
	public static String get() {
		return AccessToken.accessToken;
	}
}
