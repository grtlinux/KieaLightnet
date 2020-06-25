package org.tain.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/mapper/detail"})
@Slf4j
public class DetailController {

	@PostMapping(value = {"/request/s2j"})
	public ResponseEntity<?> requestStreamToJson(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
		}
		
		Map<String,String> reqMap = null;
		if (Flag.flag) {
			reqMap = new ObjectMapper().readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
			//reqMap = new ObjectMapper().readValue(_httpEntity.getBody(), Map.class);
			reqMap.forEach((key,value) -> {
				System.out.printf(">>>>> [%s] => [%s]%n", key, value);
			}); 
		}
		
		Map<String,String> resMap = null;
		
		if (Flag.flag) {
			// mapping processing
			resMap = new HashMap<>();
			resMap.put("job", "/mapper/detail/request/s2j");
			resMap.put("jsonData", "{ \"sourceCountry\": \"KOR\", \"destinationCountry\": \"KHM\","
					+ "\"destinationOperatorCode\": \"lyhour\", \"withdrawableAmount\": \"1.500\","
					+ "\"transactionCurrency\": \"USD\", \"deliveryMethod\": \"cash\" }");
		}
		
		return new ResponseEntity<>(resMap, HttpStatus.OK);
	}
	
	@PostMapping(value = {"/request/j2s"})
	public ResponseEntity<?> requestJsonToStream(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	
	@PostMapping(value = {"/response/s2j"})
	public ResponseEntity<?> responseStreamToJson(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	@PostMapping(value = {"/response/j2s"})
	public ResponseEntity<?> responseJsonToStream(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
