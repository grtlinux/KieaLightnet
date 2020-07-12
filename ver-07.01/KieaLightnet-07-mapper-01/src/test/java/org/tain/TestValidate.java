package org.tain;

import java.io.File;

import org.tain.object.ValidateReq;
import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestValidate {

	public static void main(String[] args) throws Exception {
		if (!Flag.flag) test00();
		if (Flag.flag) test01();
		if (!Flag.flag) test02();
		if (!Flag.flag) test03();
		if (!Flag.flag) test04();
	}

	private static void test00() throws Exception {
		if (Flag.flag) {
			// read validate_req
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/validate_req.json"));
			System.out.println("----------------------------- validate_req.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			// read validate_req2
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/validate_req2.json"));
			System.out.println("----------------------------- validate_req2.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			// read validate_res
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/validate_res.json"));
			System.out.println("----------------------------- validate_res.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			// read validate_res2
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/validate_res2.json"));
			System.out.println("----------------------------- validate_res2.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test01() throws Exception {
		if (Flag.flag) {
			// read validate_req
			ValidateReq validateReq = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_req.json"), ValidateReq.class);
			System.out.println(">>>>> " + validateReq);
		}
	}

	private static void test02() throws Exception {
		if (Flag.flag) {
			// read validate_req2
			
		}
	}

	private static void test03() throws Exception {
		if (Flag.flag) {
			// read validate_res
		}
	}

	private static void test04() throws Exception {
		if (Flag.flag) {
			// read validate_res2
			
		}
	}
}
