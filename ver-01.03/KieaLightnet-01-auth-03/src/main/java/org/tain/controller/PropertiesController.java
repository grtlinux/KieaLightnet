package org.tain.controller;

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
import org.tain.properties.LnsEnvAuthProperties;
import org.tain.properties.LnsEnvBaseProperties;
import org.tain.properties.LnsEnvJsonProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/auth/properties"})
@Slf4j
public class PropertiesController {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	// http://localhost:18081/v0.3/auth/properties/base
	
	@Autowired
	private LnsEnvBaseProperties lnsEnvBaseProperties;
	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"base"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> base(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("--------------- v1 Request --------------------");
			log.info(">>>>> Headers = " + httpEntity.getHeaders());
			log.info(">>>>> Body = " + httpEntity.getBody());
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		if (Flag.flag) {
			log.info("--------------- v1 Response --------------------");
			log.info(">>>>> Headers = " + headers);
			log.info(">>>>> Body = " + this.lnsEnvBaseProperties);
			log.info("==================================================");
		}
		
		return new ResponseEntity<>(this.lnsEnvBaseProperties, headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	// http://localhost:18081/v0.3/auth/properties/json

	@Autowired
	private LnsEnvJsonProperties lnsEnvJsonProperties;
	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"json"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> json(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("--------------- v1 Request --------------------");
			log.info(">>>>> Headers = " + httpEntity.getHeaders());
			log.info(">>>>> Body = " + httpEntity.getBody());
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		if (Flag.flag) {
			log.info("--------------- v1 Response --------------------");
			log.info(">>>>> Headers = " + headers);
			log.info(">>>>> Body = " + this.lnsEnvJsonProperties);
			log.info("==================================================");
		}
		
		return new ResponseEntity<>(this.lnsEnvJsonProperties, headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	// http://localhost:18081/v0.3/auth/properties/auth

	@Autowired
	private LnsEnvAuthProperties lnsEnvAuthProperties;
	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"virtual"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> virtual(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("--------------- v1 Request --------------------");
			log.info(">>>>> Headers = " + httpEntity.getHeaders());
			log.info(">>>>> Body = " + httpEntity.getBody());
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		if (Flag.flag) {
			log.info("--------------- v1 Response --------------------");
			log.info(">>>>> Headers = " + headers);
			log.info(">>>>> Body = " + this.lnsEnvAuthProperties);
			log.info("==================================================");
		}
		
		return new ResponseEntity<>(this.lnsEnvAuthProperties, headers, HttpStatus.OK);
	}
}
