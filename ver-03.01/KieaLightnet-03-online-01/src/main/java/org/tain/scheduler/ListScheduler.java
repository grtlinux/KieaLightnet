package org.tain.scheduler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tain.config.SkipSSLConfig;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListScheduler {

	public static String process(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.printf("ONLINE >>>>> 1. request data: [%s]\n", request);
		}

		String response = "0402 Hello world!!! response";
		
		if (Flag.flag) {
			// req mapper process
			response = mapperHttpPostReq(request);
			System.out.printf("ONLINE >>>>> 1. response data: [%s]\n", response);
			if (Flag.flag) {
				JsonNode jsonNode = new ObjectMapper().readTree(response);
				System.out.println("ONLINE >>>>> 1. REQ JSON: " + jsonNode.toPrettyString());
			}
			
			response = linkHttpPost(response);
			System.out.printf("ONLINE >>>>> 2. response data: [%s]\n", response);
			
			// process
			response = mapperHttpPostRes(response);
			System.out.printf("ONLINE >>>>> 3. response data: [%s]\n", response);
		}
		
		if (Flag.flag) {
			System.out.printf("ONLINE >>>>> 9. response data: [%s]\n", response);
		}

		return response;
	}

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	private static String POST_MAPPER_LIST_REQ_S2J_HTTP_URL = "http://localhost:18086/v0.1/mapper/list/s2j";
	
	private static String mapperHttpPostReq(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		Map<String,String> reqMap = new HashMap<>();
		if (Flag.flag) {
			reqMap.put("title", "/mapper/list");
			reqMap.put("command", "Stream To Json");
			reqMap.put("data", request);
			System.out.println("ONLINE >>>>> reqMap: " + reqMap);
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Map<String,String>> reqHttpEntity = new HttpEntity<>(reqMap, reqHeaders);
			
			response = SkipSSLConfig.getRestTemplate(0).exchange(POST_MAPPER_LIST_REQ_S2J_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
		}
		
		if (!Flag.flag) {
			/*
			// Pretty Print
			try {
				JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
				String json = jsonNode.toPrettyString();
				System.out.println("ONLINE >>>>> response json: " + json);
				
				retResponse = jsonNode.at("/retData").toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
		}
		
		if (Flag.flag) {
			Map<String,String> map = new ObjectMapper().readValue(response.getBody(), new TypeReference<Map<String,String>>(){});
			retResponse = map.get("retData");
		}
		
		return retResponse;
	}
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	private static String POST_LINK_HTTP_URL = "http://localhost:18085/v0.1/batch/list";
	
	private static String linkHttpPost(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		if (Flag.flag) {
			System.out.println("ONLINE >>>>> request = " + request);
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(request, reqHeaders);
			
			response = SkipSSLConfig.getRestTemplate(0).exchange(POST_LINK_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
			
			retResponse = response.getBody();
		}
		
		if (!Flag.flag) {
			/*
			// Pretty Print
			try {
				JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
				String json = jsonNode.toPrettyString();
				System.out.println(">>>>> response json: " + json);
				
				retResponse = jsonNode.at("/data").toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
		}
		
		if (!Flag.flag) {
			/*
			Map<String,String> map = new ObjectMapper().readValue(response.getBody(), new TypeReference<Map<String,String>>(){});
			retResponse = map.get("retData");
			*/
		}
		
		return retResponse;
	}
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	private static String POST_MAPPER_LIST_RES_S2J_HTTP_URL = "http://localhost:18086/v0.1/mapper/list/j2s";
	
	private static String mapperHttpPostRes(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		if (Flag.flag) {
			JsonNode jsonNode = new ObjectMapper().readTree(request);
			System.out.println("ONLINE >>>>> 3. REQ JSON: " + jsonNode.toPrettyString());
		}
		
		Map<String,String> reqMap = new HashMap<>();
		if (Flag.flag) {
			reqMap.put("title", "/mapper/list");
			reqMap.put("command", "Stream To Json");
			reqMap.put("data", request);
			System.out.println("ONLINE >>>>> reqMap: " + reqMap);
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Map<String,String>> reqHttpEntity = new HttpEntity<>(reqMap, reqHeaders);
			
			response = SkipSSLConfig.getRestTemplate(0).exchange(POST_MAPPER_LIST_RES_S2J_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
		}
		
		if (Flag.flag) {
			// Pretty Print
			try {
				JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
				String json = jsonNode.toPrettyString();
				System.out.println("ONLINE >>>>> response json: " + json);
				
				retResponse = jsonNode.at("/retData").toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return retResponse;
	}
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	private String POST_BATCH_HTTP_URL = "http://localhost:18085/batch/list";
	
	public void httpPostBatch() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		String reqJson = ""
			+ "{"
			+ "\"operatorCode\": \"\","
			+ "\"offset\": \"0\","
			+ "\"limit\": \"20\""
			+ "}";
		
		ResponseEntity<String> response = null;
		
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);

			RestTemplate restTemplate = new RestTemplate();
			for (int i=0; i < 5; i++) {
				response = restTemplate.exchange(POST_BATCH_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
				
				log.info("=====================================================");
				log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
				log.info("KANG-20200623 >>>>> POST {}", POST_BATCH_HTTP_URL);
				log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
				log.info("=====================================================");
				
				if (response.getStatusCodeValue() == 200) {
					break;
				}
				
				log.info("KANG-20200618 >>>>> {} after 5sec, retry to connect.....", CurrentInfo.get());
				try { Thread.sleep(5000); } catch (InterruptedException e) {}
			}
		}
		
		if (Flag.flag) {
			// Pretty Print
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			JsonNode jsonNode = objectMapper.readTree(response.getBody());
			//String json = jsonNode.at("/").toPrettyString();
			String json = jsonNode.toPrettyString();
			System.out.println(">>>>> json: " + json);
		}
	}
}
