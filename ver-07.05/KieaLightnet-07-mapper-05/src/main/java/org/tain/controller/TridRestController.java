package org.tain.controller;

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
import org.tain.object.lns.LnsMap;
import org.tain.object.lns.LnsStream;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/mapper/trid"})
@Slf4j
public class TridRestController {

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/s2j"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> tridStrToJson(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println("MAPPER >>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println("MAPPER >>>>> Body = " + _httpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		LnsStream lnsStream = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(_httpEntity.getBody(), LnsJson.class);
			lnsStream = new LnsStream(lnsJson.getReqStrData());
			log.info("LNS01 >>>>> 1. lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
			log.info("LNS01 >>>>> 2. lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
		}
		
		LnsMap lnsMap = null;
		if (Flag.flag) {
			lnsMap = this.strToJson(lnsStream);
			lnsJson.setReqJsonData(lnsMap.toJson());
			
			log.info("LNS01 >>>>> 1. lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
			log.info("LNS01 >>>>> 2. lnsMap = {}", JsonPrint.getInstance().toPrettyJson(lnsMap));
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private LnsMap strToJson(LnsStream lnsStream) throws Exception {
		LnsMap lnsMap = new LnsMap();
		
		lnsMap.put("strLength", lnsStream.getStrLength());
		lnsMap.put("trTypeCode", lnsStream.getTrTypeCode());
		lnsMap.put("content", lnsStream.getContent());
		lnsMap.reset();
		
		return lnsMap;
	}
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	@CrossOrigin(origins = {"/**"})
	@RequestMapping(value = {"/j2s"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> tridJsonToStr(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println("MAPPER >>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println("MAPPER >>>>> Body = " + _httpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		LnsMap lnsMap = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(_httpEntity.getBody(), LnsJson.class);
			lnsMap = new LnsMap(lnsJson.getResJsonData());
			log.info("LNS01 >>>>> 1. lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
			log.info("LNS01 >>>>> 2. lnsMap = {}", JsonPrint.getInstance().toPrettyJson(lnsMap));
		}
		
		LnsStream lnsStream = null;
		if (Flag.flag) {
			lnsStream = this.jsonToStr(lnsMap);
			lnsJson.setResStrData(lnsStream.getData());
			
			log.info("LNS01 >>>>> 1. lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
			log.info("LNS01 >>>>> 2. lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private LnsStream jsonToStr(LnsMap lnsMap) throws Exception {
		
		//StringBuffer sb = new StringBuffer("00530702RESHWyymmddhhmmA999    ............OK        ");
		StringBuffer sb = new StringBuffer();
		sb.append("0702");
		sb.append("RES");
		sb.append(String.format("%-20s", lnsMap.get("trid")));
		sb.append("00000");
		sb.append("SUCCESS   ");
		sb.insert(0, String.format("%04d", sb.length() + 4));
		
		LnsStream lnsStream = new LnsStream(sb.toString());
		
		return lnsStream;
	}
}
