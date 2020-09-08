package org.tain.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.object.test.req._ReqTestData;
import org.tain.object.test.res._ResTestData;
import org.tain.object.test.res._ResTestName;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/test"})
@Slf4j
public class TestRestController {

	/*
	 * curl -v -d '("clientId":"_TEST_", "secret":"_TEST_"}' -X POST http://localhost:18888/test/get | jq
	 */
	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> reqStrToJson(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("VIRTUAL >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("VIRTUAL >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		_ReqTestData reqTestData = null;
		_ResTestData resTestData = null;
		
		if (Flag.flag) {
			reqTestData = new ObjectMapper().readValue(reqHttpEntity.getBody(), _ReqTestData.class);
			log.info("VIRTUAL >>>>> reqData = {}", JsonPrint.getInstance().toPrettyJson(reqTestData));
			
			_ResTestName name = new _ResTestName();
			name.setFirstName("SEOK");
			name.setLastName("KANG");
			
			resTestData = new _ResTestData();
			resTestData.setTitle("Res_Title");
			resTestData.setMessage("Message");
			resTestData.setStatus("Status");
			resTestData.setName(name);
			log.info("VIRTUAL >>>>> resData = {}", JsonPrint.getInstance().toPrettyJson(resTestData));
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(resTestData, headers, HttpStatus.OK);
	}
}
