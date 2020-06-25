package org.tain.utils;

public class Sleep {

	public static void run(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// no response
		}
	}
}
