package org.tain.controller;

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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/mapper/list"})
@Slf4j
public class ListController {

	@PostMapping(value = {"/j2s"})
	public ResponseEntity<?> jsonToStream(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
		}
		
		String response = null;
		if (Flag.flag) {
			// mapping process
			response = ""
					+ "{"
					+ "  \"data\": \"LIST-J2S          1234567890  ABC1002003        Hello \""
					+ "}";
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@PostMapping(value = {"/s2j"})
	public ResponseEntity<?> streamToJson(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
		}
		
		String response = null;
		if (Flag.flag) {
			// mapping process
			response = ""
				+ "{"
				+ "\"title\": \"LIST-S2J\","
				+ "\"sourceCountry\": \"KOR\","
				+ "\"destinationCountry\": \"KHM\","
				+ "\"destinationOperatorCode\": \"lyhour\","
				+ "\"withdrawableAmount\": \"1.500\","
				+ "\"transactionCurrency\": \"USD\","
				+ "\"deliveryMethod\": \"cash\""
				+ "}";
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		//headers.add("X-Fsl-Location", "/");
		//headers.add("X-Fsl-Response-Code", "200");
		
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}
}
