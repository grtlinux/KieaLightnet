package org.tain.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/mapper/commit"})
@Slf4j
public class CommitController {

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
			response = "COMMIT-J2S          1234567890  ABC1002003        Hello ";
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
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
				+ "\"title\": \"COMMIT-S2J\","
				+ "\"sourceCountry\": \"KOR\","
				+ "\"destinationCountry\": \"KHM\","
				+ "\"destinationOperatorCode\": \"lyhour\","
				+ "\"withdrawableAmount\": \"1.500\","
				+ "\"transactionCurrency\": \"USD\","
				+ "\"deliveryMethod\": \"cash\""
				+ "}";
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
