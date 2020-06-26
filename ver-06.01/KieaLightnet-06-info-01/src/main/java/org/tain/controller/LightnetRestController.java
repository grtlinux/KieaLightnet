package org.tain.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.tain.utils.AccessToken;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/rest/lightnet"})
@Slf4j
public class LightnetRestController {

	@PostMapping(value = {"/auth"})
	public ResponseEntity<?> auth(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200618 >>>>> {}", CurrentInfo.get());
		ResponseEntity<String> response = null;
		
		Map<String,Object> map = null;
		Map<String,Object> mapReq = null;
		if (Flag.flag) {
			System.out.println("=====================================================");
			System.out.println("param    : " + _httpEntity.getBody());
			System.out.println("=====================================================");
			map = new ObjectMapper().readValue(_httpEntity.getBody(), new TypeReference<Map<String,Object>>(){});
			System.out.println("=====================================================");
			System.out.println("reqJsonData    : " + (String)map.get("reqJsonData"));
			System.out.println("=====================================================");
			mapReq = new ObjectMapper().readValue((String)map.get("reqJsonData"), new TypeReference<Map<String,Object>>(){});
		}
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("clientId", (String) mapReq.get("clientId"));
			parameters.put("secret", (String) mapReq.get("secret"));
			HttpEntity<Map<String,Object>> request = new HttpEntity<>(parameters, headers);
			
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.exchange((String)map.get("reqUrl"), HttpMethod.POST, request, String.class);
			
			AccessToken.set(response.getHeaders().get("AccessToken").get(0));
			System.out.println("=====================================================");
			System.out.println(">>>>> AccessToken: " + AccessToken.get());
			System.out.println("=====================================================");
			
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
		}
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
}
