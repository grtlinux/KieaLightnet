package org.tain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import org.tain.utils.Flag;

public class TestTimestamp {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
	
	/*
	 * LocalDateTime
	 * LocalDate
	 * LocalTime
	 * Timestamp
	 * Date
	 * Time
	 * Calendar
	 * SimpleDateFormat
	 */
	public static void main(String[] args) throws Exception {
		
		if (Flag.flag) {
			// method 1
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println("1 >>>>> " + timestamp);
			
			// return the number of milliseconds since January 1, 1970, 00:00:00 GMT
			System.out.println("2 >>>>> " + timestamp.getTime());
			
			// format timestamp
			System.out.println("3 >>>>> " + sdf.format(timestamp));
		}
		if (Flag.flag) {
			// method 2 - via Date
			Date date = new Date();
			System.out.println("4 >>>>> " + new Timestamp(date.getTime()));
		}
		if (Flag.flag) {
			// Calendar
			Calendar cal = Calendar.getInstance();
			long timestamp = cal.getTimeInMillis();
			System.out.println("5 >>>>> " + timestamp);
		}
		if (Flag.flag) {
			long epoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse("01/01/1970 00:00:01").getTime() / 1000;
			String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (epoch*1000));
			System.out.println("6 >>>>> " + epoch + ", " + date);
		}
		
		if (Flag.flag) {
			//Timestamp timestamp = resultSet.getTimestamp("time_column");
			
			LocalDateTime localDateTime = LocalDateTime.now();
			System.out.println("7 >>>>> " + localDateTime);

			// LocalDateTime -> Timestamp로 변경
			//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Timestamp timestamp2 = Timestamp.valueOf(localDateTime);
			System.out.println("8 >>>>> " + timestamp2);

			// 1970/01/01 00:00:00 GMT 부터 millisecond로 계산한 시간 출력
			System.out.println(timestamp2.getTime());

			// Timestamp -> LocalDateTime으로 변경
			LocalDateTime localDateTime1 = timestamp2.toLocalDateTime();
			LocalDate localDate1 = localDateTime1.toLocalDate();
			LocalTime localTime1 = localDateTime1.toLocalTime();
			System.out.println("9 >>>>> " + localDateTime1);
			System.out.println("9 >>>>> " + localDate1);
			System.out.println("9 >>>>> " + localTime1);
		}
		
		if (Flag.flag) {
			LocalDate localDate = LocalDate.now();
			System.out.println("10 >>>>> " + localDate);

			// LocalDate -> Timestamp 로 변경
			Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
			System.out.println("11 >>>>> " + timestamp);
			System.out.println("12 >>>>> " + timestamp.getTime());

			// Timestamp -> LocalDate로 변경
			LocalDate localDate1 = timestamp.toLocalDateTime().toLocalDate();
			LocalTime localTime1 = timestamp.toLocalDateTime().toLocalTime();
			System.out.println("13 >>>>> " + localDate1);
			System.out.println("13 >>>>> " + localTime1);
		}
		
		if (Flag.flag) {
			//  LocalDate to Timestamp
			LocalDate now = LocalDate.now();
			Timestamp timestamp = Timestamp.valueOf(now.atStartOfDay());

			System.out.println("14 >>>>> " + now);		// 2019-06-14
			System.out.println("15 >>>>> " + timestamp);  // 2019-06-14 00:00:00.0

			//  Timestamp to LocalDate
			LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
			LocalTime localTime = timestamp.toLocalDateTime().toLocalTime();
			System.out.println("16 >>>>> " + localDate);  // 2019-06-14
			System.out.println("17 >>>>> " + localTime);  // 12:34
		}
	}
}
