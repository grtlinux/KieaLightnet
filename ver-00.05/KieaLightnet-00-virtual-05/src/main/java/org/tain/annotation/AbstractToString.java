package org.tain.annotation;

import java.lang.reflect.Field;

@Deprecated
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
						String value = (String) field.get(this);
						if (value == null) value = "";
						sb.append(String.format("%-" + length + "." + length + "s", value));
					} else {
						sb.append(field.get(this).toString());
					}
				} else {
					// sb.append(field.get(this));
				}
			}
		} catch (Exception e) {
		}
		
		return sb.toString();
	}
}
