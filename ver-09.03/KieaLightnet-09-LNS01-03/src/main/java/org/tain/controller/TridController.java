package org.tain.controller;

import java.time.LocalDateTime;

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
import org.tain.object.LnsJson;
import org.tain.object.LnsStream;
import org.tain.object.Message;
import org.tain.socket.StreamClient;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/lns01"})
@Slf4j
public class TridController {

	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/trid"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> validate(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			log.info("LNS01 >>>>> Headers = {}", _httpEntity.getHeaders());
			log.info("LNS01 >>>>> Body = {}", _httpEntity.getBody());
		}
		
		LnsJson reqLnsJson = null;
		LnsStream reqLnsStream = null;
		if (Flag.flag) {
			reqLnsJson = new ObjectMapper().readValue(_httpEntity.getBody(), LnsJson.class);
			reqLnsStream = new LnsStream(reqLnsJson.getReqStrData());
			log.info("LNS01 >>>>> 1. reqLnsJson = {}", reqLnsJson.toPrettyJson());
			log.info("LNS01 >>>>> 2. reqLnsStream = {}", reqLnsStream.toPrettyJson());
		}
		
		LnsJson resLnsJson = null;
		LnsStream resLnsStream = null;
		if (Flag.flag) {
			String response = this.callStreamClient(reqLnsStream.getData());
			resLnsStream = new LnsStream(response);
			
			resLnsJson = (LnsJson) reqLnsJson.clone();
			resLnsJson.setDivisionType("RES");
			resLnsJson.setResStrData(resLnsStream.getData());
			resLnsJson.setCode("00000");
			resLnsJson.setMessage("MSG: to get the trid..");
			
			log.info("LNS01 >>>>> 1. resLnsJson = {}", resLnsJson.toPrettyJson());
			log.info("LNS01 >>>>> 2. resLnsStream = {}", resLnsStream.toPrettyJson());
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(resLnsJson, headers, HttpStatus.OK);
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////

	@Autowired
	private StreamClient streamClient;
	
	private String callStreamClient(String req) throws Exception {
		Message message = new Message();
		message.setData(req);
		
		this.streamClient.getThread().setMessage(message);
		
		String res = message.getDataFromQueue();
		return res;
	}
}
