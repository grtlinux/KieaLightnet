package org.tain;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

public class TestJsonToMap {

	public static void main(String[] args) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = "{\"name\":\"mkyong\", \"age\":\"37\"}";
		
		// [{"age":29,"name":"mkyong"}, {"age":30,"name":"fong"}]
		// List<Person> list = Arrays.asList(objectMapper.readValue(jsonString, Person[].class));
		
		Map<String,String> map = objectMapper.readValue(jsonString, new TypeReference<Map<String,String>>(){});
		map.forEach((key,value) -> {
			System.out.printf(">>>>> [%s] => [%s]\n", key, value);
		});
	}
}

@Data
class Person {
	private String name;
	private int age;
}