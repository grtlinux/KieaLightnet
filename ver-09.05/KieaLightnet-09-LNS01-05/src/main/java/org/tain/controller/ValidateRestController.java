package org.tain.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.object.lns.LnsMessage;
import org.tain.socket.StreamClient;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/lns01"})
@Slf4j
public class ValidateRestController {

	
	@PostMapping(value = {"/validate"})
	public ResponseEntity<?> validate(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			System.out.println("LNS01 >>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println("LNS01 >>>>> Body = " + _httpEntity.getBody());
		}
		
		// transaction id
		String trid = "";
		if (Flag.flag) {
			String reqTrid = "00000701REQ................................          ";
			String resTrid = this.callStreamClient(reqTrid);  // IN:stream OUT:stream
			trid = resTrid.substring(11, 43);
			System.out.println(">>>>> trid = " + trid);
		}
		
		// validate stream
		String response = "00000102  temp data.123456789012345678901234567890...................... not real";
		if (!Flag.flag) {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, String> map = objectMapper.readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
			
			response = this.callStreamClient(map.get("data"));  // IN:stream OUT:stream
		}
		
		Map<String,Object> map = null;
		if (Flag.flag) {
			map = new HashMap<>();
			
			map.put("title", "/lns01/validate");
			map.put("createdDate", LocalDateTime.now());
			map.put("response", response);
			
			System.out.println("LNS01 >>>>> retMap = " + map);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
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
