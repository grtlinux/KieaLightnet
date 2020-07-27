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
public class TransactionIdController {

	
	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/trid"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> validate(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			log.info("LNS01 >>>>> Headers = {}", _httpEntity.getHeaders());
			log.info("LNS01 >>>>> Body = {}", _httpEntity.getBody());
		}
		
		LnsJson reqJson = null;
		LnsStream reqStream = null;
		if (Flag.flag) {
			reqJson = new ObjectMapper().readValue(_httpEntity.getBody(), LnsJson.class);
			reqStream = new LnsStream(reqJson.getReqData());
			log.info("LNS01 >>>>> reqJson = {}", reqJson.toPrettyJson());
			log.info("LNS01 >>>>> reqStream = {}", reqStream.toPrettyJson());
		}
		
		LnsJson resJson = null;
		LnsStream resStream = null;
		if (Flag.flag) {
			resJson = (LnsJson) reqJson.clone();
			
			resStream = (LnsStream) reqStream.clone();
			resStream.setDivision("0702");
			resStream.setDivisionType("RES");
			resStream.setTrid("HW34567890123456");
			resStream.setContent(".............................OK");
			resStream.combind();
			
			resJson.setDivisionType("RES");
			resJson.setResData(resStream.getData());
			resJson.setCode("00000");
			resJson.setMessage("MSG: to get the trid..");
			
			log.info("LNS01 >>>>> resJson = {}", resJson.toPrettyJson());
		}
		
		/*
		Map<String,String> mapReq = null;
		if (Flag.flag) {
			mapReq = new ObjectMapper().readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
		}
		
		// transaction id
		String trid = "";
		if (Flag.flag) {
			//String reqTrid = "00530701REQ................................          ";
			String reqTrid = mapReq.get("data");
			String resTrid = this.callStreamClient(reqTrid);  // IN:stream OUT:stream
			trid = resTrid.substring(11, 43).trim();
			System.out.println(">>>>> trid = " + trid);
		}
		*/
		
		/*
		// validate stream
		String response = "00000102  temp data.123456789012345678901234567890...................... not real";
		if (!Flag.flag) {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, String> map = objectMapper.readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
			
			response = this.callStreamClient(map.get("data"));  // IN:stream OUT:stream
		}
		*/
		
		/*
		String response = "00000102  temp data.123456789012345678901234567890...................... not real";
		
		Map<String,Object> mapRes = null;
		if (Flag.flag) {
			mapRes = new HashMap<>();
			
			mapRes.put("title", "/lns01/transactionId");
			mapRes.put("createdDate", LocalDateTime.now());
			mapRes.put("response", response);
			
			System.out.println("LNS01 >>>>> retMap = " + mapRes);
		}
		*/
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(resJson, headers, HttpStatus.OK);
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
