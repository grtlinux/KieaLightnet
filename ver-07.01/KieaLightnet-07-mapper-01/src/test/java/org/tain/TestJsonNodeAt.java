package org.tain;

import java.io.File;

import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestJsonNodeAt {

	public static void main(String[] args) throws Exception {
		if (!Flag.flag) test01();
		if (Flag.flag) test02();
	}
	
	private static void test01() throws Exception {
		if (Flag.flag) {
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/list_res_20200624.json"));
			//System.out.println(">>>>> " + jsonNode.toPrettyString());
			System.out.println(">>>>> / = " + jsonNode.toString());

			System.out.println("--------------------------------------------------------------------");
			JsonNode node1 = jsonNode.at("/_metadata");
			System.out.println(">>>>> /_metadata = " + node1.toPrettyString());
			
			System.out.println("--------------------------------------------------------------------");
			JsonNode node2 = jsonNode.at("/data");
			System.out.println(">>>>> /data = " + node2.toPrettyString());
			
			System.out.println("--------------------------------------------------------------------");
			ArrayNode arrNode2 = (ArrayNode) node2;
			for (int i=0; i < arrNode2.size(); i++) {
				System.out.println("--------------------------------------------------------------------");
				System.out.println(">>>>> /data[" + i + "] = " + arrNode2.get(i).toPrettyString());
			}
			System.out.println("--------------------------------------------------------------------");
			JsonNode node3 = jsonNode.at("/data/10");
			System.out.println(">>>>> /data/10 = " + node3.toPrettyString());
		}
	}
	
	private static void test02() throws Exception {
		if (Flag.flag) {
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/list_res_20200624.json"));
			JsonNode metadata = jsonNode.at("/_metadata");
			String textNext = metadata.at("/next").asText();
			Long longTotalRecords = metadata.at("/totalRecords").asLong();
			String textImsi1 = metadata.at("/imsi1").asText("Default String of imis1");
			String textImsi2 = metadata.at("/imsi2").asText("Default String of imsi2");
			System.out.println(">>>>> textNext: " + textNext);
			System.out.println(">>>>> longTotalRecords: " + longTotalRecords);
			System.out.println(">>>>> textImsi1: " + textImsi1);
			System.out.println(">>>>> textImsi2: " + textImsi2);
			
			ObjectNode objMetadata = (ObjectNode) metadata;
			objMetadata.put("imsi1", "Hello, world");
			objMetadata.put("imsi2", "Bye");
			
			System.out.println(">>>>> metadata: " + metadata.toPrettyString());
		}
	}
}
