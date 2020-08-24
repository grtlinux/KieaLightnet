package org.tain.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/info/trId"})
@Slf4j
public class TrIdRestController {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	// http://localhost:18087/v0.3/info/transactionId/get

	private Random random = new Random(System.currentTimeMillis());
	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/get"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> validate(HttpEntity<String> _httpEntity) {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("--------------- Request --------------------");
			log.info(">>>>> Headers = " + _httpEntity.getHeaders());
			log.info(">>>>> Body = " + _httpEntity.getBody());
		}
		
		StringBuffer transactionId = new StringBuffer();
		if (Flag.flag) {
			LocalDateTime now = LocalDateTime.now();
			transactionId.append("HW");
			transactionId.append(now.format(DateTimeFormatter.ofPattern("yyMMddHHmm")));
			transactionId.append("A");
			transactionId.append(now.format(DateTimeFormatter.ofPattern("ss")));
			transactionId.append(String.valueOf(this.random.nextInt(10)));
			
			log.info(">>>>> transactionId = [" + transactionId.toString() + "]");
		}
		
		// TODO: map to object
		Map<String,Object> map = new HashMap<>();
		if (Flag.flag) {
			map.put("transactionId", transactionId.toString());
			map.put("status", "success");
			map.put("message", "OK");
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		if (Flag.flag) {
			log.info("--------------- Response --------------------");
			log.info(">>>>> Headers = " + headers);
			log.info(">>>>> Body = " + map);
			log.info("==================================================");
		}
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
}
