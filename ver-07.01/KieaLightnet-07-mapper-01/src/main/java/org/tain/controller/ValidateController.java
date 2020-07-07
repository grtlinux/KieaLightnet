package org.tain.controller;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/mapper/validate"})
@Slf4j
public class ValidateController {

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	@PostMapping(value = {"/s2j"})
	public ResponseEntity<?> streamToJson(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
		}
		
		Map<String,String> map = null;
		String stream = null;
		if (Flag.flag) {
			// mapping process
			//{
			//  "title": "/mapper/validate",
			//  "command": "Stream To Json",
			//  "data": "0101 0000000000000000000000000000000000000000 "
			//}
			ObjectMapper objectMapper = new ObjectMapper();
			map = objectMapper.readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
			stream = map.get("data");
			System.out.println(">>>>> stream = [" + stream + "]");
			String response = ""
				+ "{"
				+ "\"title\":\"LIST-S2J\","
				+ "\"sourceCountry\":\"KOR\","
				+ "\"destinationCountry\":\"KHM\","
				+ "\"destinationOperatorCode\":\"lyhour\","
				+ "\"withdrawableAmount\":\"1.500\","
				+ "\"transactionCurrency\":\"USD\","
				+ "\"deliveryMethod\":\"cash\""
				+ "}";
			map.put("data", response);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	@PostMapping(value = {"/j2s"})
	public ResponseEntity<?> jsonToStream(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
		}
		
		Map<String,String> map = null;
		JsonNode jsonNode = null;
		if (Flag.flag) {
			// mapping process
			//{
			//  "title": "/mapper/validate",
			//  "command": "Json To Stream",
			//  "data": "{\"field1\":\"value1\",\"field2\":\"value2\"}"
			//}
			ObjectMapper objectMapper = new ObjectMapper();
			map      = objectMapper.readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
			jsonNode = objectMapper.readTree(map.get("data"));
			System.out.println(">>>>> jsonNode = " + jsonNode.toPrettyString());
			
			map.put("data", "0102AAAA        1234567890  ABC1002003        Hello    ");
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
}
