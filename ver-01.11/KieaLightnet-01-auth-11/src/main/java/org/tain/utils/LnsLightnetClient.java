package org.tain.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.tain.data.LnsData;
import org.tain.mapper.LnsJsonNode;
import org.tain.utils.enums.RestTemplateType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LnsLightnetClient {

	public static LnsJsonNode auth(LnsJsonNode lnsJsonNode) throws Exception {
		
		log.info("=========================== LnsLightnetClient.auth =============================");
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info(">>>>> REQ-0.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			String httpUrl = lnsJsonNode.getText("httpUrl");
			HttpMethod httpMethod = HttpMethod.POST;
			
			LnsJsonNode reqJsonNode = new LnsJsonNode();
			reqJsonNode.put("clientId", lnsJsonNode.getText("clientId"));
			reqJsonNode.put("secret", lnsJsonNode.getText("secret"));
			log.info(">>>>> REQ.reqJsonNode    = {}", reqJsonNode.toPrettyString());
			
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJsonNode.toPrettyString(), reqHeaders);
			log.info(">>>>> REQ.reqHttpEntity  = {}", reqHttpEntity);
			
			ResponseEntity<String> response = null;
			try {
				response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						httpUrl
						, httpMethod
						, reqHttpEntity
						, String.class);
				
				log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> RES.getBody()            = {}", response.getBody());
				
				LnsData.getInstance().setAccessToken(response.getHeaders().get("AccessToken").get(0));
				log.info(">>>>> RES.accessToken          = {}", LnsData.getInstance().getAccessToken());
				
				LnsJsonNode resJsonNode = new LnsJsonNode(response.getBody());
				log.info(">>>>> RES.resJsonNode          = {}", resJsonNode.toPrettyString());
				
				lnsJsonNode.put("code", "00000");
				lnsJsonNode.put("status", "success");
				lnsJsonNode.put("message", "OK");
			} catch (Exception e) {
				//e.printStackTrace();
				String message = e.getMessage();
				log.error("ERROR >>>>> {}", message);
				int pos1 = message.indexOf('[');
				int pos2 = message.lastIndexOf(']');
				lnsJsonNode.put("code", "99999");
				lnsJsonNode.put("status", "fail");
				lnsJsonNode.put("message", message.substring(pos1 + 1, pos2));
			}
		}
		
		log.info("--------------------------- LnsLightnetClient.auth -----------------------------");
		
		return lnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	/*
	public static LnsJson get(LnsJson lnsJson) throws Exception {
		return get(lnsJson, false);
	}
	
	public static LnsJson get(LnsJson lnsJson, boolean flagAccessToken) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== Lightnet START: {} ===================", lnsJson.getName());
			log.info(">>>>> REQ.httpUrl(method): {} ({})", lnsJson.getHttpUrl(), lnsJson.getHttpMethod());
		}
		
		if (Flag.flag) {
			String httpUrl = lnsJson.getHttpUrl();
			HttpMethod httpMethod = HttpMethod.GET;
			
			String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(lnsJson);
			log.info(">>>>> REQ.lnsJson        = {}", json);
			
			Map<String,String> reqMap = new ObjectMapper().readValue(lnsJson.getReqJsonData(), new TypeReference<Map<String,String>>(){});
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.setAll(reqMap);
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
					.queryParams(map)
					.build(true);
			httpUrl = builder.toString();
			log.info(">>>>> REQ.httpUrl(method) = {} ({})", httpUrl, httpMethod);
			
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			if (flagAccessToken) reqHeaders.set("Authorization", "Bearer " + LnsData.getInstance().getAccessToken());
			log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqHeaders);
			log.info(">>>>> REQ.reqHttpEntity  = {}", reqHttpEntity);
			
			ResponseEntity<String> response = null;
			try {
				response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						httpUrl
						, httpMethod
						, reqHttpEntity
						, String.class);
				
				log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> RES.getBody()            = {}", response.getBody());
				json = response.getBody();
				lnsJson = new ObjectMapper().readValue(json, LnsJson.class);
				log.info(">>>>> RES.lnsJson              = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
			} catch (Exception e) {
				//e.printStackTrace();
				String message = e.getMessage();
				log.error("ERROR >>>>> {}", message);
				int pos1 = message.indexOf('[');
				int pos2 = message.lastIndexOf(']');
				lnsJson.setCode("99999");
				lnsJson.setStatus("FAIL");
				lnsJson.setMsgJson(message.substring(pos1 + 1, pos2));
			}
		}
		
		if (Flag.flag) {
			log.info("================== Lightnet FINISH: {} ===================", lnsJson.getName());
		}
		
		return lnsJson;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public static LnsJson post(LnsJson lnsJson) throws Exception {
		return post(lnsJson, false);
	}
	
	public static LnsJson post(LnsJson lnsJson, boolean flagAccessToken) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== Lightnet START: {} ===================", lnsJson.getName());
			log.info(">>>>> REQ.httpUrl(method): {} ({})", lnsJson.getHttpUrl(), lnsJson.getHttpMethod());
		}
		
		if (Flag.flag) {
			String httpUrl = lnsJson.getHttpUrl();
			HttpMethod httpMethod = HttpMethod.POST;
			
			String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(lnsJson.getReqJsonData());
			//JsonNode jsonNode = new ObjectMapper().readTree(lnsReqWeb.getReqJson());
			//((ObjectNode) jsonNode.at("/source")).put("transactionId", TransactionId.get());
			//reqJson = jsonNode.toPrettyString();
			log.info(">>>>> REQ.lnsJson        = {}", json);
			
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			if (flagAccessToken) reqHeaders.set("Authorization", "Bearer " + LnsData.getInstance().getAccessToken());
			log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(json, reqHeaders);
			log.info(">>>>> REQ.reqHttpEntity  = {}", reqHttpEntity);
			
			ResponseEntity<String> response = null;
			try {
				response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						httpUrl
						, httpMethod
						, reqHttpEntity
						, String.class);
				
				log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> RES.getBody()            = {}", response.getBody());
				json = response.getBody();
				lnsJson = new ObjectMapper().readValue(json, LnsJson.class);
				log.info(">>>>> RES.lnsJson              = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
			} catch (Exception e) {
				//e.printStackTrace();
				String message = e.getMessage();
				log.error("ERROR >>>>> {}", message);
				int pos1 = message.indexOf('[');
				int pos2 = message.lastIndexOf(']');
				lnsJson.setCode("99999");
				lnsJson.setStatus("FAIL");
				lnsJson.setMsgJson(message.substring(pos1 + 1, pos2));
			}
		}
		
		if (Flag.flag) {
			log.info("================== Lightnet FINISH: {} ===================", lnsJson.getName());
		}
		
		return lnsJson;
	}
	*/
}
