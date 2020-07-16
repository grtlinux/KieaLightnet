package org.tain.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/v1.1/remittances.detail"})
@Slf4j
public class DetailV11Controller {

	// http://localhost:18888/v1/remittances.detail
	/*
		GET
		
		sourceCountry(S): The country which is the MTO sender
		destinationCountry(S): The country which sending MTO would like to send money to
		destinationOperatorCode(S): The operator code of the destination
		withDrawableAmount(S): The amount which the sender wishes to send.
		transactionCurrency(S): A transaction currency code in ISO-4217 format.
		deliveryMethod(S): The delivery method which the sending MTO would like to filter.
		
		KOR
		KHM
		lyhour
		1.500
		USD
		cash
	*/

	@Value("${json.res-data.files.detail}")
	private String jsonResDataFilesDetail;

	//@Value("${json.res-data.files.list1_1}")
	//private String jsonResDataFilesList1_1;

	@GetMapping(value = {""})
	public ResponseEntity<?> detail(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			System.out.println("--------------- v1.1 Request --------------------");
			System.out.println(">>>>> Headers = " + httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + httpEntity.getBody());
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		
		Map<String,Object> map = null;
		if (Flag.flag) {
			map = new ObjectMapper().readValue(new File(System.getenv("HOME") + jsonResDataFilesDetail), new TypeReference<Map<String,Object>>(){});
		}
		
		if (Flag.flag) {
			System.out.println("--------------- v1.1 Response --------------------");
			System.out.println(">>>>> Headers = " + headers);
			System.out.println(">>>>> Body = " + map);
		}
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
}
