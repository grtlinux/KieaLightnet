package org.tain.utils;

import java.lang.reflect.Field;

import org.tain.annotation.StreamAnnotation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransferStrAndJson {

	private static boolean flagTest = !true;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static String getStream(Object object) {
		StringBuffer sb = new StringBuffer();
		
		if (Flag.flag) {
			// simple test
		}
		
		if (Flag.flag) {
			try {
				Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					//log.trace("FIELD: '{}'", field.getName());
					field.setAccessible(true);
					
					StreamAnnotation annotation = field.getAnnotation(StreamAnnotation.class);
					if (annotation != null && annotation.usable()) {
						int length = annotation.length();
						
						Class<?> type = field.getType();
						if (type == String.class) {
							String value = (String) field.get(object);
							if (value == null) value = "";
							log.trace("\tFIELD: '{}', String: '{}', length: {}", field.getName(), value, length);
							sb.append(String.format("%-" + length + "." + length + "s", value));
						} else if (type == long.class) {
							long value = (long) field.get(object);
							log.trace("\tFIELD: '{}', long: {}, length: {}", field.getName(), value, length);
							sb.append(String.format("%" + length + "." + length + "s", String.valueOf(value)));
						} else if (type == int.class) {
							int value = (int) field.get(object);
							log.trace("\tFIELD: '{}', int: {}, length: {}", field.getName(), value, length);
							sb.append(String.format("%" + length + "." + length + "s", String.valueOf(value)));
						} else if (type == double.class) {
							// TO DO: to fixed for scale precision
							double value = (double) field.get(object);
							log.trace("\tFIELD: '{}', double: {}, length: {}", field.getName(), value, length);
							sb.append(String.format("%" + length + "f", value));
							//sb.append(String.format("%" + length + "." + length + "s", String.valueOf(value)));
						} else if (type == float.class) {
							// TO DO: to fixed for scale precision
							float value = (float) field.get(object);
							log.trace("\tFIELD: '{}', float: {}, length: {}", field.getName(), value, length);
							sb.append(String.format("%" + length + "." + length + "s", String.valueOf(value)));
						} else {
							Object obj = field.get(object);
							sb.append(TransferStrAndJson.getStream(obj));
							// continue;
						}
					} else {
						// sb.append(field.get(this).toString());
					}
					if (flagTest) sb.append('|');
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (flagTest) sb.deleteCharAt(sb.length() - 1);
		
		return sb.toString();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static SubString subString = null;
	
	public static Object getObject(Object object) {
		
		if (Flag.flag) {
			// simple test
		}
		
		if (Flag.flag) {
			try {
				Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					//log.trace("FIELD: '{}'", field.getName());
					field.setAccessible(true);
					
					StreamAnnotation annotation = field.getAnnotation(StreamAnnotation.class);
					if (annotation != null && annotation.usable()) {
						int length = annotation.length();
						//boolean useNull = annotation.useNull();
						boolean useNullSpace = annotation.useNullSpace();
						
						Class<?> type = field.getType();
						if (type == String.class) {
							String value = subString.get(length).trim();
							log.trace("\tFIELD: '{}', String: '{}', length: {}", field.getName(), value, length);
							if ("".equals(value) && useNullSpace == false)
								value = null;
							field.set(object, value);
						} else if (type == long.class) {
							long value = Long.parseLong(subString.get(length).trim());
							log.trace("\tFIELD: '{}', long: {}, length: {}", field.getName(), value, length);
							field.setLong(object, value);
						} else if (type == int.class) {
							int value = Integer.parseInt(subString.get(length).trim());
							log.trace("\tFIELD: '{}', int: {}, length: {}", field.getName(), value, length);
							field.setInt(object, value);
						} else if (type == double.class) {
							double value = Double.parseDouble(subString.get(length).trim());
							log.trace("\tFIELD: '{}', double: {}, length: {}", field.getName(), value, length);
							field.setDouble(object, value);
						} else if (type == float.class) {
							float value = Float.parseFloat(subString.get(length).trim());
							log.trace("\tFIELD: '{}', float: {}, length: {}", field.getName(), value, length);
							field.setFloat(object, value);
						} else {
							Object obj = field.get(object);
							obj = TransferStrAndJson.getObject(obj);
							field.set(object, obj);
						}
					} else {
						//
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return object;
	}
}
