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
import org.tain.object.lns.LnsJson;
import org.tain.object.lns.LnsStream;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrIdScheduler {

	public static LnsStream process(LnsStream reqLnsStream) throws Exception {
		log.info(">>>>> {} {}", CurrentInfo.get());
		log.info(">>>>> 0. reqLnsStream.json: {}", JsonPrint.getInstance().toPrettyJson(reqLnsStream));
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			// initialize (lnsJson)
			lnsJson = LnsJson.builder()
					.name("MAPPER")
					.title("trid: ready")
					.trType("")
					.trCode("")
					.svrCode("")
					.reqStrData(reqLnsStream.getData())
					.build();
			log.info(">>>>> 1. lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) {
			// reqStr -> reqJson
			lnsJson.setTitle("trid: reqStr -> reqJson");
			lnsJson.setTrType("0200");
			lnsJson.setTrCode("100");
			lnsJson.setSvrCode("trid.req");
			log.info(">>>>> 2. lnsJson.BEFORE = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
			lnsJson = mapperStr2Json(lnsJson);
			log.info(">>>>> 2. lnsJson.AFTER  = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag ) {
			// reqJson -> resJson
			lnsJson.setTitle("trid: reqJson -> resJson");
			lnsJson.setTrType("0200");
			lnsJson.setTrCode("100");
			lnsJson.setSvrCode("trid");
			log.info(">>>>> 3. lnsJson.BEFORE = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
			lnsJson = httpPost(lnsJson);
			log.info(">>>>> 3. lnsJson.AFTER  = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) {
			// resJson -> resStr
			lnsJson.setTitle("trid: resJson -> resStr");
			lnsJson.setTrType("0210");   // server setting
			lnsJson.setTrCode("100");
			lnsJson.setSvrCode("trid.res");
			log.info(">>>>> 4. lnsJson.BEFORE = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
			lnsJson = mapperJson2Str(lnsJson);
			log.info(">>>>> 4. lnsJson.AFTER  = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		LnsStream resLnsStream = null;
		if (Flag.flag) {
			resLnsStream = new LnsStream(lnsJson.getResStrData());
			log.info(">>>>> 5. resLnsStream.json: {}", JsonPrint.getInstance().toPrettyJson(resLnsStream));
		}
		
		return resLnsStream;
	}

	/////////////////////////////////////////////////////////////////////////
	
	public static LnsJson mapperStr2Json(LnsJson lnsJson) throws Exception {
		log.info(">>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(JsonPrint.getInstance().toJson(lnsJson), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						"http://localhost:18086/v0.5/mapper/" + lnsJson.getSvrCode() // trid.req
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info("------------------------------------------");
				log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
				log.info("------------------------------------------");
				
				lnsJson = new ObjectMapper().readValue(response.getBody(), LnsJson.class);
			} catch (Exception e) {
				log.error(">>>>> Exception.message = {}", e.getMessage());
				throw e;
				//return null;
			}
		}
		
		return lnsJson;
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	private static LnsJson httpPost(LnsJson lnsJson) throws Exception {
		log.info(">>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(JsonPrint.getInstance().toJson(lnsJson), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						"http://localhost:18087/v0.5/info/" + lnsJson.getSvrCode() // trid
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info("------------------------------------------");
				log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
				log.info("------------------------------------------");
				
				lnsJson = new ObjectMapper().readValue(response.getBody(), LnsJson.class);
			} catch (Exception e) {
				log.error(">>>>> Exception.message = {}", e.getMessage());
				throw e;
				//System.exit(-1);
				//return null;
			}
		}
		
		return lnsJson;
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	public static LnsJson mapperJson2Str(LnsJson lnsJson) throws Exception {
		log.info(">>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(JsonPrint.getInstance().toJson(lnsJson), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						"http://localhost:18086/v0.5/mapper/" + lnsJson.getSvrCode() // trid.res
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info("------------------------------------------");
				log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
				log.info("------------------------------------------");
				
				lnsJson = new ObjectMapper().readValue(response.getBody(), LnsJson.class);
			} catch (Exception e) {
				log.error(">>>>> Exception.message = {}", e.getMessage());
				throw e;
				//System.exit(-1);
				//return null;
			}
		}
		
		return lnsJson;
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
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	@Deprecated
	public static String process(String request) throws Exception {
		log.info("KANG-20200717 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.printf(">>>>> 1. request  data: [%s]\n", request);
		}

		String response = "00180702RES.......";
		
		if (!Flag.flag) {
			/*
			// req mapper process
			response = mapperHttpPostReq(request);
			System.out.printf(">>>>> 1. response data: [%s]\n", response);
			if (Flag.flag) {
				JsonNode jsonNode = new ObjectMapper().readTree(response);
				System.out.println(">>>>> 1. REQ JSON: " + jsonNode.toPrettyString());
			}
			
			response = linkHttpPost(response);
			System.out.printf(">>>>> 2. response data: [%s]\n", response);
			
			// process
			response = mapperHttpPostRes(response);
			System.out.printf(">>>>> 3. response data: [%s]\n", response);
			*/
		}
		
		StringBuffer sb = null;
		if (Flag.flag) {
			response = infoHttpPost(request);
			System.out.printf(">>>>> 2. response data: [%s]\n", response);
			
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
	
	@Deprecated
	private static String POST_MAPPER_VALIDATE_REQ_S2J_HTTP_URL = "http://localhost:18086/v0.1/mapper/validate/s2j";
	
	@Deprecated
	@SuppressWarnings("unused")
	private static String mapperHttpPostReq(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		Map<String,String> reqMap = new HashMap<>();
		if (Flag.flag) {
			reqMap.put("title", "/mapper/validate");
			reqMap.put("command", "Stream To Json");
			reqMap.put("data", request);
			System.out.println(">>>>> reqMap: " + reqMap);
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
				System.out.println(">>>>> response json: " + json);
				
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
	
	@Deprecated
	private static String POST_LINK_HTTP_URL = "http://localhost:18082/v0.1/link/validate";
	
	@Deprecated
	@SuppressWarnings("unused")
	private static String linkHttpPost(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		if (Flag.flag) {
			System.out.println(">>>>> request = " + request);
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
	
	@Deprecated
	private static String POST_MAPPER_VALIDATE_RES_S2J_HTTP_URL = "http://localhost:18086/v0.1/mapper/validate/j2s";
	
	@Deprecated
	@SuppressWarnings("unused")
	private static String mapperHttpPostRes(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		if (Flag.flag) {
			JsonNode jsonNode = new ObjectMapper().readTree(request);
			System.out.println(">>>>> 3. REQ JSON: " + jsonNode.toPrettyString());
		}
		
		Map<String,String> reqMap = new HashMap<>();
		if (Flag.flag) {
			reqMap.put("title", "/mapper/validate");
			reqMap.put("command", "Stream To Json");
			reqMap.put("data", request);
			System.out.println(">>>>> reqMap: " + reqMap);
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
				System.out.println(">>>>> response json: " + json);
				
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
	
	@Deprecated
	private static String POST_LINK_TRANSACTIONID_HTTP_URL = "http://localhost:18087/v0.2//info/rest/transactionId/get";
	
	@Deprecated
	private static String infoHttpPost(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		if (Flag.flag) {
			System.out.println(">>>>> request = " + request);
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

	@Deprecated
	private String POST_BATCH_HTTP_URL = "http://localhost:18085/batch/list";
	
	@Deprecated
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
