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
		if (Flag.flag) test02();
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
		if (!Flag.flag) {
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
		
		if (Flag.flag) {
			System.out.println("----------------------------- commit_req2.json ----------------------------");
			CommitReq commitReq = new ObjectMapper().readValue(new File("src/test/java/org/tain/commit_req2.json"), CommitReq.class);
			System.out.println(">>>>> " + commitReq);
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				String jsonCommitReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commitReq);
				System.out.println(">>>>> " + jsonCommitReq);
			}
			
			String strCommitReq = null;
			if (Flag.flag) {
				StringBuffer sb = new StringBuffer();
				
				sb.append(String.format("0201"));
				sb.append(String.format("REQ"));
				
				sb.append(String.format("%-50s", nvl(commitReq.getTransactionId())));                            // data.transactionId
				
				int length = 4 + sb.length();
				sb.insert(0, String.format("%04d", length));
				
				if (Flag.flag) System.out.printf(">>>>> (%04d) [%s]\n", sb.length(), sb.toString());
				
				strCommitReq = sb.toString();
			}
			
			//strCommitReq = "00610201REQ62bbb4b8-fe03-40d2-6f30-92baf54da82d              ";
			//strCommitReq = "00610201REQ62bbb4b8-fe03-40d2-6f30-92baf54da82d KIEA         ";
			
			CommitReq dummyCommitReq = new ObjectMapper().readValue(new File("src/test/java/org/tain/commit_req_dummy.json"), CommitReq.class);
			if (Flag.flag) {
				System.out.printf(">>>>> (%d) [%s]\n", strCommitReq.length(), strCommitReq);
				
				int offset = 0;
				int size = -1;
				
				int length = -1;
				String division = null;
				String type = null;
				
				size = 4; length = Integer.valueOf(nvl(strCommitReq.substring(offset, offset+size).trim())); offset += size;
				size = 4; division = nvl(strCommitReq.substring(offset, offset+size).trim()); offset += size;
				size = 3; type = nvl(strCommitReq.substring(offset, offset+size).trim()); offset += size;
				
				size = 50; dummyCommitReq.setTransactionId(nvl(strCommitReq.substring(offset, offset+size).trim())); offset += size;
				
				if (Flag.flag) {
					System.out.printf(">>>>> %d %s %s\n", length, division, type);
					
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
					String jsonDummyCommitReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dummyCommitReq);
					System.out.println(">>>>> " + jsonDummyCommitReq);
				}
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
		if (!Flag.flag) {
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
		
		if (Flag.flag) {
			System.out.println("----------------------------- commit_res2.json ----------------------------");
			CommitRes commitRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/commit_res2.json"), CommitRes.class);
			System.out.println(">>>>> " + commitRes);
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				String jsonCommitRes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commitRes);
				System.out.println(">>>>> " + jsonCommitRes);
			}
			
			String strCommitRes = null;
			if (Flag.flag) {
				StringBuffer sb = new StringBuffer();
				
				sb.append(String.format("0202"));
				sb.append(String.format("RES"));
				
				sb.append(String.format("%-20s", nvl(commitRes.getData().getDestination().getTransactionId())));           // data.destination.transactionId
				sb.append(String.format("%-10s", nvl(commitRes.getData().getDestination().getWithdrawalId())));            // data.destination.withdrawalId
				sb.append(String.format("%-50s", nvl(commitRes.getData().getTransactionId())));                            // data.transactionId
				sb.append(String.format("%-20s", nvl(commitRes.getData().getStatus())));                                   // data.status
				
				sb.append(String.format("%-20s", nvl(commitRes.getStatus())));                                             // data.sender.firstName
				sb.append(String.format("%-20s", nvl(commitRes.getMessage())));                                            // data.sender.lastName
				
				int length = 4 + sb.length();
				sb.insert(0, String.format("%04d", length));
				
				if (Flag.flag) System.out.printf(">>>>> (%04d) [%s]\n", sb.length(), sb.toString());
				
				strCommitRes = sb.toString();
			}
			
			//strCommitRes = "01510202RESD014017804533540              62bbb4b8-fe03-40d2-6f30-92baf54da82d              RECEIVED            success             OK                  ";
			//strCommitRes = "01510202RESD014017804533540              62bbb4b8-fe03-40d2-6f30-92baf54da82d KIEA         RECEIVED            success             OK                  ";
			
			CommitRes dummyCommitRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/commit_res_dummy.json"), CommitRes.class);
			if (Flag.flag) {
				System.out.printf(">>>>> (%d) [%s]\n", strCommitRes.length(), strCommitRes);
				
				int offset = 0;
				int size = -1;
				
				int length = -1;
				String division = null;
				String type = null;
				
				size = 4; length = Integer.valueOf(nvl(strCommitRes.substring(offset, offset+size).trim())); offset += size;
				size = 4; division = nvl(strCommitRes.substring(offset, offset+size).trim()); offset += size;
				size = 3; type = nvl(strCommitRes.substring(offset, offset+size).trim()); offset += size;
				
				size = 20; dummyCommitRes.getData().getDestination().setTransactionId(nvl(strCommitRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyCommitRes.getData().getDestination().setWithdrawalId(nvl(strCommitRes.substring(offset, offset+size).trim())); offset += size;
				size = 50; dummyCommitRes.getData().setTransactionId(nvl(strCommitRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyCommitRes.getData().setStatus(nvl(strCommitRes.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyCommitRes.setStatus(nvl(strCommitRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyCommitRes.setMessage(nvl(strCommitRes.substring(offset, offset+size).trim())); offset += size;
				
				if (Flag.flag) {
					System.out.printf(">>>>> %d %s %s\n", length, division, type);
					
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
					String jsonDummyCommitRes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dummyCommitRes);
					System.out.println(">>>>> " + jsonDummyCommitRes);
				}
			}
		}
		
		System.out.println("--------------------------- E N D -------------------------------");
	}
	
	private static String nvl(String str) {
		if (str == null || "null".equals(str)) {
			return "";
		} else if ("".equals(str)) {
			return null;
		}
		return str;
	}
}
