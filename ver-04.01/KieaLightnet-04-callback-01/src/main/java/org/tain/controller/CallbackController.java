package org.tain.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.config.SkipSSLConfig;
import org.tain.object.Message;
import org.tain.socket.StreamClient;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/callback"})
@Slf4j
public class CallbackController {

	@PostMapping(value = {"/callback"})
	public ResponseEntity<?> callback(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
		}
		
		String request = null;
		if (Flag.flag) {
			request = _httpEntity.getBody();
			// System.out.println("CALLBACK >>>>> request = " + request);
		}
		
		String response = null;
		if (Flag.flag) {
			// req mapper process j2s
			response = mapperHttpPostReq(request);
			System.out.printf("CALLBACK >>>>> 1. response = [%s]\n", response);
			
			// job process
			response = this.callStreamClient(response);
			System.out.printf("CALLBACK >>>>> 2. response = [%s]\n", response);
			
			// res mapper process s2j
			response = mapperHttpPostRes(response);
			System.out.printf("CALLBACK >>>>> 3. response = [%s]\n", response);
		}
		
		Map<String,String> map = null;
		if (Flag.flag) {
			map = new ObjectMapper().readValue(response, new TypeReference<Map<String,String>>(){});
			System.out.printf("CALLBACK >>>>> 9. response data: [%s]\n", map);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	private String POST_MAPPER_CALLBACK_REQ_S2J_HTTP_URL = "http://localhost:18086/v0.1/mapper/callback/j2s";
	
	private String mapperHttpPostReq(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		if (Flag.flag) {
			JsonNode jsonNode = new ObjectMapper().readTree(request);
			System.out.println("CALLBACK >>>>> REQ JSON: " + jsonNode.toPrettyString());
		}
		
		Map<String,String> reqMap = new HashMap<>();
		if (Flag.flag) {
			reqMap.put("title", "/mapper/callback");
			reqMap.put("command", "Stream To Json");
			reqMap.put("data", request);
			System.out.println("CALLBACK >>>>> reqMap: " + reqMap);
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Map<String,String>> reqHttpEntity = new HttpEntity<>(reqMap, reqHeaders);
			
			response = SkipSSLConfig.getRestTemplate(0).exchange(POST_MAPPER_CALLBACK_REQ_S2J_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
		}
		
		if (Flag.flag) {
			// Pretty Print
			try {
				JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
				String json = jsonNode.toPrettyString();
				System.out.println("CALLBACK >>>>> response json: " + json);
				
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

	@Autowired
	private StreamClient streamClient;
	
	private String callStreamClient(String req) throws Exception {
		Message message = new Message();
		message.setData(req);
		
		this.streamClient.getThread().setMessage(message);
		
		String res = message.getDataFromQueue();
		return res;
	}

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	private String POST_MAPPER_CALLBACK_RES_S2J_HTTP_URL = "http://localhost:18086/v0.1/mapper/callback/s2j";
	
	private String mapperHttpPostRes(String request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		String retResponse = null;
		
		Map<String,String> reqMap = new HashMap<>();
		if (Flag.flag) {
			reqMap.put("title", "/mapper/callback");
			reqMap.put("command", "Stream To Json");
			reqMap.put("data", request);
			System.out.println("CALLBACK >>>>> reqMap: " + reqMap);
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Map<String,String>> reqHttpEntity = new HttpEntity<>(reqMap, reqHeaders);
			
			response = SkipSSLConfig.getRestTemplate(0).exchange(POST_MAPPER_CALLBACK_RES_S2J_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
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
	
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////

	@PostMapping(value = {"/callback0"})
	public ResponseEntity<?> callback0(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		String request = null;
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
			request = _httpEntity.getBody();
			request = "0601 LNS02 callbacl REQUEST";
			if (Flag.flag) System.out.println("CALLBACK >>>>> request = " + request);
		}
		
		if (Flag.flag) {
			// /mapper/callback/j2s
		}
		
		String response = this.callStreamClient(request);
		if (Flag.flag) System.out.println("CALLBACK >>>>> response = " + response);
		
		if (Flag.flag) {
			// /mapper/callback/s2j
		}
		
		Map<String,Object> map = new HashMap<>();
		//map.put("title", "/callback");
		//map.put("response", response);
		map.put("message", "ACKNOWLEDGE");
		map.put("status", "success");
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
}
