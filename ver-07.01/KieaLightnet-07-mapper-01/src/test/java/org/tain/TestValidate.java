package org.tain;

import java.io.File;

import org.tain.object.ValidateReq;
import org.tain.object.ValidateRes;
import org.tain.utils.Flag;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestValidate {

	public static void main(String[] args) throws Exception {
		if (!Flag.flag) test00();
		if (!Flag.flag) test01();
		if (!Flag.flag) test02();
		if (!Flag.flag) test03();
		if (Flag.flag) test04();
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
			System.out.println("----------------------------- validate_req.json ----------------------------");
			ValidateReq validateReq = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_req.json"), ValidateReq.class);
			System.out.println(">>>>> " + validateReq);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + validateReq.getSender().getAddress().getPostalCode());
			}
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				//objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.CUSTOM);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
				String jsonValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateReq);
				System.out.println(">>>>> " + jsonValidateReq);
			}
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test02() throws Exception {
		if (Flag.flag) {
			// read validate_req2
			System.out.println("----------------------------- validate_req2.json ----------------------------");
			ValidateReq validateReq = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_req2.json"), ValidateReq.class);
			System.out.println(">>>>> " + validateReq);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + validateReq.getSender().getAddress().getPostalCode());
			}
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				//objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.CUSTOM);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
				String jsonValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateReq);
				System.out.println(">>>>> " + jsonValidateReq);
			}
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test03() throws Exception {
		if (Flag.flag) {
			// read validate_res
			System.out.println("----------------------------- validate_res.json ----------------------------");
			ValidateRes validateRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_res.json"), ValidateRes.class);
			System.out.println(">>>>> " + validateRes);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + validateRes.getData().getSender().getAddress().getPostalCode());
			}
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				//objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.CUSTOM);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
				String jsonValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateRes);
				System.out.println(">>>>> " + jsonValidateReq);
			}
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test04() throws Exception {
		if (Flag.flag) {
			// read validate_res2
			System.out.println("----------------------------- validate_res2.json ----------------------------");
			ValidateRes validateRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_res2.json"), ValidateRes.class);
			System.out.println(">>>>> " + validateRes);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + validateRes.getData().getSender().getAddress().getPostalCode());
			}
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				//objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.CUSTOM);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
				String jsonValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateRes);
				System.out.println(">>>>> " + jsonValidateReq);
			}
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}
}
