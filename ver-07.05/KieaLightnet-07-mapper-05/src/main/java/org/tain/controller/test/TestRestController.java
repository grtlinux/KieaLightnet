package org.tain.controller.test;

import java.util.Map;

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
import org.tain.utils.Sample;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/rest/mapper"})
@Slf4j
public class TestRestController {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	// http://localhost:18086/v0.5/rest/mapper/test1
	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/test1"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> test1(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		Map<?,?> map = Sample.getMap();
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	// http://localhost:18086/v0.5/rest/mapper/test2
	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/test2"}, method = {RequestMethod.GET, RequestMethod.POST})
	public Map<?,?> test2() throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		return Sample.getMap();
	}
}