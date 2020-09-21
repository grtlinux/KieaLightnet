package org.tain.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.object.amend.req._ReqAmendData;
import org.tain.object.amend.res._ResAmendData;
import org.tain.object.lns.LnsJson;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.SubString;
import org.tain.utils.TransferStrAndJson;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/mapper/amend"})
@Slf4j
public class AmendRestController {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	 * http://localhost:18086/v1.0/mapper/amend/req/s2j
	 */
	@RequestMapping(value = {"/req/s2j"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> amendReqStrToJson(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER.req >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.req >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
			
			TransferStrAndJson.subString = new SubString(lnsJson.getReqStrData());
			_ReqAmendData reqData = new _ReqAmendData();
			reqData = (_ReqAmendData) TransferStrAndJson.getObject(reqData);
			
			lnsJson.setReqJsonData(JsonPrint.getInstance().toJson(reqData));
			log.info("MAPPER.req >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	/*
	 * http://localhost:18086/v1.0/mapper/amend/res/s2j
	 */
	@RequestMapping(value = {"/res/s2j"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> amendResStrToJson(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER.res >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.res >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
			
			TransferStrAndJson.subString = new SubString(lnsJson.getResStrData());
			_ResAmendData resData = new _ResAmendData();
			resData = (_ResAmendData) TransferStrAndJson.getObject(resData);
			
			lnsJson.setResJsonData(JsonPrint.getInstance().toJson(resData));
			log.info("MAPPER.res >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	 * http://localhost:18086/v1.0/mapper/amend/req/j2s
	 */
	@RequestMapping(value = {"/req/j2s"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> amendReqJsonToStr(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER.req >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.req >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
			
			_ReqAmendData reqData = new ObjectMapper().readValue(lnsJson.getReqJsonData(), _ReqAmendData.class);
			String reqStream = TransferStrAndJson.getStream(reqData);
			lnsJson.setReqStrData(reqStream);
			log.info("MAPPER.req >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	/*
	 * http://localhost:18086/v1.0/mapper/amend/res/j2s
	 */
	@RequestMapping(value = {"/res/j2s"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> amendResJsonToStr(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER.res >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.res >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
			
			_ResAmendData resData = new ObjectMapper().readValue(lnsJson.getResJsonData(), _ResAmendData.class);
			String resStream = TransferStrAndJson.getStream(resData);
			lnsJson.setResStrData(resStream);
			log.info("MAPPER.res >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	 * http://localhost:18086/v1.0/mapper/amend/req/cstruct
	 */
	@RequestMapping(value = {"/req/cstruct"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> detailReqCStruct(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER.req >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.req >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
			
			//_ReqAmendData reqData = new ObjectMapper().readValue(lnsJson.getReqJsonData(), _ReqAmendData.class);
			String reqCStruct = TransferStrAndJson.getCStruct(new _ReqAmendData());
			lnsJson.setBatData(reqCStruct);
			log.info("MAPPER.req >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	/*
	 * http://localhost:18086/v1.0/mapper/amend/res/cstruct
	 */
	@RequestMapping(value = {"/res/cstruct"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> amendResCStruct(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER.res >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.res >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
			
			//_ResAmendData resData = new ObjectMapper().readValue(lnsJson.getResJsonData(), _ResAmendData.class);
			String resCStruct = TransferStrAndJson.getCStruct(new _ResAmendData());
			lnsJson.setBatData(resCStruct);
			log.info("MAPPER.res >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
}
