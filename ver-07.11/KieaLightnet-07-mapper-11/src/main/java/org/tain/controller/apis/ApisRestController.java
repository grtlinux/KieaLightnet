package org.tain.controller.apis;

import java.io.File;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.mapper.LnsJsonNode;
import org.tain.mapper.LnsJsonToStream;
import org.tain.mapper.LnsMstInfo;
import org.tain.mapper.LnsStreamToJson;
import org.tain.task.MapperReaderJob;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/mapper"})
@Slf4j
public class ApisRestController {

	@Autowired
	private MapperReaderJob mapperReaderJob;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	 * TODO: kang_template
	 * http://localhost:18087/v1.1/mapper/s2j
	 */
	@RequestMapping(value = {"/s2j"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> strToJson(HttpEntity<String> reqHttpEntity) throws Exception {
		if (Flag.flag) log.info("========================== START: /mapper/s2j =========================");
		if (Flag.flag) log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		String body = null;
		if (Flag.flag) {
			body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
			log.info("MAPPER.cstruct >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.cstruct >>>>> Body = {}", body);
		}
		
		LnsJsonNode reqJsonNode = null;
		if (Flag.flag) {
			reqJsonNode = new LnsJsonNode(body);
			log.info("MAPPER.cstruct >>>>> reqJsonNode = {}", reqJsonNode.toPrettyString());
		}
		
		LnsJsonNode resJsonNode = null;
		if (Flag.flag) {
			resJsonNode = new LnsJsonNode("{}");
		}
		
		if (Flag.flag) {
			LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(reqJsonNode.getText("reqResType"));
			JsonNode node = new LnsStreamToJson(lnsMstInfo, reqJsonNode.getText("stream")).get();
			resJsonNode.put("json", node);
			log.info("MAPPER.req >>>>> resJsonNode = {}", resJsonNode.toPrettyString());
		}
		
		MultiValueMap<String,String> headers = null;
		if (Flag.flag) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		if (Flag.flag) log.info("-------------------------- END: /mapper/s2j -------------------------\n\n");
		
		return new ResponseEntity<>(resJsonNode.toPrettyString(), headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	 * http://localhost:18087/v1.1/mapper/j2s
	 */
	@RequestMapping(value = {"/j2s"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> jsonToStr(HttpEntity<String> reqHttpEntity) throws Exception {
		if (Flag.flag) log.info("========================== START: /mapper/j2s =========================");
		if (Flag.flag) log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		String body = null;
		if (Flag.flag) {
			body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
			log.info("MAPPER.cstruct >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.cstruct >>>>> Body = {}", body);
		}
		
		LnsJsonNode reqJsonNode = null;
		if (Flag.flag) {
			reqJsonNode = new LnsJsonNode(body);
			log.info("MAPPER.cstruct >>>>> reqJsonNode = {}", reqJsonNode.toPrettyString());
		}
		
		LnsJsonNode resJsonNode = null;
		if (Flag.flag) {
			resJsonNode = new LnsJsonNode("{}");
		}
		
		if (Flag.flag) {
			LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(reqJsonNode.getText("reqResType"));
			String stream = new LnsJsonToStream(lnsMstInfo, reqJsonNode.getJsonNode("json").toPrettyString()).get();
			resJsonNode.put("stream", stream);
			log.info("MAPPER.req >>>>> resJsonNode = {}", resJsonNode.toPrettyString());
		}
		
		MultiValueMap<String,String> headers = null;
		if (Flag.flag) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		if (Flag.flag) log.info("-------------------------- END: /mapper/j2s -------------------------\n\n");
		
		return new ResponseEntity<>(resJsonNode.toPrettyString(), headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	 * curl -v -d '{"reqResType":"0200100"}' -X POST http://localhost:18087/v1.1/mapper/cstruct
	 */
	@RequestMapping(value = {"/cstruct"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> cstruct(HttpEntity<String> reqHttpEntity) throws Exception {
		if (Flag.flag) log.info("========================== START: /mapper/cstruct =========================");
		if (Flag.flag) log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		String body = null;
		if (Flag.flag) {
			body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
			log.info("MAPPER.cstruct >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.cstruct >>>>> Body = {}", body);
		}
		
		LnsJsonNode reqJsonNode = null;
		if (Flag.flag) {
			reqJsonNode = new LnsJsonNode(body);
			log.info("MAPPER.cstruct >>>>> reqJsonNode = {}", reqJsonNode.toPrettyString());
		}
		
		LnsJsonNode resJsonNode = null;
		if (Flag.flag) {
			resJsonNode = new LnsJsonNode("{}");
		}
		
		if (Flag.flag) {
			LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(reqJsonNode.getText("reqResType"));
			//String strCStruct = new LnsCStruct(lnsMstInfo).get();
			String strCStruct = lnsMstInfo.getCStruct();
			
			resJsonNode.put("cstruct", strCStruct);
			log.info("MAPPER.cstruct >>>>> resJsonNode = {}", resJsonNode.toPrettyString());
		}
		
		MultiValueMap<String,String> headers = null;
		if (Flag.flag) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		if (Flag.flag) log.info("-------------------------- END: /mapper/cstruct -------------------------\n\n");
		
		return new ResponseEntity<>(resJsonNode.toPrettyString(), headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	/*
	 * http://localhost:18087/v1.1/mapper/info/get
	 * $ curl -d '{"reqResType": "0700000"}' -H 'Context-Type: application/json' -X POST http://localhost:18087/v1.1/mapper/info/get
	 */
	@RequestMapping(value = {"/info/get"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> infoGet(HttpEntity<String> reqHttpEntity) throws Exception {
		if (Flag.flag) log.info("========================== START: /mapper/info/get =========================");
		if (Flag.flag) log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		String body = null;
		if (Flag.flag) {
			body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
			log.info("MAPPER.cstruct >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.cstruct >>>>> Body = {}", body);
		}
		
		LnsJsonNode reqJsonNode = null;
		if (Flag.flag) {
			reqJsonNode = new LnsJsonNode(body);
			log.info("MAPPER.cstruct >>>>> reqJsonNode = {}", reqJsonNode.toPrettyString());
		}
		
		LnsJsonNode resJsonNode = null;
		if (Flag.flag) {
			resJsonNode = new LnsJsonNode("{}");
		}
		
		if (Flag.flag) {
			LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(reqJsonNode.getText("reqResType"));
			String jsonInfo = lnsMstInfo.getStrJsonInfo();
			resJsonNode.put("jsonInfo", jsonInfo);
			log.info("MAPPER.infoGet >>>>> jsonInfo = {}", jsonInfo);
		}
		
		MultiValueMap<String,String> headers = null;
		if (Flag.flag) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		if (Flag.flag) log.info("-------------------------- END: /mapper/info/get -------------------------\n\n");
		
		return new ResponseEntity<>(resJsonNode.toPrettyString(), headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	/*
	 * http://localhost:17087/v0.6/mapper/info/save
	 */
	@RequestMapping(value = {"/info/save"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> infoSave(HttpEntity<String> reqHttpEntity) throws Exception {
		if (Flag.flag) log.info("========================== START: /mapper/info/save =========================");
		if (Flag.flag) log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		String body = null;
		if (Flag.flag) {
			body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
			log.info("MAPPER.cstruct >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.cstruct >>>>> Body = {}", body);
		}
		
		LnsJsonNode reqJsonNode = null;
		if (Flag.flag) {
			reqJsonNode = new LnsJsonNode(body);
			log.info("MAPPER.cstruct >>>>> reqJsonNode = {}", reqJsonNode.toPrettyString());
		}
		
		LnsJsonNode resJsonNode = null;
		if (Flag.flag) {
			resJsonNode = new LnsJsonNode("{}");
		}
		
		LnsMstInfo lnsMstInfo = null;
		if (Flag.flag) {
			lnsMstInfo = this.mapperReaderJob.get(reqJsonNode.getText("reqResType"));
		}
		
		if (Flag.flag) {
			// rename
			String srcPath = lnsMstInfo.getFilePath();
			String dstPath = lnsMstInfo.getFilePath() + "_" + StringTools.getYYMMDDHHMMSS();
			File file = new File(srcPath);
			boolean isSuccess = file.renameTo(new File(dstPath));
			log.info("MAPPER.rename file -------------\nFROM: {}\nTO: {}\nSTATUS: {}", srcPath, dstPath, isSuccess);
		}
		
		if (Flag.flag) {
			// save
			String filePath = lnsMstInfo.getFilePath();
			String jsonInfo = reqJsonNode.getText("jsonInfo");
			
			StringTools.stringToFile(jsonInfo, filePath);
			
			resJsonNode.put("status", "update success");
			resJsonNode.put("message", "UPDATE OK");
			log.info("MAPPER.infoSave >>>>> resJsonNode = {}", resJsonNode.toPrettyString());
		}
		
		MultiValueMap<String,String> headers = null;
		if (Flag.flag) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		if (Flag.flag) log.info("-------------------------- END: /mapper/info/save -------------------------\n\n");
		
		return new ResponseEntity<>(resJsonNode.toPrettyString(), headers, HttpStatus.OK);
	}
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	/*
	 * http://localhost:18087/v1.1/mapper/info/headbase
	 * $ curl -d '{"reqResType": "0700000"}' -H 'Context-Type: application/json' -X POST http://localhost:18087/v1.1/mapper/info/headbase
	 */
	@RequestMapping(value = {"/info/headbase"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> infoHeadBase(HttpEntity<String> reqHttpEntity) throws Exception {
		if (Flag.flag) log.info("========================== START: /mapper/info/headbase =========================");
		if (Flag.flag) log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		String body = null;
		if (Flag.flag) {
			body = URLDecoder.decode(reqHttpEntity.getBody(), "utf-8");
			log.info("MAPPER.cstruct >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("MAPPER.cstruct >>>>> Body = {}", body);
		}
		
		LnsJsonNode reqJsonNode = null;
		if (Flag.flag) {
			reqJsonNode = new LnsJsonNode(body);
			log.info("MAPPER.cstruct >>>>> reqJsonNode = {}", reqJsonNode.toPrettyString());
		}
		
		LnsJsonNode resJsonNode = null;
		if (Flag.flag) {
			resJsonNode = new LnsJsonNode("{}");
		}
		
		if (Flag.flag) {
			LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(reqJsonNode.getText("reqResType"));
			resJsonNode.put("jsonInfo", lnsMstInfo.getHeadBaseInfoNode());
			String jsonInfo = resJsonNode.getJsonNode("jsonInfo").toPrettyString();
			log.info("MAPPER.infoGet >>>>> jsonInfo = {}", jsonInfo);
		}
		
		MultiValueMap<String,String> headers = null;
		if (Flag.flag) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		if (Flag.flag) log.info("-------------------------- END: /mapper/info/headbase -------------------------\n\n");
		
		return new ResponseEntity<>(resJsonNode.toPrettyString(), headers, HttpStatus.OK);
	}
}
