package org.tain;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class TestJsonNodeAt {

	public static void main(String[] args) throws Exception {
		
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
