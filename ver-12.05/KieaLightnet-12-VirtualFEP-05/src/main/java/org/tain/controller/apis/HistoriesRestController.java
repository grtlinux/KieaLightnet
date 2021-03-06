package org.tain.controller.apis;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tain.object.lns.LnsJson;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.LnsHttpClient;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/apis/histories"})
@Slf4j
public class HistoriesRestController {

	@RequestMapping(value = {"/mapper/req/json2str"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> mapperReqJson2Str(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("SIT >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("SIT >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new LnsJson();
			lnsJson.setName("Histories mapperReqJson2Str");
			lnsJson.setHttpUrl("http://localhost:18086/v1.0/mapper/histories/req/j2s");
			lnsJson.setHttpMethod("POST");
			lnsJson.setReqJsonData(reqHttpEntity.getBody());
			
			lnsJson = LnsHttpClient.post(lnsJson);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/lns01"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> histories(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("SIT >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("SIT >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new LnsJson();
			lnsJson.setName("Histories lns01");
			lnsJson.setHttpUrl("http://localhost:18091/v1.0/lns01/histories");
			lnsJson.setHttpMethod("POST");
			
			//JsonNode jsonNode = new ObjectMapper().readTree(reqHttpEntity.getBody());
			//lnsJson.setReqStrData("" + jsonNode.get("reqStrData").asText());  // len(4) + method(4) + division(3)
			lnsJson.setReqStrData("" + reqHttpEntity.getBody());  // len(4) + method(4) + division(3)
			
			lnsJson = LnsHttpClient.post(lnsJson);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/mapper/res/str2json"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> mapperResStr2Json(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("SIT >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("SIT >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new LnsJson();
			lnsJson.setName("Histories mapperResStr2Json");
			lnsJson.setHttpUrl("http://localhost:18086/v1.0/mapper/histories/res/s2j");
			lnsJson.setHttpMethod("POST");
			
			//JsonNode jsonNode = new ObjectMapper().readTree(reqHttpEntity.getBody());
			//lnsJson.setResStrData("" + jsonNode.get("resStrData").asText());  // len(4) + method(4) + division(3)
			lnsJson.setResStrData("" + reqHttpEntity.getBody());  // len(4) + method(4) + division(3)
			
			lnsJson = LnsHttpClient.post(lnsJson);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/mapper/req/cstruct"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> mapperReqCStruct(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("SIT >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("SIT >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new LnsJson();
			lnsJson.setName("Histories mapperResStr2Json");
			lnsJson.setHttpUrl("http://localhost:18086/v1.0/mapper/histories/req/cstruct");
			lnsJson.setHttpMethod("POST");
			lnsJson.setReqJsonData(reqHttpEntity.getBody());
			
			lnsJson = LnsHttpClient.post(lnsJson);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/mapper/res/cstruct"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> mapperResCStruct(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("SIT >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("SIT >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new LnsJson();
			lnsJson.setName("Histories mapperResStr2Json");
			lnsJson.setHttpUrl("http://localhost:18086/v1.0/mapper/histories/res/cstruct");
			lnsJson.setHttpMethod("POST");
			lnsJson.setReqJsonData(reqHttpEntity.getBody());
			
			lnsJson = LnsHttpClient.post(lnsJson);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
}
