package org.tain.task;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.data.AccessToken;
import org.tain.object.auth.req._ReqAuthData;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.Sleep;
import org.tain.utils.enums.RestTemplateType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthJob {

	///////////////////////////////////////////////////////////////////////////
	
	private long LOOP_SEC = 10;
	
	@Async
	public void authJob(String param) {
		log.info("KANG-20200908 >>>>> START param = {}, {}", param, CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				do {
					log.info(">>>>> AuthJob.process()..");
					process();
					
					Sleep.run(LOOP_SEC * 1000);
				} while (true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		log.info("KANG-20200907 >>>>> END   param = {}, {}", param, CurrentInfo.get());
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private String clientId = "pkey_tUsjZ1aL8UhvJnNibssfEGo6Y4MhSzXT";
	private String secret = "skey_D1ZL5MW4bKW7clFW2Vz3jH8sm2k7FUfWiu5wh1aL8Uivo6RMNOa74wxfSYo5ylmk";
	
	private void process() {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		_ReqAuthData reqAuthData = null;
		if (Flag.flag) {
			reqAuthData = new _ReqAuthData();
			reqAuthData.setClientId(clientId);
			reqAuthData.setSecret(secret);
		}
		
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(JsonPrint.getInstance().toJson(reqAuthData), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						"http://localhost:18888/auth"
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				AccessToken.set(response.getHeaders().get("AccessToken").get(0));
				
				log.info("===============================================================");
				log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> accessToken          = {}", AccessToken.get());
				log.info(">>>>> getBody()            = {}", response.getBody());
				log.info("===============================================================");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
