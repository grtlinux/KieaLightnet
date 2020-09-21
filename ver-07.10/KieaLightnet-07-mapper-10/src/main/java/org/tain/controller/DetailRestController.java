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
import org.tain.object.detail.req._ReqDetailData;
import org.tain.object.detail.res._ResDetailData;
import org.tain.object.lns.LnsJson;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.SubString;
import org.tain.utils.TransferStrAndJson;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/mapper/detail"})
@Slf4j
public class DetailRestController {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	 * http://localhost:18086/v1.0/mapper/detail/req/s2j
	 */
	@RequestMapping(value = {"/req/s2j"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> detailReqStrToJson(HttpEntity<String> reqHttpEntity) throws Exception {
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
			_ReqDetailData reqData = new _ReqDetailData();
			reqData = (_ReqDetailData) TransferStrAndJson.getObject(reqData);
			
			lnsJson.setReqJsonData(JsonPrint.getInstance().toJson(reqData));
			log.info("MAPPER.req >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	/*
	 * http://localhost:18086/v1.0/mapper/detail/res/s2j
	 */
	@RequestMapping(value = {"/res/s2j"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> detailResStrToJson(HttpEntity<String> reqHttpEntity) throws Exception {
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
			_ResDetailData resData = new _ResDetailData();
			resData = (_ResDetailData) TransferStrAndJson.getObject(resData);
			
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
	 * http://localhost:18086/v1.0/mapper/detail/req/j2s
	 */
	@RequestMapping(value = {"/req/j2s"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> detailReqJsonToStr(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER.req >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.req >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
			
			_ReqDetailData reqData = new ObjectMapper().readValue(lnsJson.getReqJsonData(), _ReqDetailData.class);
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
	 * http://localhost:18086/v1.0/mapper/detail/res/j2s
	 */
	@RequestMapping(value = {"/res/j2s"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> detailResJsonToStr(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER.res >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.res >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
			
			_ResDetailData resData = new ObjectMapper().readValue(lnsJson.getResJsonData(), _ResDetailData.class);
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
	 * http://localhost:18086/v1.0/mapper/detail/req/cstruct
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
			
			//_ReqDetailData reqData = new ObjectMapper().readValue(lnsJson.getReqJsonData(), _ReqDetailData.class);
			String reqCStruct = TransferStrAndJson.getCStruct(new _ReqDetailData());
			lnsJson.setBatData(reqCStruct);
			log.info("MAPPER.req >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
	
	/*
	 * http://localhost:18086/v1.0/mapper/detail/res/cstruct
	 */
	@RequestMapping(value = {"/res/cstruct"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> detailResCStruct(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) log.info("========================================================");
		
		if (Flag.flag) {
			log.info("MAPPER.res >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.res >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsJson.class);
			
			//_ResDetailData resData = new ObjectMapper().readValue(lnsJson.getResJsonData(), _ResDetailData.class);
			String resCStruct = TransferStrAndJson.getCStruct(new _ResDetailData());
			lnsJson.setBatData(resCStruct);
			log.info("MAPPER.res >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) log.info("========================================================");
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lnsJson, headers, HttpStatus.OK);
	}
}
