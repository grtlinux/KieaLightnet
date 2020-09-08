package org.tain.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.data.AccessToken;
import org.tain.object.auth.res._ResAuthData;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/auth"})
@Slf4j
public class AuthRestController {

	/*
	 * curl -v -d '("clientId":"_TEST_", "secret":"_TEST_"}' -X POST http://localhost:18081/v1.0/auth | jq
	 */
	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> reqStrToJson(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("VIRTUAL >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("VIRTUAL >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		_ResAuthData resAuthData = null;
		if (Flag.flag) {
			resAuthData = new _ResAuthData();
			resAuthData.setStatus("success");
			resAuthData.setMessage("OK");
			resAuthData.setAccessToken(AccessToken.get());
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(JsonPrint.getInstance().toJson(resAuthData), headers, HttpStatus.OK);
	}
}
