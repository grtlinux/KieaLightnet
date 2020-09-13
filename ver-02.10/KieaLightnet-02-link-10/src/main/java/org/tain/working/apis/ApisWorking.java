package org.tain.working.apis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.tain.object.auth.req._ReqAuthData;
import org.tain.object.detail.req._ReqDetailData;
import org.tain.properties.ProjEnvJobProperties;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApisWorking {

	@Autowired
	private ProjEnvJobProperties projEnvJobProperties;
	
	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	private String accessToken;
	
	///////////////////////////////////////////////////////////////////////////
	
	public void transaction() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		switch (this.projEnvJobProperties.getTransaction()) {
		case "auth":
			this.auth();
			break;
		case "detail":
			this.auth();
			this.detail();
			break;
		default:
			this.auth();
			break;
		}
		
		if (Flag.flag) System.exit(0);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void auth() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== START: 1. authentication(POST) =============");
			
			Map<String,String> reqMap = null;
			String reqJson = null;
			if (Flag.flag) {
				_ReqAuthData reqAuthData = new _ReqAuthData();
				reqAuthData.setClientId(this.projEnvJobProperties.getAuthClientId());
				reqAuthData.setSecret(this.projEnvJobProperties.getAuthSecret());
				log.info(">>>>> REQ.reqJson of req~Data  = {}", JsonPrint.getInstance().toPrettyJson(reqAuthData));
				reqJson = JsonPrint.getInstance().toJson(reqAuthData);
				
				reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				log.info(">>>>> REQ.reqJson of reqMap  = {}", JsonPrint.getInstance().toPrettyJson(reqMap));
				reqJson = JsonPrint.getInstance().toJson(reqMap);
			}
			
			String httpUrl = null;
			if (Flag.flag) {
				httpUrl = this.projEnvUrlProperties.getLightnet1() + "/auth";
				log.info(">>>>> httpUrl              = {}", httpUrl);
			}
			
			if (Flag.flag) {
				try {
					HttpHeaders reqHeaders = new HttpHeaders();
					reqHeaders.setContentType(MediaType.APPLICATION_JSON);
					HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
					
					ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, HttpMethod.POST
							, reqHttpEntity
							, String.class);
					
					this.accessToken = response.getHeaders().get("AccessToken").get(0);
					
					log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> getBody()            = {}", response.getBody());
					log.info(">>>>> this.accessToken     = {}", this.accessToken);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			log.info("===============================================================");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void detail() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== START: 2. detail(GET) ==============");
			
			Map<String,String> reqMap = null;
			String reqJson = null;
			if (Flag.flag) {
				_ReqDetailData reqDetailData = new _ReqDetailData();
				reqJson = JsonPrint.getInstance().toJson(reqDetailData);
				log.info(">>>>> REQ.reqJson of req~Data  = {}", JsonPrint.getInstance().toPrettyJson(reqDetailData));
				
				reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				log.info(">>>>> REQ.reqJson of reqMap  = {}", JsonPrint.getInstance().toPrettyJson(reqMap));
				reqJson = JsonPrint.getInstance().toJson(reqMap);
			}
			
			String httpUrl = null;
			if (Flag.flag) {
				httpUrl = this.projEnvUrlProperties.getLightnet11() + "/remittances.detail";
				log.info(">>>>> httpUrl              = {}", httpUrl);
			}
			
			UriComponents builder = null;
			if (Flag.flag) {
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				log.info(">>>>> builder.toString     = {}", builder.toString());
			}
			
			if (Flag.flag) {
				try {
					HttpHeaders reqHeaders = new HttpHeaders();
					reqHeaders.setContentType(MediaType.APPLICATION_JSON);
					reqHeaders.set("Authorization", "Bearer " + accessToken);  // accessToken
					HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqHeaders);
					
					ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							builder.toString()
							, HttpMethod.GET
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			log.info("===============================================================");
		}
	}
}
