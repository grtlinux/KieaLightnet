package org.tain;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestMapToJson {

	public static void main(String[] args) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String,String> map = new HashMap<>();
		map.put("name", "kiea");
		map.put("age", "33");
		
		String jsonString = objectMapper.writeValueAsString(map);
		System.out.println(">>>>> " + jsonString);
		
		jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		System.out.println(">>>>> " + jsonString);
	}
}
