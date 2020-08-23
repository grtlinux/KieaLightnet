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
import org.tain.object.lns.LnsJson;
import org.tain.object.lns.LnsMessage;
import org.tain.object.lns.LnsStream;
import org.tain.socket.StreamClient;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/lns01"})
@Slf4j
public class TridController {

	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/trid"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> trid(HttpEntity<String> _httpEntity) throws Exception {
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
			log.info("LNS01 >>>>> 1. reqLnsJson = {}", JsonPrint.getInstance().toPrettyJson(reqLnsJson));
			log.info("LNS01 >>>>> 2. reqLnsStream = {}", JsonPrint.getInstance().toPrettyJson(reqLnsStream));
		}
		
		LnsJson resLnsJson = null;
		LnsStream resLnsStream = null;
		if (Flag.flag) {
			String response = this.callStreamClient(reqLnsStream.getData());
			resLnsStream = new LnsStream(response);
			
			resLnsJson = (LnsJson) reqLnsJson.clone();
			resLnsJson.setTrType("0210");
			resLnsJson.setTrCode("100");
			resLnsJson.setResStrData(resLnsStream.getData());
			
			log.info("LNS01 >>>>> 1. resLnsJson = {}", JsonPrint.getInstance().toPrettyJson(resLnsJson));
			log.info("LNS01 >>>>> 2. resLnsStream = {}", JsonPrint.getInstance().toPrettyJson(resLnsStream));
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
		LnsMessage message = new LnsMessage();
		message.setData(req);
		
		this.streamClient.getThread().setMessage(message);
		
		String res = message.getDataFromQueue();
		return res;
	}
}
