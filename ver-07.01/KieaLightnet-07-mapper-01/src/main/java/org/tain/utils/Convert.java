package org.tain.utils;

public class Convert {

	public static String quote(String str) {
		return str.replaceAll("\"", "\\\"");
	}
	
	public static String quote2(String str) {
		return str.replaceAll("\"", "\\\\\\\"");
	}
}
