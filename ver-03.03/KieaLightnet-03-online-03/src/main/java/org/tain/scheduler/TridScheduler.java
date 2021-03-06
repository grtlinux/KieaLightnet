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
import org.tain.object.LnsJson;
import org.tain.object.LnsMap;
import org.tain.object.LnsPacket;
import org.tain.object.LnsStream;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TridScheduler {

	public static LnsPacket process(LnsPacket reqLnsPacket) throws Exception {
		log.info(">>>>> request.json: {}", reqLnsPacket.toPrettyJson());
		
		LnsPacket resLnsPacket = null;
		if (Flag.flag) {
			// stream to json of trid
			String reqJson = mapperS2J(reqLnsPacket.getData());
			log.info(">>>>> {} reqJson: {}", CurrentInfo.get(), reqJson);
			
			// http info
			String resJson = httpPost(reqJson);
			log.info(">>>>> {} resJson: {}", CurrentInfo.get(), resJson);
			
			// json to stream of trid
			String resStream = mapperJ2S(resJson);
			log.info(">>>>> {} resStream: {}", CurrentInfo.get(), resStream);
			
			resLnsPacket = new LnsPacket(resStream);
			log.info(">>>>> {} resLnsPacket: {}", CurrentInfo.get(), resLnsPacket.toPrettyJson());
		}
		
		return resLnsPacket;
	}

	/////////////////////////////////////////////////////////////////////////
	
	public static String mapperS2J(String reqStream) throws Exception {
		log.info(">>>>> {} {}", CurrentInfo.get());
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = LnsJson.builder()
					.name("MAPPER")
					.title("mapperS2J with trid")
					.workUrl("http://localhost:18086/v0.3/mapper/trid/s2j")
					.division("trid")
					.divisionType("REQ")
					.dataType("STREAM")
					.reqStrData(reqStream)
					.build();
			LnsStream lnsStream = new LnsStream(lnsJson.getReqStrData());
			
			log.info(">>>>> 1. reqLnsJson = {}", lnsJson.toPrettyJson());
			log.info(">>>>> 2. reqLnsStream = {}", lnsStream.toPrettyJson());
		}
		
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(lnsJson.toJson(), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						lnsJson.getWorkUrl()
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
				
				lnsJson = new ObjectMapper().readValue(response.getBody(), LnsJson.class);
			} catch (Exception e) {
				log.error(">>>>> Exception.message = {}", e.getMessage());
				//System.exit(-1);
				return null;
			}
		}
		
		LnsMap lnsMap = null;
		if (Flag.flag) {
			lnsMap = new LnsMap(lnsJson.getReqJsonData());
			
			log.info(">>>>> 1. resLnsJson = {}", lnsJson.toPrettyJson());
			log.info(">>>>> 2. resLnsMap = {}", lnsMap.toPrettyJson());
		}
		
		return lnsMap.toJson();
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	private static String httpPost(String reqJson) throws Exception {
		log.info(">>>>> {} {}", CurrentInfo.get());
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = LnsJson.builder()
					.name("trid")
					.title("to get trid from INFO")
					.workUrl("http://localhost:18087/v0.3/info/trid/get")
					.division("trid")
					.divisionType("REQ")
					.dataType("JSON")
					.reqJsonData(reqJson)
					.build();
			LnsMap lnsMap = new LnsMap(lnsJson.getReqJsonData());
			
			log.info("ONLINE -> INFO >>>>> 1. lnsJson = {}", lnsJson.toPrettyJson());
			log.info("ONLINE -> INFO >>>>> 2. lnsMap = {}", lnsMap.toPrettyJson());
		}
		
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(lnsJson.toJson(), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						lnsJson.getWorkUrl()
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info("ONLINE <- INFO >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info("ONLINE <- INFO >>>>> response.getStatusCode()      = {}", response.getStatusCode());
				
				lnsJson = new ObjectMapper().readValue(response.getBody(), LnsJson.class);
			} catch (Exception e) {
				log.error(">>>>> Exception.message = {}", e.getMessage());
				//System.exit(-1);
				return null;
			}
		}
		
		LnsMap lnsMap = null;
		if (Flag.flag) {
			lnsMap = new LnsMap(lnsJson.getResJsonData());
			
			log.info("ONLINE <- INFO >>>>> 1. lnsJson = {}", lnsJson.toPrettyJson());
			log.info("ONLINE <- INFO >>>>> 2. lnsMap = {}", lnsMap.toPrettyJson());
		}
		
		return lnsMap.toJson();
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	public static String mapperJ2S(String resJson) throws Exception {
		log.info(">>>>> {} {}", CurrentInfo.get());
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = LnsJson.builder()
					.name("MAPPER")
					.title("mapperS2J with trid")
					.workUrl("http://localhost:18086/v0.3/mapper/trid/j2s")
					.division("trid")
					.divisionType("REQ")
					.dataType("JSON")
					.resJsonData(resJson)
					.build();
			LnsMap lnsMap = new LnsMap(lnsJson.getResJsonData());
			
			log.info(">>>>> 1. lnsJson = {}", lnsJson.toPrettyJson());
			log.info(">>>>> 2. lnsMap = {}", lnsMap.toPrettyJson());
		}
		
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(lnsJson.toJson(), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						lnsJson.getWorkUrl()
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info(">>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> response.getStatusCode()      = {}", response.getStatusCode());
				
				lnsJson = new ObjectMapper().readValue(response.getBody(), LnsJson.class);
			} catch (Exception e) {
				log.error(">>>>> Exception.message = {}", e.getMessage());
				//System.exit(-1);
				return null;
			}
		}
		
		LnsStream lnsStream = null;
		if (Flag.flag) {
			lnsStream = new LnsStream(lnsJson.getResStrData());
			
			log.info(">>>>> 1. lnsJson = {}", lnsJson.toPrettyJson());
			log.info(">>>>> 2. lnsStream = {}", lnsStream.toPrettyJson());
		}
		
		return lnsStream.getData();
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
	
	public static String process(String request) throws Exception {
		log.info("KANG-20200717 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.printf("ONLINE >>>>> 1. request  data: [%s]\n", request);
		}

		String response = "00180702RES.......";
		
		if (!Flag.flag) {
			/*
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
			*/
		}
		
		StringBuffer sb = null;
		if (Flag.flag) {
			response = infoHttpPost(request);
			System.out.printf("ONLINE >>>>> 2. response data: [%s]\n", response);
			
			Map<String,String> map = new ObjectMapper().readValue(response, new TypeReference<Map<String,String>>(){});
			
			sb = new StringBuffer();
			sb.append("0702");
			sb.append("RES");
			sb.append(map.get("transactionId"));
			sb.insert(0, String.format("%04d", sb.length() + 4));
		}
		
		return sb.toString();
	}

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	private static String POST_MAPPER_VALIDATE_REQ_S2J_HTTP_URL = "http://localhost:18086/v0.1/mapper/validate/s2j";
	
	@SuppressWarnings("unused")
	private static String mapperHttpPostReq(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		Map<String,String> reqMap = new HashMap<>();
		if (Flag.flag) {
			reqMap.put("title", "/mapper/validate");
			reqMap.put("command", "Stream To Json");
			reqMap.put("data", request);
			System.out.println("ONLINE >>>>> reqMap: " + reqMap);
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Map<String,String>> reqHttpEntity = new HttpEntity<>(reqMap, reqHeaders);
			
			response = RestTemplateConfig.get(RestTemplateType.NORMAL).exchange(POST_MAPPER_VALIDATE_REQ_S2J_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
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
	
	private static String POST_LINK_HTTP_URL = "http://localhost:18082/v0.1/link/validate";
	
	@SuppressWarnings("unused")
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
			
			response = RestTemplateConfig.get(RestTemplateType.NORMAL).exchange(POST_LINK_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
			
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
	
	private static String POST_MAPPER_VALIDATE_RES_S2J_HTTP_URL = "http://localhost:18086/v0.1/mapper/validate/j2s";
	
	@SuppressWarnings("unused")
	private static String mapperHttpPostRes(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		if (Flag.flag) {
			JsonNode jsonNode = new ObjectMapper().readTree(request);
			System.out.println("ONLINE >>>>> 3. REQ JSON: " + jsonNode.toPrettyString());
		}
		
		Map<String,String> reqMap = new HashMap<>();
		if (Flag.flag) {
			reqMap.put("title", "/mapper/validate");
			reqMap.put("command", "Stream To Json");
			reqMap.put("data", request);
			System.out.println("ONLINE >>>>> reqMap: " + reqMap);
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Map<String,String>> reqHttpEntity = new HttpEntity<>(reqMap, reqHeaders);
			
			response = RestTemplateConfig.get(RestTemplateType.NORMAL).exchange(POST_MAPPER_VALIDATE_RES_S2J_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
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
			try {
				Map<String,String> map = new ObjectMapper().readValue(response.getBody(), new TypeReference<Map<String,String>>(){});
				retResponse = map.get("retData");
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
	
	private static String POST_LINK_TRANSACTIONID_HTTP_URL = "http://localhost:18087/v0.2//info/rest/transactionId/get";
	
	private static String infoHttpPost(String request) throws Exception {
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
			
			response = RestTemplateConfig.get(RestTemplateType.NORMAL).exchange(POST_LINK_TRANSACTIONID_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
			
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
