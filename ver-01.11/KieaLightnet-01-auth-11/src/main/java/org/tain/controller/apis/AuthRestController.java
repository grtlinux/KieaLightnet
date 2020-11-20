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
import org.tain.httpClient.LnsAuthClient;
import org.tain.mapper.LnsJsonNode;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/auth"})
@Slf4j
public class AuthRestController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private LnsAuthClient lnsAuthClient;
	
	@Autowired
	private LnsData lnsData;
	
	/*
	 * curl -v -d '{"reqJson":{"clientId":"_CLIENT_ID_", "secret":"_SECRET_"}}' -X POST http://localhost:18081/v1.1/auth | jq
	 * curl -v -X POST http://localhost:18081/v1.1/auth | jq
	 */
	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> auth(HttpEntity<String> reqHttpEntity) throws Exception {
		
		log.info("=========================== AUTH: /auth =============================");
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		LnsJsonNode lnsJsonNode = null;
		if (Flag.flag) {
			if (reqHttpEntity.getBody() == null) {
				lnsJsonNode = new LnsJsonNode();
				log.info("AUTH.REQ >>>>> Headers = {}", reqHttpEntity.getHeaders());
				log.info("AUTH.REQ >>>>> lnsJsonNode = {}", lnsJsonNode.toPrettyString());
			} else {
				String body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
				lnsJsonNode = new LnsJsonNode(body);
				log.info("AUTH.REQ >>>>> Headers = {}", reqHttpEntity.getHeaders());
				log.info("AUTH.REQ >>>>> lnsJsonNode = Body = {}", lnsJsonNode.toPrettyString());
			}
		}
		
		if (Flag.flag) {
			lnsJsonNode.put("code", "00000");
			lnsJsonNode.put("status", "success");
			lnsJsonNode.put("message", "OK");
			lnsJsonNode.put("accessToken", this.lnsData.getAccessToken());
		}
		
		if (Flag.flag) {
			log.info("AUTH.RES >>>>> lnsJsonNode = {}", lnsJsonNode.toPrettyString());
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
	 * curl -v -d '{"reqJson":{"clientId":"pkey_vGivg1gzuzukJQg62kVWCcw1x5QOMOWT", "secret":"skey_sWzFCnkZx9aoZf9K2I3fM3se8XJYwIEt5l3371gzuzvY0giyjwO6cQ49bC4UeBlx"}}' -X POST http://localhost:18081/v1.1/auth/lightnet | jq
	 * curl -v -X POST http://localhost:18081/v1.1/auth/lightnet | jq
	 */
	@RequestMapping(value = {"/lightnet"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> lightnet(HttpEntity<String> reqHttpEntity) throws Exception {
		
		log.info("=========================== AUTH: /auth/lightnet =============================");
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		LnsJsonNode lnsJsonNode = null;
		if (Flag.flag) {
			String body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
			lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("reqJson", new LnsJsonNode(body).get());
			log.info("LIGHTNET.REQ >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("LIGHTNET.REQ >>>>> lnsJsonNode = Body = {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getLightnet1() + "/auth");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode = this.lnsAuthClient.auth(lnsJsonNode);
			
			lnsJsonNode.put("accessToken", this.lnsData.getAccessToken());
		}
		
		if (Flag.flag) {
			log.info("LIGHTNET.RES >>>>> lnsJsonNode = {}", lnsJsonNode.toPrettyString());
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
