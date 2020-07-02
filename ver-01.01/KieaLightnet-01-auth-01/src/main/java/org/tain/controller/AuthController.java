package org.tain.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping(value = {"/auth"})
	public ResponseEntity<?> auth(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + httpEntity.getBody());
		}
		
		while (AccessToken.get() == null) {
			// wait for a second
			Sleep.run(3 * 1000);
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("title", "/auth/auth");
		map.put("accessToken", AccessToken.get());
		map.put("createdDate", LocalDateTime.now());
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@Autowired
	private AuthScheduler authScheduler;
	
	@PostMapping(value = {"/refresh"})
	public ResponseEntity<?> refresh(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + httpEntity.getBody());
		}
		
		this.authScheduler.authRefresh();
		
		Map<String,Object> map = new HashMap<>();
		map.put("title", "/auth/auth");
		map.put("accessToken", AccessToken.get());
		map.put("createdDate", LocalDateTime.now());
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
