package org.tain.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class StringTools {

	// text.replaceAll("(?<=^.{47}).*$", "...");

	public static String truncate(String line, int maxLength) {
		if (line.length() < maxLength)
			return line;
		int pos = line.lastIndexOf(" ", maxLength - 3);
		if (pos <= 0)
			pos = maxLength - 3; // no spaces, so just cut anyway
		return line.substring(0, pos) + "...";
	}

	public static String smartSubstring(String str, int maxLength) {
		String subStr = str.substring(0);
		if (maxLength == 0) {
			return "";
		} else if (str.length() <= maxLength) {
			return str;
		} else {
			int i = maxLength;
			while (i >= 0) {
				while (str.length() < i) {
					i--;
				}
				if (str.charAt(i) == ' ') {
					subStr = str.substring(0, i);
					break;
				}
				i--;
			}
			return subStr;
		}
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private final static String NON_THIN = "[^iIl1\\.,']";

	private static int textWidth(String str) {
		return (int) (str.length() - str.replaceAll(NON_THIN, "").length() / 2);
	}

	public static String ellipsize(String text, int max) {

		if (textWidth(text) <= max)
			return text;

		// Start by chopping off at the word before max
		// This is an over-approximation due to thin-characters...
		int end = text.lastIndexOf(' ', max - 3);

		// Just one long word. Chop it off.
		if (end == -1)
			return text.substring(0, max - 3) + "...";

		// Step forward as long as textWidth allows.
		int newEnd = end;
		do {
			end = newEnd;
			newEnd = text.indexOf(' ', end + 1);

			// No more spaces.
			if (newEnd == -1)
				newEnd = text.length();

		} while (textWidth(text.substring(0, newEnd) + "...") < max);

		return text.substring(0, end) + "...";
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	// KANG-20200918
	public static String stringFromFile(String filePath) {
		StringBuffer sb = new StringBuffer();
		if (Flag.flag) {
			BufferedReader br = null;
			
			try {
				br = new BufferedReader(new FileReader(filePath));
				String line = null;
				while ((line = br.readLine()) != null) {
					line = line.trim();
					if (line.length() == 0 || line.charAt(0) == '#')
						continue;
					sb.append(line).append("\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) try { br.close(); } catch (Exception e) {}
			}
		}
		return sb.toString();
	}
	
	// KANG-20200918
	public static void stringToFile(String strSource, String filePath) {
		if (Flag.flag) {
			PrintWriter pw = null;
			
			try {
				pw = new PrintWriter(new FileWriter(filePath));
				pw.print(strSource);
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (pw != null) try { pw.close(); } catch (Exception e) {}
			}
		}
	}
}
