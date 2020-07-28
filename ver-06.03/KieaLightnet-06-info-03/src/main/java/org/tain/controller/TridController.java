package org.tain.controller;

import java.util.HashMap;
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
import org.tain.object.LnsJson;
import org.tain.object.LnsMap;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Trid;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/info/trid"})
@Slf4j
public class TridController {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	// http://localhost:18087/v0.3/info/trid/get

	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/get"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> getTrid(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println("MAPPER >>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println("MAPPER >>>>> Body = " + _httpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		LnsMap reqLnsMap = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(_httpEntity.getBody(), LnsJson.class);
			reqLnsMap = new LnsMap(lnsJson.getReqJsonData());
			log.info("LNS01 >>>>> 1. lnsJson = {}", lnsJson.toPrettyJson());
			log.info("LNS01 >>>>> 2. reqLnsMap = {}", reqLnsMap.toPrettyJson());
		}
		
		LnsMap resLnsMap = null;
		if (Flag.flag) {
			resLnsMap = reqLnsMap.clone();
			resLnsMap.put("trid", Trid.get());
			resLnsMap.reset();
			
			lnsJson.setResJsonData(resLnsMap.toJson());
			
			log.info("LNS01 >>>>> 1. lnsJson = {}", lnsJson.toPrettyJson());
			log.info("LNS01 >>>>> 2. resLnsMap = {}", resLnsMap.toPrettyJson());
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	// http://localhost:18087/v0.3/info/trid/_get

	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/_get"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> validate(HttpEntity<String> _httpEntity) {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("--------------- Request --------------------");
			log.info(">>>>> Headers = " + _httpEntity.getHeaders());
			log.info(">>>>> Body = " + _httpEntity.getBody());
		}
		
		String trid = Trid.get();
		
		// TODO: map to object
		Map<String,Object> map = new HashMap<>();
		if (Flag.flag) {
			map.put("trid", trid);
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
