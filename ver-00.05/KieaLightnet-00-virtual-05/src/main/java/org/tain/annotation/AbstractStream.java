package org.tain.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractStream {

	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	
	public String getStream() {
		StringBuffer sb = new StringBuffer();
		
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (Flag.flag) log.trace("FIELD: " + field.getName());
				field.setAccessible(true);
				StreamAnnotation annotation = field.getAnnotation(StreamAnnotation.class);
				if (annotation != null && annotation.usable()) {
					int length = annotation.length();
					
					Class<?> type = field.getType();
					if (type == String.class) {
						String value = (String) field.get(this);
						if (value == null) value = "";
						if (Flag.flag) log.trace("\tString: " + value);
						sb.append(String.format("%-" + length + "." + length + "s", value));
					} else if (type == long.class) {
						long value = (long) field.get(this);
						if (Flag.flag) log.trace("\tint: " + value);
						sb.append(String.format("%" + length + "d", value));
					} else if (type == int.class) {
						int value = (int) field.get(this);
						if (Flag.flag) log.trace("\tint: " + value);
						sb.append(String.format("%" + length + "d", value));
					} else {
						Object object = field.get(this);
						Class<?> cls = object.getClass();
						if (Flag.flag) log.trace("\tCLASS: " + cls.getName());
						
						Method method = cls.getMethod("getStream");
						sb.append(method.invoke(object));
					}
				} else {
					// sb.append(field.get(this).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	
	public SubString setStream(SubString subString) {
		
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (Flag.flag) log.trace("FIELD: " + field.getName());
				field.setAccessible(true);
				StreamAnnotation annotation = field.getAnnotation(StreamAnnotation.class);
				if (annotation != null && annotation.usable()) {
					int length = annotation.length();
					
					Class<?> type = field.getType();
					if (type == String.class) {
						String value = subString.get(length).trim();
						if (Flag.flag) log.trace("\tString: [%s][%s]\n", field.getName(), value);
						field.set(this, value);
					} else if (type == long.class) {
						long value = Long.parseLong(subString.get(length).trim());
						if (Flag.flag) log.trace("\tint: [%s][%d]\n", field.getName(), value);
						field.setLong(this, value);
					} else if (type == int.class) {
						int value = Integer.parseInt(subString.get(length).trim());
						if (Flag.flag) log.trace("\tint: [%s][%d]\n", field.getName(), value);
						field.setInt(this, value);
					} else {
						Object object = field.get(this);
						Class<?> cls = object.getClass();
						if (Flag.flag) log.trace("\tCLASS: " + cls.getName());
						
						Class<?>[] param = new Class<?>[] { SubString.class };
						Method method = cls.getMethod("setStream", param);
						
						Object[] paramObject = new Object[] { subString };
						subString = (SubString) method.invoke(object, paramObject);
						
					}
				} else {
					// sb.append(field.get(this).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return subString;
	}
}
