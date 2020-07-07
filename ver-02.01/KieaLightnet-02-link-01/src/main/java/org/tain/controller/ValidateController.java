package org.tain.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.config.SkipSSLConfig;
import org.tain.data.AccessToken;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/link/validate"})
@Slf4j
public class ValidateController {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private String POST_VALIDATE_HTTPS_URL = "https://test-public.lightnetapis.io/v1.1/remittances.validate";
	
	@PostMapping(value = {""})
	public ResponseEntity<?> validate(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		String data = null;
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
			JsonNode jsonNode = new ObjectMapper().readTree(_httpEntity.getBody());
			data = jsonNode.at("/data").asText();
		}
		
		String accessToken = null;
		if (Flag.flag) {
			// get AccessToken from /auth/auth
			accessToken = AccessToken.getAccessToken("/validate");
			System.out.println(">>>>> accessToken = " + accessToken);
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);
			HttpEntity<String> httpEntity = new HttpEntity<>(data, headers);
			
			response = SkipSSLConfig.getRestTemplate(1).exchange(POST_VALIDATE_HTTPS_URL, HttpMethod.POST, httpEntity, String.class);
		}
		
		if (Flag.flag) {
			// Pretty Print
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			JsonNode jsonNode = objectMapper.readTree(response.getBody());
			//String json = jsonNode.at("/").toPrettyString();
			String json = jsonNode.toPrettyString();
			System.out.println(">>>>> json: " + json);
		}
		
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
}
