package org.tain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestMapToJson {

	public static void main(String[] args) throws Exception {
		Map<String,Object> subMap = new HashMap<>();
		List<Map<String,Object>> list = new ArrayList<>();
		for (int i=0; i < 2; i++) {
			subMap.clear();
			subMap.put("number", i + 1);
			subMap.put("name", "kiea");
			subMap.put("age", "33");
			list.add(subMap);
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("name", "kiea");
		map.put("age", "33");
		map.put("list", list);
		
		String jsonString = new ObjectMapper().writeValueAsString(map);
		System.out.println(">>>>> " + jsonString);
		
		jsonString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
		System.out.println(">>>>> " + jsonString);
	}
}
