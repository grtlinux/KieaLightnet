package org.tain.controller;

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
import org.tain.object.LnsJson;
import org.tain.object.LnsMap;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/link"})
@Slf4j
public class TestController {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	// http://localhost:18082/v0.3/link/properties/base
	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/test"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> base(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("--------------- v1 Request --------------------");
			log.info(">>>>> Headers = " + _httpEntity.getHeaders());
			log.info(">>>>> Body = " + _httpEntity.getBody());
		}
		
		LnsJson reqLnsJson = null;
		LnsMap reqLnsMap = null;
		if (Flag.flag) {
			reqLnsJson = new ObjectMapper().readValue(_httpEntity.getBody(), LnsJson.class);
			reqLnsMap = new LnsMap(reqLnsJson.getReqJsonData());
			log.info("LINK >>>>> 1. reqLnsJson = {}", reqLnsJson.toPrettyJson());
			log.info("LINK >>>>> 2. reqLnsMap = {}", reqLnsMap);
		}
		
		LnsJson resLnsJson = null;
		LnsMap resLnsMap = null;
		if (Flag.flag) {
			resLnsJson = (LnsJson) reqLnsJson.clone();
			resLnsMap = new LnsMap();
			resLnsMap.put("status", "OK");
			resLnsMap.put("message", "SUCCESS");
			resLnsMap.reset();
			
			resLnsJson.setDivisionType("RES");
			resLnsJson.setResJsonData(resLnsMap.toString());
			resLnsJson.setCode("00000");
			resLnsJson.setMessage("MSG: to get the auth..");
			
			log.info("LINK >>>>> 1. resLnsJson = {}", resLnsJson.toPrettyJson());
			log.info("LINK >>>>> 2. resLnsMap = {}", resLnsMap.toString());
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		if (Flag.flag) {
			log.info("--------------- v1 Response --------------------");
			log.info(">>>>> Headers = " + headers);
			log.info(">>>>> Body = " + resLnsJson);
			log.info("==================================================");
		}
		
		return new ResponseEntity<>(resLnsJson, headers, HttpStatus.OK);
	}
}
