package org.tain.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/v1/remittances"})
@Slf4j
public class ListV1Controller {
	
	// http://localhost:18888/v1/remittances?operatorCode&offset=0&limit=20
	/*
		operatorCode(S): (required) operator slug remove this line before submit the document
		offset(N): offset for pagination
		limit(N): limit for pagination
		from(S): from time
		to(S): to time
		''
		0
		100
		2020-06-01 00:00:00
		2020-06-01 23:59:59
	*/
	
	@Value("${json.res-data.files.list1}")
	private String jsonResDataFilesList1;

	//@Value("${json.res-data.files.list1_1}")
	//private String jsonResDataFilesList1_1;

	@GetMapping(value = {""})
	public ResponseEntity<?> list(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			System.out.println("--------------- v1 Request --------------------");
			System.out.println(">>>>> Headers = " + httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + httpEntity.getBody());
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		
		Map<String,Object> map = null;
		if (Flag.flag) {
			map = new ObjectMapper().readValue(new File(System.getenv("HOME") + jsonResDataFilesList1), new TypeReference<Map<String,Object>>(){});
		}
		
		if (Flag.flag) {
			System.out.println("--------------- v1 Response --------------------");
			System.out.println(">>>>> Headers = " + headers);
			System.out.println(">>>>> Body = " + map);
		}
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
}
