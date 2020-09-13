package org.tain.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.object._list11.req._ReqList11Data;
import org.tain.object._list11.res._ResList11Data;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/list11"})
@Slf4j
public class List11RestController {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	///////////////////////////////////////////////////////////////////////////
	
	/*
	 * curl -v -d '("clientId":"_TEST_", "secret":"_TEST_"}' -X POST http://localhost:18888/list11 | jq
	 */
	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> reqStrToJson(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("VIRTUAL >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("VIRTUAL >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		_ReqList11Data reqData = null;
		_ResList11Data resData = null;
		
		if (Flag.flag) {
			if (reqHttpEntity.getBody() != null) {
				reqData = new ObjectMapper().readValue(reqHttpEntity.getBody(), _ReqList11Data.class);
				log.info("VIRTUAL >>>>> reqData = {}", JsonPrint.getInstance().toPrettyJson(reqData));
			}
			
			//resData = new _ResList11Data();
			//log.info("VIRTUAL >>>>> resData = {}", JsonPrint.getInstance().toPrettyJson(resData));
			
			// read json file
			String fileName = System.getenv("HOME") + this.projEnvParamProperties.getList11File();
			resData = new ObjectMapper().readValue(new File(fileName), _ResList11Data.class);
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(resData, headers, HttpStatus.OK);
	}
}
