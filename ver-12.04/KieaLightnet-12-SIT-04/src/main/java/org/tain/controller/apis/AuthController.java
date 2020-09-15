package org.tain.controller.apis;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tain.data.LnsData;
import org.tain.object.auth.req._ReqAuthData;
import org.tain.object.lns.LnsReqWeb;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/apis/auth"})
@Slf4j
public class AuthController {

	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> http(HttpEntity<String> reqHttpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("SIT >>>>> Headers = {}", reqHttpEntity.getHeaders());
			log.info("SIT >>>>> Body = {}", reqHttpEntity.getBody());
		}
		
		String resJson = null;
		if (Flag.flag) {
			LnsReqWeb lnsReqWeb = new ObjectMapper().readValue(reqHttpEntity.getBody(), LnsReqWeb.class);
			resJson = this.auth(lnsReqWeb);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(resJson, headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private String auth(LnsReqWeb lnsReqWeb) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== START: 1. authentication(POST) =============");
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqAuthData reqData = new ObjectMapper().readValue(lnsReqWeb.getReqJson(), _ReqAuthData.class);
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				log.info(">>>>> REQ.reqJson        = {}", reqJson);
			}
			
			String httpUrl = null;
			HttpMethod httpMethod = null;
			HttpEntity<String> reqHttpEntity = null;
			ResponseEntity<String> response = null;
			
			if (Flag.flag) {
				// post method
				httpUrl = lnsReqWeb.getHttpUrl();
				httpMethod = HttpMethod.POST;
				log.info(">>>>> REQ.httpUrl        = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			}
			
			if (Flag.flag) {
				try {
					response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, httpMethod
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> RES.getBody()            = {}", response.getBody());
					reqJson = response.getBody();
					
					LnsData.getInstance().setAccessToken(response.getHeaders().get("AccessToken").get(0));
					log.info(">>>>> RES.accessToken          = {}", LnsData.getInstance().getAccessToken());
				} catch (Exception e) {
					//e.printStackTrace();
					String message = e.getMessage();
					log.error("ERROR >>>>> {}", message);
					int pos1 = message.indexOf('[');
					int pos2 = message.lastIndexOf(']');
					reqJson = message.substring(pos1 + 1, pos2);
				}
			}
			log.info("===============================================================");
			
			return reqJson;
		}
		
		return null;
	}
}
