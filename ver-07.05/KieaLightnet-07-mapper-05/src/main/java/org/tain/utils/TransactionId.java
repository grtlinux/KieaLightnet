package org.tain.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionId {

	//private static Random random = new Random(System.currentTimeMillis());
	private static int seq = 0;
	
	public synchronized static String get() {
		StringBuffer trId = new StringBuffer();
		if (Flag.flag) {
			LocalDateTime now = LocalDateTime.now();
			trId.append("HW");
			trId.append(now.format(DateTimeFormatter.ofPattern("yyMMddHHmm")));
			trId.append("A");
			trId.append(now.format(DateTimeFormatter.ofPattern("ss")));
			//trId.append(String.valueOf(random.nextInt(10)));
			trId.append(String.valueOf(++seq % 10));
		}
		
		return trId.toString();
	}
}
