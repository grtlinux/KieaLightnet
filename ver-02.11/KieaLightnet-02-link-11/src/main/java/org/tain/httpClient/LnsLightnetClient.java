package org.tain.httpClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.tain.data.LnsData;
import org.tain.data.LnsError;
import org.tain.mapper.LnsJsonNode;
import org.tain.mapper.LnsNodeTools;
import org.tain.task.ErrorReaderJob;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LnsLightnetClient {

	@Autowired
	private LnsData lnsData;
	
	@Autowired
	private ErrorReaderJob errorReaderJob;
	
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode get(LnsJsonNode lnsJson) throws Exception {
		return get(lnsJson, true);
	}
	
	public LnsJsonNode get(LnsJsonNode lnsJsonNode, boolean flagAccessToken) throws Exception {
		
		log.info("=========================== LnsLightnetClient.get =============================");
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info(">>>>> GET.REQ.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			String httpUrl = lnsJsonNode.getText("httpUrl");
			HttpMethod httpMethod = HttpMethod.GET;
			
			LnsJsonNode reqJsonNode = new LnsJsonNode(lnsJsonNode.getJsonNode("request"));
			log.info(">>>>> GET.REQ.reqJsonNode    = {}", reqJsonNode.toPrettyString());
			LnsJsonNode reqHeadNode = new LnsJsonNode(reqJsonNode.getJsonNode("__head_data"));
			log.info(">>>>> GET.REQ.reqHeadNode    = {}", reqHeadNode.toPrettyString());
			LnsJsonNode reqBodyNode = new LnsJsonNode(reqJsonNode.getJsonNode("__body_data"));
			log.info(">>>>> GET.REQ.reqBodyNode    = {}", reqBodyNode.toPrettyString());
			
			Map<String,String> reqMap = new ObjectMapper().readValue(reqBodyNode.toPrettyString(), new TypeReference<Map<String,String>>(){});
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.setAll(reqMap);
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
					.queryParams(map)
					.build(true);
			httpUrl = builder.toString();
			log.info(">>>>> GET.REQ.httpUrl = {}", httpUrl);
			
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			if (flagAccessToken) reqHeaders.set("Authorization", "Bearer " + this.lnsData.getAccessToken());
			log.info(">>>>> GET.REQ.reqHeaders     = {}", reqHeaders);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqHeaders);
			log.info(">>>>> GET.REQ.reqHttpEntity  = {}", reqHttpEntity);
			
			ResponseEntity<String> response = null;
			LnsJsonNode resHeadNode = new LnsJsonNode(reqHeadNode.get());
			resHeadNode.put("reqres", "0210");
			resHeadNode.put("resTime", LnsNodeTools.getTime());
			try {
				response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						httpUrl
						, httpMethod
						, reqHttpEntity
						, String.class);
				
				log.info(">>>>> GET.RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> GET.RES.getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> GET.RES.getBody()            = {}", response.getBody());
				
				// TODO: for repair
				if (Flag.flag) {
					LnsError lnsError = this.errorReaderJob.search("SUCCESS");
					resHeadNode.put("resCode", lnsError.getError_code());
					resHeadNode.put("resMessage", "SUCCESS");
				}
				
				LnsJsonNode resDataNode = new LnsJsonNode(response.getBody());
				
				
				LnsJsonNode resJsonNode = new LnsJsonNode("{}");
				resJsonNode.put("__head_data", resHeadNode.get());
				resJsonNode.put("__body_data", resDataNode.get());
				lnsJsonNode.put("response", resJsonNode.get());
				
				log.info(">>>>> GET.RES-1.lnsJsonNode          = {}", lnsJsonNode.toPrettyString());
				
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
		
		if (Flag.flag) {
			log.info(">>>>> GET.RES-2.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		log.info("--------------------------- LnsLightnetClient.get -----------------------------");
		
		return lnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode post(LnsJsonNode lnsJson) throws Exception {
		return post(lnsJson, true);
	}
	
	public LnsJsonNode post(LnsJsonNode lnsJsonNode, boolean flagAccessToken) throws Exception {
		
		log.info("=========================== LnsLightnetClient.post =============================");
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info(">>>>> POST.REQ.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			String httpUrl = lnsJsonNode.getText("httpUrl");
			HttpMethod httpMethod = HttpMethod.POST;
			
			LnsJsonNode reqJsonNode = new LnsJsonNode(lnsJsonNode.getJsonNode("request"));
			log.info(">>>>> POST.REQ.reqJsonNode    = {}", reqJsonNode.toPrettyString());
			LnsJsonNode reqHeadNode = new LnsJsonNode(reqJsonNode.getJsonNode("__head_data"));
			log.info(">>>>> POST.REQ.reqHeadNode    = {}", reqHeadNode.toPrettyString());
			LnsJsonNode reqBodyNode = new LnsJsonNode(reqJsonNode.getJsonNode("__body_data"));
			log.info(">>>>> POST.REQ.reqBodyNode    = {}", reqBodyNode.toPrettyString());
			
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			if (flagAccessToken) reqHeaders.set("Authorization", "Bearer " + this.lnsData.getAccessToken());
			log.info(">>>>> POST.REQ.reqHeaders     = {}", reqHeaders);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqBodyNode.toPrettyString(), reqHeaders);
			log.info(">>>>> POST.REQ.reqHttpEntity  = {}", reqHttpEntity);
			
			ResponseEntity<String> response = null;
			try {
				response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						httpUrl
						, httpMethod
						, reqHttpEntity
						, String.class);
				
				log.info(">>>>> POST.RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> POST.RES.getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> POST.RES.getBody()            = {}", response.getBody());
				
				LnsJsonNode resHeadNode = new LnsJsonNode(reqHeadNode.get());
				resHeadNode.put("reqres", "0210");
				resHeadNode.put("resTime", LnsNodeTools.getTime());
				resHeadNode.put("resCode", "000");
				resHeadNode.put("resMessage", "SUCCESS");
				LnsJsonNode resDataNode = new LnsJsonNode(response.getBody());
				
				LnsJsonNode resJsonNode = new LnsJsonNode("{}");
				resJsonNode.put("__head_data", resHeadNode.get());
				resJsonNode.put("__body_data", resDataNode.get());
				lnsJsonNode.put("response", resJsonNode.get());
				
				log.info(">>>>> POST.RES-1.lnsJsonNode          = {}", lnsJsonNode.toPrettyString());
				
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
		
		if (Flag.flag) {
			log.info(">>>>> POST.RES-2.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		log.info("--------------------------- LnsLightnetClient.post -----------------------------");
		
		return lnsJsonNode;
	}
}
