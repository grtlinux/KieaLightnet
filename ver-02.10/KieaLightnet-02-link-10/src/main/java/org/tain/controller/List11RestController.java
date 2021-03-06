package org.tain.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.tain.data.AccessToken;
import org.tain.object.lns.LnsJson;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Deprecated
@RestController
@RequestMapping(value = {"/link/list11"})
@Slf4j
public class List11RestController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private AccessToken accessToken;
	
	@RequestMapping(value = {"/get"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> reqStrToJson(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		String accessToken = null;
		if (Flag.flag) {
			// get AccessKey by auth
			accessToken = this.accessToken.get();
		}
		
		UriComponents builder = null;
		if (Flag.flag) {
			try {
				MultiValueMap<String, String> reqMap = new LinkedMultiValueMap<>();
				Map<String,String> map = new HashMap<>();
				map.put("limit", "20");
				reqMap.setAll(map);
				
				builder = UriComponentsBuilder.fromHttpUrl(this.projEnvUrlProperties.getLightnet11() + "/remittances")
						.queryParams(reqMap)
						.build(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			try {
				lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				reqHeaders.set("Authorization", "Bearer " + accessToken);  // accessToken
				//reqHttpEntity = new HttpEntity<>(lnsJson.getReqJsonData(), reqHeaders);
				reqHttpEntity = new HttpEntity<>(reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						builder.toString()
						//this.projEnvUrlProperties.getLocalhost() + "/list11"
						//this.projEnvUrlProperties.getLocalhost() + "/list11"
						//, HttpMethod.POST
						, HttpMethod.GET
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
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
}
