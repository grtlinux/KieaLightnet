package org.tain.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/info/rest/transactionId"})
@Slf4j
public class TransactionIdRestController {

	private Random random = new Random(new Date().getTime());
	
	@PostMapping(value = {"/get"})
	public ResponseEntity<?> get(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200717 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println("--------------- Request --------------------");
			System.out.println(">>>>> Headers = " + httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + httpEntity.getBody());
		}
		
		StringBuffer transactionId = new StringBuffer();
		if (Flag.flag) {
			LocalDateTime now = LocalDateTime.now();
			transactionId.append("HW");
			transactionId.append(now.format(DateTimeFormatter.ofPattern("yyMMddHHmm")));
			transactionId.append("A");
			transactionId.append(String.valueOf(this.random.nextInt(10)));
			transactionId.append(now.format(DateTimeFormatter.ofPattern("ss")));
			if (Flag.flag) System.out.println(">>>>> transactionId = [" + transactionId.toString() + "]");
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		Map<String,Object> map = new HashMap<>();
		map.put("transactionId", transactionId.toString());
		map.put("status", "success");
		map.put("message", "OK");
		
		if (Flag.flag) {
			System.out.println("--------------- Response --------------------");
			System.out.println(">>>>> Headers = " + headers);
			System.out.println(">>>>> Body = " + map);
		}
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
}
