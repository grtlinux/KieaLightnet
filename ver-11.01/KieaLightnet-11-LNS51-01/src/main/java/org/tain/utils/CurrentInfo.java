package org.tain.utils;

public class CurrentInfo {
	
	private static String title = null;
	
	public static String get() {
		StringBuffer sb = new StringBuffer();
		StackTraceElement element = Thread.currentThread().getStackTrace()[2];
		if (CurrentInfo.title != null) sb.append("<").append(CurrentInfo.title).append("> ");
		sb.append(element.getClassName()).append('.').append(element.getMethodName()).append("()");
		//sb.append(" of ");
		//sb.append(element.getFileName());
		sb.append("#").append(element.getLineNumber());
		return sb.toString();
	}
	
	public static void setTitle(String title) {
		CurrentInfo.title = title;
	}
}
