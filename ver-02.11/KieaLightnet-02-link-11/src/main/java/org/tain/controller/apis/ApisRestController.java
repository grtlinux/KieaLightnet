package org.tain.controller.apis;

import java.net.URLDecoder;

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
import org.tain.httpClient.LnsHttpClient;
import org.tain.httpClient.LnsLightnetClient;
import org.tain.mapper.LnsJsonNode;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/link"})
@Slf4j
public class ApisRestController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private LnsHttpClient lnsHttpClient;
	
	@Autowired
	private LnsLightnetClient lnsLightnetClient;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	 * curl -v -d '{"clientId":"_CLIENT_ID_", "secret":"_SECRET_"}' -X POST http://localhost:18081/v1.1/auth | jq
	 * curl -v -X POST http://localhost:18081/v1.1/auth | jq
	 */
	@RequestMapping(value = {"/process"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> process(HttpEntity<String> reqHttpEntity) throws Exception {
		
		log.info("=========================== LINK: /link/process =============================");
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		LnsJsonNode lnsJsonNode = null;
		if (Flag.flag) {
			String body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
			lnsJsonNode = new LnsJsonNode(body);
			log.info("LINK.REQ >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("LINK.REQ >>>>> lnsJsonNode = Body = {}", lnsJsonNode.toPrettyString());
		}
		
		String reqResType = null;
		if (Flag.flag) {
			lnsJsonNode = new LnsJsonNode(reqHttpEntity.getBody());
			reqResType = lnsJsonNode.getValue("reqResType");
			log.info(">>>>> [{}] REQ.lnsJsonNode = {}", reqResType, lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			String sentbe = this.projEnvUrlProperties.getSentbe();
			if (sentbe.contains("localhost")) {
				lnsJsonNode.put("httpUrl", sentbe + "/apis/checkUser");
				lnsJsonNode.put("httpMethod", "POST");
				lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			} else {
				lnsJsonNode.put("httpUrl", sentbe + "/hanwha/checkUser");
				lnsJsonNode.put("httpMethod", "POST");
				lnsJsonNode = this.lnsLightnetClient.post(lnsJsonNode);
			}
			log.info(">>>>> RES.lnsJsonNode  = {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			log.info("LINK.RES >>>>> lnsJsonNode = {}", lnsJsonNode.toPrettyString());
		}
		
		MultiValueMap<String,String> headers = null;
		if (Flag.flag) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		log.info("--------------------------- LINK: /link/process -----------------------------");
		
		return new ResponseEntity<>(lnsJsonNode.toPrettyString(), headers, HttpStatus.OK);
	}
}
