package org.tain.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.data.AccessToken;
import org.tain.scheduler.AuthScheduler;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/auth"})
@Slf4j
public class AuthController {

	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	// http://localhost:18081/v0.3/auth/auth
	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/auth"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> auth(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("--------------- Request --------------------");
			log.info(">>>>> Headers = " + httpEntity.getHeaders());
			log.info(">>>>> Body = " + httpEntity.getBody());
		}
		
		while (AccessToken.get() == null) {
			// wait for a second
			Sleep.run(3 * 1000);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		// TODO: to be repaired
		Map<String,Object> map = new HashMap<>();
		map.put("url", "http://localhost:18081/v0.3/auth/auth");
		map.put("accessToken", AccessToken.get());
		map.put("createdDate", LocalDateTime.now());
		
		if (Flag.flag) {
			log.info("--------------- Response --------------------");
			log.info(">>>>> Headers = " + headers);
			log.info(">>>>> Body = " + map);
			log.info("==================================================");
		}
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}

	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	// http://localhost:18081/v0.3/auth/refresh
	
	@Autowired
	private AuthScheduler authScheduler;
	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/refresh"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> refresh(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("--------------- Request --------------------");
			log.info(">>>>> Headers = " + httpEntity.getHeaders());
			log.info(">>>>> Body = " + httpEntity.getBody());
		}
		
		this.authScheduler.authRefresh(httpEntity.getBody());
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		// TODO: to be repaired
		Map<String,Object> map = new HashMap<>();
		map.put("url", "http://localhost:18081/v0.3/auth/refresh");
		map.put("accessToken", AccessToken.get());
		map.put("createdDate", LocalDateTime.now());
		
		if (Flag.flag) {
			log.info("--------------- Response --------------------");
			log.info(">>>>> Headers = " + headers);
			log.info(">>>>> Body = " + map);
			log.info("==================================================");
		}
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
}
