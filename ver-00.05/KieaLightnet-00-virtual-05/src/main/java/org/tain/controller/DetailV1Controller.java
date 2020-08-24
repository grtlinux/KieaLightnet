package org.tain.controller;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.properties.LnsEnvJobProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/v1/remittances.detail"})
@Slf4j
public class DetailV1Controller {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
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

	@Autowired
	private LnsEnvJobProperties lnsEnvVirtualProperties;

	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> detail(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("--------------- v1 Request --------------------");
			log.info(">>>>> Headers = " + httpEntity.getHeaders());
			log.info(">>>>> Body = " + httpEntity.getBody());
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		Map<String,Object> map = null;
		if (Flag.flag) {
			map = new ObjectMapper().readValue(
					new File(System.getenv("HOME") + this.lnsEnvVirtualProperties.getDetailFile())
					, new TypeReference<Map<String,Object>>(){});
		}
		
		if (Flag.flag) {
			log.info("--------------- v1 Response --------------------");
			log.info(">>>>> Headers = " + headers);
			log.info(">>>>> Body = " + map);
			log.info("==================================================");
		}
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
}
