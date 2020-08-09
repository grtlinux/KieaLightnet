package org.tain.controller;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.tain.data.AccessToken;
import org.tain.properties.LnsEnvLinkProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/link"})
@Slf4j
public class CommitController {

	@Autowired
	private LnsEnvLinkProperties lnsEnvLinkProperties;

	@Autowired
	private AccessToken accessToken;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	// http://localhost:18082/v0.3/link/commit

	private String POST_COMMIT_HTTPS_URL = "/v1.1/remittances.commit";
	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/commit"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> commit(HttpEntity<String> _httpEntity) {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("--------------- Request --------------------");
			log.info(">>>>> Headers = " + _httpEntity.getHeaders());
			log.info(">>>>> Body = " + _httpEntity.getBody());
		}
		
		MultiValueMap<String, String> reqMap = new LinkedMultiValueMap<>();
		if (Flag.flag && _httpEntity.getBody() != null) {
			try {
				Map<String,String> map = new ObjectMapper().readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>() {});
				reqMap.setAll(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String accessToken = null;
		if (Flag.flag) {
			try {
				// get AccessToken from /auth/auth
				accessToken = this.accessToken.getAccessToken("/commit");
				log.info(">>>>> accessToken = " + accessToken);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		ResponseEntity<String> response = null;
		String retJson = null;
		if (Flag.flag) {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.set("Authorization", "Bearer " + accessToken);
				HttpEntity<String> httpEntity = new HttpEntity<>(headers);
				
				response = RestTemplateConfig.get(RestTemplateType.SSL01).exchange(
						this.lnsEnvLinkProperties.getLightnetUrl() + POST_COMMIT_HTTPS_URL
						, HttpMethod.POST
						, httpEntity
						, String.class);
				
				log.info("--------------- Response --------------------");
				log.info(">>>>> Headers = " + response.getHeaders());
				log.info(">>>>> Body = " + response.getBody());
				retJson = response.getBody();
			} catch (HttpServerErrorException e) {
				log.info("--------------- Response --------------------");
				log.info("LINK ERROR >>>>> e.getStatusText()           = " + e.getStatusText());
				log.info("LINK ERROR >>>>> e.getStatusCode()           = " + e.getStatusCode());
				log.info("LINK ERROR >>>>> e.getRawStatusCode()        = " + e.getRawStatusCode());
				log.info("LINK ERROR >>>>> e.getResponseHeaders()      = " + e.getResponseHeaders());
				log.info("LINK ERROR >>>>> e.getResponseBodyAsString() = " + e.getResponseBodyAsString());
				log.info("==================================================");
				retJson = e.getResponseBodyAsString();
			} catch (Exception e) {
				// system error
				e.printStackTrace();
				return null;
			}
		}
		
		if (Flag.flag) {
			try {
				// Pretty Print
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				JsonNode jsonNode = objectMapper.readTree(retJson);
				//String json = jsonNode.at("/").toPrettyString();
				log.info(">>>>> json: " + jsonNode.toPrettyString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(retJson, headers, HttpStatus.OK);
	}
}
