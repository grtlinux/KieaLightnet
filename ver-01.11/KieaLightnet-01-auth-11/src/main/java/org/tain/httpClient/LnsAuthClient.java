package org.tain.httpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tain.data.LnsData;
import org.tain.mapper.LnsJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LnsAuthClient {

	@Autowired
	private LnsData lnsData;
	
	public LnsJsonNode auth(LnsJsonNode lnsJsonNode) throws Exception {
		
		log.info("=========================== LnsLightnetClient.auth =============================");
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info(">>>>> AUTH.REQ.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			String httpUrl = lnsJsonNode.getText("httpUrl");
			HttpMethod httpMethod = HttpMethod.POST;
			
			LnsJsonNode reqJsonNode = new LnsJsonNode();
			reqJsonNode.put("clientId", lnsJsonNode.getText("clientId"));
			reqJsonNode.put("secret", lnsJsonNode.getText("secret"));
			log.info(">>>>> AUTH.REQ.reqJsonNode    = {}", reqJsonNode.toPrettyString());
			
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			log.info(">>>>> AUTH.REQ.reqHeaders     = {}", reqHeaders);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJsonNode.toPrettyString(), reqHeaders);
			log.info(">>>>> AUTH.REQ.reqHttpEntity  = {}", reqHttpEntity);
			
			ResponseEntity<String> response = null;
			try {
				response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						httpUrl
						, httpMethod
						, reqHttpEntity
						, String.class);
				
				log.info(">>>>> AUTH.RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> AUTH.RES.getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> RAUTH.ES.getBody()            = {}", response.getBody());
				
				this.lnsData.setAccessToken(response.getHeaders().get("AccessToken").get(0));
				log.info(">>>>> AUTH.RES.accessToken          = {}", this.lnsData.getAccessToken());
				
				lnsJsonNode.put("code", "00000");
				lnsJsonNode.put("status", "success");
				lnsJsonNode.put("message", "OK");
			} catch (Exception e) {
				//e.printStackTrace();
				String message = e.getMessage();
				log.error("ERROR >>>>> {}", message);
				int pos1 = message.indexOf('[');
				int pos2 = message.lastIndexOf(']');
				lnsJsonNode.put("code", "99999");
				lnsJsonNode.put("status", "fail");
				lnsJsonNode.put("message", message.substring(pos1 + 1, pos2));
			}
		}
		
		if (Flag.flag) {
			log.info(">>>>> AUTH.RES.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		log.info("--------------------------- LnsLightnetClient.auth -----------------------------");
		
		return lnsJsonNode;
	}
}
