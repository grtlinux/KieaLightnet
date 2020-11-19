package org.tain.controller.apis;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.data.LnsData;
import org.tain.mapper.LnsJsonNode;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.LnsLightnetClient;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/auth"})
@Slf4j
public class AuthRestController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	/*
	 * curl -v -d '{"clientId":"_TEST_", "secret":"_TEST_"}' -X POST http://localhost:18081/v1.0/auth | jq
	 * curl -v -X POST http://localhost:18081/v1.0/auth | jq
	 */
	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> auth(HttpEntity<String> reqHttpEntity) throws Exception {
		
		log.info("=========================== AUTH: /auth =============================");
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		LnsJsonNode lnsJsonNode = null;
		if (Flag.flag) {
			String body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
			lnsJsonNode = new LnsJsonNode(body);
			log.info("AUTH >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("AUTH >>>>> lnsJsonNode = Body = {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			lnsJsonNode.put("code", "00000");
			lnsJsonNode.put("status", "success");
			lnsJsonNode.put("message", "OK");
			lnsJsonNode.put("accessToken", LnsData.getInstance().getAccessToken());
		}
		
		MultiValueMap<String,String> headers = null;
		if (Flag.flag) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		log.info("--------------------------- AUTH: /auth -----------------------------");
		
		return new ResponseEntity<>(lnsJsonNode.toPrettyString(), headers, HttpStatus.OK);
	}
	
	/*
	 * curl -v -d '{"clientId":"pkey_vGivg1gzuzukJQg62kVWCcw1x5QOMOWT", "secret":"skey_sWzFCnkZx9aoZf9K2I3fM3se8XJYwIEt5l3371gzuzvY0giyjwO6cQ49bC4UeBlx"}' -X POST http://localhost:18081/v1.1/auth/lightnet | jq
	 * curl -v -X POST http://localhost:18081/v1.1/auth/lightnet | jq
	 */
	@RequestMapping(value = {"/lightnet"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> lightnet(HttpEntity<String> reqHttpEntity) throws Exception {
		
		log.info("=========================== AUTH: /auth/lightnet =============================");
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		LnsJsonNode lnsJsonNode = null;
		if (Flag.flag) {
			String body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
			lnsJsonNode = new LnsJsonNode(body);
			log.info("AUTH >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("AUTH >>>>> lnsJsonNode = Body = {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getLightnet1() + "/auth");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode = LnsLightnetClient.auth(lnsJsonNode);
			
			lnsJsonNode.put("accessToken", LnsData.getInstance().getAccessToken());
		}
		
		MultiValueMap<String,String> headers = null;
		if (Flag.flag) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		log.info("--------------------------- AUTH: /auth/lightnet -----------------------------");
		
		return new ResponseEntity<>(lnsJsonNode.toPrettyString(), headers, HttpStatus.OK);
	}
}
