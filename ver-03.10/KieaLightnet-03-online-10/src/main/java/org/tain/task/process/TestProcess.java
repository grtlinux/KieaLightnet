package org.tain.task.process;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.tain.object.lns.LnsJson;
import org.tain.object.lns.LnsStream;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestProcess {

	public static LnsStream process(LnsStream reqLnsStream) throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			// 1. LnsJson
			lnsJson = LnsJson.builder()
					.name("TEST")
					.title("This is for test.")
					.reqStrData(reqLnsStream.getContent())
					.build();
			log.info("ONLINE-1 >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) {
			// 2. mapper TestReq Stream to Json
			lnsJson = mapperReqStrToJson(lnsJson);
			log.info("ONLINE-2 >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) {
			// 3. post link
			lnsJson = httpLinkPost(lnsJson);
			log.info("ONLINE-3 >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		if (Flag.flag) {
			// 4. mapper TestRes Json to Stream
			lnsJson = mapperResJsonToStr(lnsJson);
			log.info("ONLINE-4 >>>>> lnsJson = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
		}
		
		LnsStream resLnsStream = null;
		if (Flag.flag) {
			// 5. lnsStream
			String resStream = lnsJson.getResStrData();
			resLnsStream = new LnsStream(String.format("%04d%7.7s%s", resStream.length() + 7, "0210991", resStream));
		}
		
		return resLnsStream;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public static LnsJson mapperReqStrToJson(LnsJson lnsJson) throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(JsonPrint.getInstance().toJson(lnsJson), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						"http://localhost:18086/v1.0/mapper/test/req/s2j"
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info("===============================================================");
				log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> getBody()            = {}", response.getBody());
				log.info("===============================================================");
				
				lnsJson = new ObjectMapper().readValue(response.getBody(), LnsJson.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return lnsJson;
	}
	///////////////////////////////////////////////////////////////////////////
	
	public static LnsJson httpLinkPost(LnsJson lnsJson) throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(JsonPrint.getInstance().toJson(lnsJson), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						"http://localhost:18082/v1.0/link/test/get"
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info("===============================================================");
				log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> getBody()            = {}", response.getBody());
				log.info("===============================================================");
				
				lnsJson = new ObjectMapper().readValue(response.getBody(), LnsJson.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return lnsJson;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public static LnsJson mapperResJsonToStr(LnsJson lnsJson) throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(JsonPrint.getInstance().toJson(lnsJson), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						"http://localhost:18086/v1.0/mapper/test/res/j2s"
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info("===============================================================");
				log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> getBody()            = {}", response.getBody());
				log.info("===============================================================");
				
				lnsJson = new ObjectMapper().readValue(response.getBody(), LnsJson.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return lnsJson;
	}
}
