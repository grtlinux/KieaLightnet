package org.tain.annotation;

import java.lang.reflect.Field;

public abstract class AbstractToString {

	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				StreamAnnotation annotation = field.getAnnotation(StreamAnnotation.class);
				if (annotation != null) {
					int length = annotation.length();
					
					if (field.getType() == String.class) {
						sb.append(String.format("%-" + length + "." + length + "s", field.get(this).toString()));
					}
				} else {
					//sb.append(field.get(this).toString());
				}
			}
		} catch (Exception e) {
		}
		
		return sb.toString();
	}
}
