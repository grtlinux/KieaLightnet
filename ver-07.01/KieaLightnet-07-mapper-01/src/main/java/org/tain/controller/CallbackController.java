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
import org.tain.utils.Convert;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/mapper/callback"})
@Slf4j
public class CallbackController {
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	@PostMapping(value = {"/j2s"})
	public ResponseEntity<?> jsonToStream(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println("MAPPER >>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println("MAPPER >>>>> Body = " + _httpEntity.getBody());
		}
		
		Map<String,String> map = null;
		String data = null;
		if (Flag.flag) {
			// mapping process
			ObjectMapper objectMapper = new ObjectMapper();
			map = objectMapper.readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
			
			data = map.get("data");
			System.out.println("MAPPER >>>>> data = [" + data + "]");
			if (Flag.flag) {
				JsonNode jsonNode = new ObjectMapper().readTree(data);
				System.out.println("MAPPER >>>>> REQ JSON: " + jsonNode.toPrettyString());
			}
			
			map.put("retData", Convert.quote("0601LNS02 callback request    1234567890  ABC1002003        Hello    "));
			System.out.println("MAPPER >>>>> map: " + map);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	@PostMapping(value = {"/s2j"})
	public ResponseEntity<?> streamToJson(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println("MAPPER >>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println("MAPPER >>>>> Body = " + _httpEntity.getBody());
		}
		
		Map<String,String> map = null;
		String data = null;
		if (Flag.flag) {
			// mapping process
			ObjectMapper objectMapper = new ObjectMapper();
			map = objectMapper.readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
			
			data = map.get("data");
			System.out.println("MAPPER >>>>> data = [" + data + "]");
			
			// link process (REQ -> RES)
			String retData = ""
					+ "{"
					+ "\"message\":\"ACKNOWLEDGE\","
					+ "\"status\":\"success\""
					+ "}";
			
			map.put("retData", Convert.quote(retData));
			System.out.println("MAPPER >>>>> map: " + map);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
}
