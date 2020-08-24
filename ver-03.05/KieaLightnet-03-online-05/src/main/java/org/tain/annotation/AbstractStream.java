package org.tain.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.tain.utils.Flag;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractStream {

	/*
	 * string (<-), number(->)
	 * ignore field
	 * null field
	 * null = ""
	 * 
	 */
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	
	@JsonIgnore
	//@JsonProperty
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
	
	@JsonIgnore
	//@JsonProperty
	public SubString getObject(SubString subString) {
		
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (Flag.flag) log.trace("FIELD: " + field.getName());
				field.setAccessible(true);
				StreamAnnotation annotation = field.getAnnotation(StreamAnnotation.class);
				if (annotation != null && annotation.usable()) {
					int length = annotation.length();
					//boolean useNull = annotation.useNull();
					boolean useNullSpace = annotation.useNullSpace();
					
					Class<?> type = field.getType();
					if (type == String.class) {
						String value = subString.get(length).trim();
						if (Flag.flag) log.trace("\tString: [%s][%s]\n", field.getName(), value);
						if (value.equals("") && useNullSpace == false)
							value = null;
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
						Method method = cls.getMethod("getObject", param);
						Object[] paramObject = new Object[] { subString };
						
						//subString.setFlagNull(false);
						subString = (SubString) method.invoke(object, paramObject);
						//if (subString.getFlagNull() == true) {
						//	field.set(this, null);
						//	//subString.setFlagNull(false);
						//}
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
