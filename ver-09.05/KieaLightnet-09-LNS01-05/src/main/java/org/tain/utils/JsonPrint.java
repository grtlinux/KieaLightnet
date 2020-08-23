package org.tain.utils;

import java.io.File;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonPrint implements JsonPrintImpl {

	private static JsonPrint instance = null;
	
	public static JsonPrint getInstance() {
		if (instance == null) {
			instance = new JsonPrint();
		}
		return instance;
	}
	
	private JsonPrint() {
		if (this.objectMapper == null) {
			this.objectMapper = new ObjectMapper();
			this.objectMapper.registerModule(new JavaTimeModule());
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		}
	}
	
	///////////////////////////////////////////////////////
	///////////////////////////////////////////////////////
	///////////////////////////////////////////////////////
	
	private ObjectMapper objectMapper = null;
	
	@Override
	public ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}
	
	@Override
	public String toJson(Object object) {
		try {
			return this.objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}

	@Override
	public String toPrettyJson(Object object) {
		try {
			return this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}

	@Override
	public void printJson(Object object) {
		System.out.println("JSON >>>>> " + this.toJson(object));
	}

	@Override
	public void printJson(String title, Object object) {
		System.out.println(title + " JSON >>>>> " + this.toJson(object));
	}

	@Override
	public void printPrettyJson(Object object) {
		System.out.println("Pretty JSON >>>>> " + this.toPrettyJson(object));
	}

	@Override
	public void printPrettyJson(String title, Object object) {
		System.out.println(title + " Pretty JSON >>>>> " + this.toPrettyJson(object));
	}

	@Override
	public void saveJson(File file, Object object) {
		try {
			this.objectMapper.writeValue(file, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void savePrettyJson(File file, Object object) {
		try {
			this.objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
