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
		
		log.info("\n\n\n\n\n\n\n\n\n\n=========================== LINK: /link/process START =============================");
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		String body = null;
		if (Flag.flag) {
			body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
			log.info("LINK.process >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("LINK.process >>>>> Body = {}", body);
		}
		
		LnsJsonNode reqJsonNode = null;
		if (Flag.flag) {
			reqJsonNode = new LnsJsonNode(body);
			log.info("Link.process >>>>> reqJsonNode = {}", reqJsonNode.toPrettyString());
		}
		
		//LnsJsonNode resJsonNode = null;
		//if (Flag.flag) {
		//	resJsonNode = new LnsJsonNode("{}");
		//}
		
		String reqResType = null;
		if (Flag.flag) {
			reqResType = reqJsonNode.getText("reqResType");
			log.info(">>>>> LINK.reqResType = [{}]", reqResType);
		}
		
		String extHttpUrl = null;
		String extHttpMethod = null;
		if (Flag.flag) {
			LnsJsonNode infoJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			infoJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/info/headbase");
			infoJsonNode.put("httpMethod", "POST");
			infoJsonNode.put("/request", "reqResType", reqResType);
			infoJsonNode = this.lnsHttpClient.post(infoJsonNode);
			log.info(">>>>> LINK.infoJsonNode {} = \n{}", infoJsonNode.getText("/request", "reqResType"), infoJsonNode.getJsonNode("/response", "jsonInfo").toPrettyString());
			
			LnsJsonNode jsonInfo = new LnsJsonNode(infoJsonNode.getJsonNode("/response", "jsonInfo"));
			extHttpUrl = jsonInfo.getText("extHttpUrl");
			extHttpMethod = jsonInfo.getText("extHttpMethod");
			log.info(">>>>> LINK.extHttp = [{}] {}", extHttpMethod, extHttpUrl);
		}
		
		LnsJsonNode lnsJsonNode = null;
		if (Flag.flag) {
			lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", extHttpUrl);
			lnsJsonNode.put("httpMethod", extHttpMethod);
			lnsJsonNode.put("request", reqJsonNode.getJsonNode("json"));
			if ("POST".equals(extHttpMethod))
				lnsJsonNode = this.lnsLightnetClient.post(lnsJsonNode);
			else
				lnsJsonNode = this.lnsLightnetClient.get(lnsJsonNode);
			
			log.info(">>>>> RES.lnsJsonNode  = {}", lnsJsonNode.toPrettyString());
		}
		
		if (!Flag.flag) {
			/*
			String sentbe = this.projEnvUrlProperties.getLightnet11();
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
			*/
		}
		
		if (Flag.flag) {
			log.info("LINK.RES >>>>> lnsJsonNode = {}", lnsJsonNode.toPrettyString());
		}
		
		MultiValueMap<String,String> headers = null;
		if (Flag.flag) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		log.info("--------------------------- LINK: /link/process END -----------------------------\n\n\n\n\n\n\n\n\n\n");
		
		return new ResponseEntity<>(lnsJsonNode.toPrettyString(), headers, HttpStatus.OK);
	}
}
