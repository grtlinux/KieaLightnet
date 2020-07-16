package org.tain.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
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
@RequestMapping(value = {"/link"})
@Slf4j
public class ValidateController {

	@Value("${lightnet.url}")
	private String lightnetUrl;

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private String POST_VALIDATE_HTTPS_URL = "/v1.1/remittances.validate";
	
	@PostMapping(value = {"/validate"})
	public ResponseEntity<?> validate(HttpEntity<String> _httpEntity) {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		String data = null;
		if (Flag.flag) {
			try {
				System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
				System.out.println(">>>>> Body = " + _httpEntity.getBody());
				data = _httpEntity.getBody();
				//JsonNode jsonNode = new ObjectMapper().readTree(_httpEntity.getBody());
				//data = jsonNode.at("/data").asText();
				//System.out.println(">>>>> data = " + data);
				//Map<String,String> map = new ObjectMapper().readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
				//data = map.get("data");
				//System.out.println(">>>>> data = " + data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String accessToken = null;
		if (Flag.flag) {
			try {
				// get AccessToken from /auth/auth
				accessToken = AccessToken.getAccessToken("/validate");
				System.out.println(">>>>> accessToken = " + accessToken);
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
				HttpEntity<String> httpEntity = new HttpEntity<>(data, headers);
				
				response = SkipSSLConfig.getRestTemplate(1).exchange(this.lightnetUrl + POST_VALIDATE_HTTPS_URL, HttpMethod.POST, httpEntity, String.class);
				System.out.println(">>>>> response.getBody() = " + response.getBody());
				retJson = response.getBody();
			} catch (HttpServerErrorException e) {
				System.out.println("-------------------------------------");
				System.out.println("LINK ERROR >>>>> e.getStatusText()           = " + e.getStatusText());
				System.out.println("LINK ERROR >>>>> e.getStatusCode()           = " + e.getStatusCode());
				System.out.println("LINK ERROR >>>>> e.getRawStatusCode()        = " + e.getRawStatusCode());
				System.out.println("LINK ERROR >>>>> e.getResponseHeaders()      = " + e.getResponseHeaders());
				System.out.println("LINK ERROR >>>>> e.getResponseBodyAsString() = " + e.getResponseBodyAsString());
				System.out.println("-------------------------------------");
				retJson = e.getResponseBodyAsString();
			} catch (Exception e) {
				//e.printStackTrace();
				System.out.println("LINK ERROR >>>>> e.getMessage() = " + e.getMessage());
				System.out.println("LINK ERROR >>>>> e.getLocalizedMessage() = " + e.getLocalizedMessage());
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
				String json = jsonNode.toPrettyString();
				System.out.println(">>>>> json: " + json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<>(retJson, HttpStatus.OK);
	}
}
