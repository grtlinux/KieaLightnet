package org.tain;

import java.io.File;

import org.tain.object.CommitReq;
import org.tain.object.CommitRes;
import org.tain.utils.Flag;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestCommit {

	public static void main(String[] args) throws Exception {
		if (!Flag.flag) test00();
		if (!Flag.flag) test01();
		if (!Flag.flag) test02();
		if (!Flag.flag) test03();
		if (Flag.flag) test04();
	}

	private static void test00() throws Exception {
		if (Flag.flag) {
			// read commit_req
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/commit_req.json"));
			System.out.println("----------------------------- commit_req.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			// read commit_req2
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/commit_req2.json"));
			System.out.println("----------------------------- commit_req2.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			// read commit_res
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/commit_res.json"));
			System.out.println("----------------------------- commit_res.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			// read commit_res2
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/commit_res2.json"));
			System.out.println("----------------------------- commit_res2.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test01() throws Exception {
		if (Flag.flag) {
			// read commit_req
			System.out.println("----------------------------- commit_req.json ----------------------------");
			CommitReq commitReq = new ObjectMapper().readValue(new File("src/test/java/org/tain/commit_req.json"), CommitReq.class);
			System.out.println(">>>>> " + commitReq);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + commitReq.getTransactionId());
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
				String jsonCommitReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commitReq);
				System.out.println(">>>>> " + jsonCommitReq);
			}
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test02() throws Exception {
		if (Flag.flag) {
			// read commit_req2
			System.out.println("----------------------------- commit_req2.json ----------------------------");
			CommitReq commitReq = new ObjectMapper().readValue(new File("src/test/java/org/tain/commit_req2.json"), CommitReq.class);
			System.out.println(">>>>> " + commitReq);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + commitReq.getTransactionId());
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
				String jsonCommitReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commitReq);
				System.out.println(">>>>> " + jsonCommitReq);
			}
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test03() throws Exception {
		if (Flag.flag) {
			// read commit_res
			System.out.println("----------------------------- commit_res.json ----------------------------");
			CommitRes commitRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/commit_res.json"), CommitRes.class);
			System.out.println(">>>>> " + commitRes);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + commitRes.getData().getTransactionId());
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
				String jsonCommitRes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commitRes);
				System.out.println(">>>>> " + jsonCommitRes);
			}
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test04() throws Exception {
		if (Flag.flag) {
			// read commit_res2
			System.out.println("----------------------------- commit_res2.json ----------------------------");
			CommitRes commitRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/commit_res2.json"), CommitRes.class);
			System.out.println(">>>>> " + commitRes);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + commitRes.getData().getTransactionId());
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
				String jsonCommitRes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commitRes);
				System.out.println(">>>>> " + jsonCommitRes);
			}
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}
}
