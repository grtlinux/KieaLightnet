package org.tain.controller.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tain.httpClient.LnsHttpClient;
import org.tain.mapper.LnsJsonNode;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/online"})
@Slf4j
public class ApisRestController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private LnsHttpClient lnsHttpClient;
	
	/*
	 * url: http://localhost:18083/v1.0/online/getCalculation
	 */
	@RequestMapping(value = {"/process"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> process(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		String strBody = null;
		if (Flag.flag) {
			strBody = reqHttpEntity.getBody();
			log.info("ONLINE-1 >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("ONLINE-1 >>>>> Body = {}", strBody);
		}
		
		JsonNode reqJsonNode = null;
		String reqResType = null;
		String reqJson = null;
		
		if (Flag.flag) {
			reqJsonNode = new ObjectMapper().readTree(strBody);
			String strReqRes = reqJsonNode.at("/__head_data").get("reqres").asText();
			String strType = reqJsonNode.at("/__head_data").get("type").asText();
			
			reqResType = strReqRes + strType;
			log.info("ONLINE-2 >>>>> reqResType = {}", reqResType);
			
			reqJson = reqJsonNode.toPrettyString();
			log.info("ONLINE-2 >>>>> reqJson = {}", reqJson);
		}
		
		String strResJson = null;
		if (Flag.flag) {
			// 2. link
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getLink() + "/link/process");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("reqResType", reqResType);
			lnsJsonNode.put("reqJson", reqJsonNode);
			lnsJsonNode.put("/request", "reqResType", reqResType);
			lnsJsonNode.put("/request", "json", reqJsonNode);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-3 >>>>> LINK_PROCESS lnsJsonNode.link {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("response").toPrettyString());
			strResJson = lnsJsonNode.getJsonNode("/response", "response").toPrettyString();
			log.info("ONLINE-3.2 >>>>> LINK_PROCESS lnsJsonNode.link {}    __body_data = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response/response", "__body_data").toPrettyString());
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(strResJson, headers, HttpStatus.OK);
	}
}
