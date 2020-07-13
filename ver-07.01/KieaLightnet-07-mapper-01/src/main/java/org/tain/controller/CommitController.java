package org.tain.controller;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.object.CommitReq;
import org.tain.object.CommitRes;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/mapper/commit"})
@Slf4j
public class CommitController {

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	@PostMapping(value = {"/s2j"})
	public ResponseEntity<?> streamToJson(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println("MAPPER >>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println("MAPPER >>>>> Body = " + _httpEntity.getBody());
		}
		
		Map<String,String> map = null;
		String data = null;
		if (Flag.flag) {
			// mapping process
			ObjectMapper objectMapper = new ObjectMapper();
			map = objectMapper.readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
			
			data = map.get("data");
			System.out.println("MAPPER >>>>> data = [" + data + "]");
			
			// link process (REQ -> RES)
			//String retData = "{\"transactionId\":\"a205c886-0d7f-4b40-5de5-d1323eb57514\"}";
			String retData = requestStreamToJson(data);
			
			//map.put("retData", Convert.quote(retData));
			map.put("retData", retData);
			System.out.println("MAPPER >>>>> map: " + map);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
	
	//strCommitReq = "00610201REQ62bbb4b8-fe03-40d2-6f30-92baf54da82d              ";
	//strCommitReq = "00610201REQ62bbb4b8-fe03-40d2-6f30-92baf54da82d KIEA         ";
	
	@Value("${json.file.commitReqDummy.path}")
	private String jsonFileCommitReqDummyPath;

	private String requestStreamToJson(String data) throws Exception {
		
		CommitReq dummyCommitReq = new ObjectMapper().readValue(new File(System.getenv("HOME") + this.jsonFileCommitReqDummyPath), CommitReq.class);
		String jsonDummyCommitReq = "";
		
		if (Flag.flag) {
			System.out.printf(">>>>> (%d) [%s]\n", data.length(), data);
			
			int offset = 0;
			int size = -1;
			
			int length = -1;
			String division = null;
			String type = null;
			
			size = 4; length = Integer.valueOf(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 4; division = nvl1(data.substring(offset, offset+size).trim()); offset += size;
			size = 3; type = nvl1(data.substring(offset, offset+size).trim()); offset += size;
			
			size = 50; dummyCommitReq.setTransactionId(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			
			if (Flag.flag) {
				System.out.printf(">>>>> %d %s %s\n", length, division, type);
				
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				//String jsonDummyCommitReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dummyCommitReq);
				//System.out.println(">>>>> " + jsonDummyCommitReq);
				jsonDummyCommitReq = objectMapper.writeValueAsString(dummyCommitReq);
				//System.out.println(">>>>> " + jsonDummyCommitReq);
			}
		}
		return jsonDummyCommitReq;
	}

	private static String nvl1(String str) {
		if ("null".equals(str) || "".equals(str)) {
			return null;
		}
		return str;
	}

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	@PostMapping(value = {"/j2s"})
	public ResponseEntity<?> jsonToStream(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println("MAPPER >>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println("MAPPER >>>>> Body = " + _httpEntity.getBody());
		}
		
		Map<String,String> map = null;
		String data = null;
		if (Flag.flag) {
			// mapping process
			ObjectMapper objectMapper = new ObjectMapper();
			map = objectMapper.readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
			
			data = map.get("data");
			System.out.println("MAPPER >>>>> data = [" + data + "]");
			
			//if (Flag.flag) {
			//	JsonNode jsonNode = new ObjectMapper().readTree(data);
			//	System.out.println("MAPPER >>>>> REQ JSON: " + jsonNode.toPrettyString());
			//}
			//map.put("retData", Convert.quote("0202AAAA        1234567890  ABC1002003        Hello    "));
			
			String retData = responseJsonToStream(data);
			
			map.put("retData", retData);
			System.out.println("MAPPER >>>>> map: " + map);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
	
	//strCommitRes = "01510202RESD014017804533540              62bbb4b8-fe03-40d2-6f30-92baf54da82d              RECEIVED            success             OK                  ";
	//strCommitRes = "01510202RESD014017804533540              62bbb4b8-fe03-40d2-6f30-92baf54da82d KIEA         RECEIVED            success             OK                  ";
	
	private String responseJsonToStream(String data) throws Exception {
		
		CommitRes commitRes = new ObjectMapper().readValue(data, CommitRes.class);
		System.out.println(">>>>> " + commitRes);
		
		if (!Flag.flag) {
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
			
			if ("success".equals(commitRes.getStatus())) {
				sb.append(String.format("%-20s", nvl2(commitRes.getData().getDestination().getTransactionId())));           // data.destination.transactionId
				sb.append(String.format("%-10s", nvl2(commitRes.getData().getDestination().getWithdrawalId())));            // data.destination.withdrawalId
				sb.append(String.format("%-50s", nvl2(commitRes.getData().getTransactionId())));                            // data.transactionId
				sb.append(String.format("%-20s", nvl2(commitRes.getData().getStatus())));                                   // data.status
				
				sb.append(String.format("%-20s", nvl2(commitRes.getStatus())));                                             // data.sender.firstName
				sb.append(String.format("%-50s", nvl2(commitRes.getMessage())));                                            // data.sender.lastName
			} else {
				sb.append(String.format("%-20s", ""));   // data.destination.transactionId
				sb.append(String.format("%-10s", ""));   // data.destination.withdrawalId
				sb.append(String.format("%-50s", ""));   // data.transactionId
				sb.append(String.format("%-20s", ""));   // data.status
				
				sb.append(String.format("%-20s", nvl2(commitRes.getStatus())));                                             // data.sender.firstName
				sb.append(String.format("%-50s", nvl2(commitRes.getMessage())));                                            // data.sender.lastName
			}
			
			int length = 4 + sb.length();
			sb.insert(0, String.format("%04d", length));
			
			if (Flag.flag) System.out.printf(">>>>> (%04d) [%s]\n", sb.length(), sb.toString());
			
			strCommitRes = sb.toString();
		}
		return strCommitRes;
	}
	
	private static String nvl2(String str) {
		if (str == null || "null".equals(str)) {
			return "";
		}
		return str;
	}
}
