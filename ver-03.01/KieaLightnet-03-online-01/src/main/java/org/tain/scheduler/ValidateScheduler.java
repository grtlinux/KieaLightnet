package org.tain.scheduler;

import java.time.LocalDateTime;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tain.config.SkipSSLConfig;
import org.tain.utils.Convert;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateScheduler {

	public static String process(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", request);

		String response = null;
		
		if (Flag.flag) {
			// REQ mapper process
			response = mapperHttpPostReq(request);
			System.out.println(">>>>> 1. response data: " + response);
			
			// link process (REQ -> RES)
			if (Flag.flag) response = "{\"receiver\":{\"firstName\":\"SOPIDA\",\"lastName\":\"WANGKIATKUL\",\"bankCode\":\"SICOTHBK\""
					+ ",\"accountId\":\"6032668977\"},\"deliveryMethod\":\"account_deposit\",\"sender\":{\"firstName\":\"NDBXMX\""
					+ ",\"lastName\":\"GYQMNB\",\"address\":{\"address\":\"senderAddress\",\"city\":\"senderCity\",\"countryCode\":\"THA\""
					+ ",\"postalCode\":\"senderZipCode\"},\"nationalityCountryCode\":\"KOR\",\"mobilePhone\":{\"number\":\"881111111\""
					+ ",\"countryCode\":\"66\"},\"idNumber\":\"idNumber\"},\"destination\":{\"country\":\"THA\",\"receive\":{\"currency\":\"THB\"}"
					+ ",\"operatorCode\":\"scb\"},\"remark\":\"This is SCB test remark\",\"source\":{\"country\":\"KOR\""
					+ ",\"send\":{\"amount\":\"1000.01\",\"currency\":\"USD\"},\"transactionId\":\"9974531076200937\"}}";
			response = linkHttpPost(response);
			System.out.println(">>>>> 2. response data: " + response);
			
			// RES mapper process
			response = mapperHttpPostRes(request);
			System.out.println(">>>>> 3. response data: " + response);
		}
		
		return "0102 Hello world!!! response";
	}

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	private static String POST_MAPPER_VALIDATE_REQ_S2J_HTTP_URL = "http://localhost:8086/v0.1/mapper/validate/s2j";
	
	private static String mapperHttpPostReq(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		String reqJson = null;
		if (Flag.flag) {
			reqJson = ""
					+ "{"
					+ "\"title\": \"/mapper/validate\","
					+ "\"command\": \"Stream To Json\","
					+ "\"data\": \"" + Convert.quote(request) + "\""
					+ "}";
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			
			response = SkipSSLConfig.getRestTemplate(0).exchange(POST_MAPPER_VALIDATE_REQ_S2J_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
		}
		
		if (Flag.flag) {
			// Pretty Print
			try {
				JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
				String json = jsonNode.toPrettyString();
				System.out.println(">>>>> response json: " + json);
				
				retResponse = jsonNode.at("/data").toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return retResponse;
	}
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	private static String POST_LINK_HTTP_URL = "http://localhost:8082/v0.1/link/validate";
	
	private static String linkHttpPost(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		String reqJson = null;
		if (Flag.flag) {
			reqJson = ""
					+ "{"
					+ "\"title\": \"/mapper/validate\","
					+ "\"command\": \"Stream To Json\","
					+ "\"data\": \"" + Convert.quote(request) + "\""
					+ "}";
			System.out.println(">>>>> reqJson = " + reqJson);
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			
			response = SkipSSLConfig.getRestTemplate(0).exchange(POST_LINK_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
		}
		
		if (Flag.flag) {
			// Pretty Print
			try {
				JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
				String json = jsonNode.toPrettyString();
				System.out.println(">>>>> response json: " + json);
				
				retResponse = jsonNode.at("/data").toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return retResponse;
	}
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	private static String POST_MAPPER_VALIDATE_RES_S2J_HTTP_URL = "http://localhost:8086/v0.1/mapper/validate/j2s";
	
	private static String mapperHttpPostRes(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		String reqJson = null;
		if (Flag.flag) {
			reqJson = ""
					+ "{"
					+ "\"title\": \"/mapper/validate\","
					+ "\"command\": \"Json To Stream\","
					+ "\"data\": \"" + Convert.quote(request) + "\""
					+ "}";
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			
			response = SkipSSLConfig.getRestTemplate(0).exchange(POST_MAPPER_VALIDATE_RES_S2J_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
		}
		
		if (Flag.flag) {
			// Pretty Print
			try {
				JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
				String json = jsonNode.toPrettyString();
				System.out.println(">>>>> response json: " + json);
				
				retResponse = jsonNode.at("/data").toString();
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

	private String POST_BATCH_HTTP_URL = "http://localhost:8085/batch/list";
	
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
