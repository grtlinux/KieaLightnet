package org.tain.controller;

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
import org.tain.data.AccessToken;
import org.tain.object.callback.res._ResCallbackData;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/callback"})
@Slf4j
public class CallbackRestController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private AccessToken accessToken;
	
	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> reqStrToJson(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		/*
		String accessToken = null;
		if (Flag.flag) {
			// get AccessKey by auth
			accessToken = this.accessToken.get();
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			try {
				lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				reqHeaders.set("Authorization", "Bearer " + accessToken);  // accessToken
				reqHttpEntity = new HttpEntity<>(lnsJson.getReqJsonData(), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						this.projEnvUrlProperties.getLocalhost() + "/test"
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info("===============================================================");
				log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> getBody()            = {}", response.getBody());
				log.info("===============================================================");
				
				lnsJson.setResJsonData(response.getBody());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
		
		_ResCallbackData resData = null;
		if (Flag.flag) {
			resData = new _ResCallbackData();
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(resData, headers, HttpStatus.OK);
	}
}
