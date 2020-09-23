package org.tain.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.tain.object.lns.LnsJson;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.LnsLightnetClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/auth"})
@Slf4j
public class AuthRestController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	/*
	 * curl -v -d '("clientId":"_TEST_", "secret":"_TEST_"}' -X POST http://localhost:18081/v1.0/auth | jq
	 * curl -v -X POST http://localhost:18081/v1.0/auth | jq
	 */
	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> auth(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("AUTH >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("AUTH >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
			
			Map<String,String> map = new HashMap<>();
			map.put("status", "success");
			map.put("message", "OK");
			map.put("accessToken", LnsData.getInstance().getAccessToken());
			
			lnsJson.setResJsonData(JsonPrint.getInstance().toPrettyJson(map));
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	/*
	 * curl -v -d '("clientId":"_TEST_", "secret":"_TEST_"}' -X POST http://localhost:18081/v1.0/auth | jq
	 * curl -v -X POST http://localhost:18081/v1.0/auth | jq
	 */
	@RequestMapping(value = {"/lightnet"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> lightnet(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
			lnsJson.setHttpUrl(this.projEnvUrlProperties.getLightnet1() + "/auth");
			lnsJson.setHttpMethod("POST");
			lnsJson = LnsLightnetClient.auth(lnsJson);
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = null;
		if (Flag.flag) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
}
