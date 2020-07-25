package org.tain.scheduler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tain.data.AccessToken;
import org.tain.properties.LnsEnvAuthProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.Sleep;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthScheduler {

	@Autowired
	private LnsEnvAuthProperties lnsEnvAuthProperties;
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////

	@Scheduled(fixedRate = 1 * 60 * 1000)    // 10 minutes
	//@Scheduled(fixedRate = 20 * 60 * 1000)    // 20 minutes
	public void scheduleJob() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (AccessToken.get() == null) {
			AccessToken.set(this.lnsEnvAuthProperties.getAccessToken());
		}
		
		if (!Flag.flag) Sleep.run(10 * 1000);
		
		if (Flag.flag) this.authRefresh();
	}

	public void authRefresh() throws Exception {
		Map<String,String> mapReq = new HashMap<>();
		mapReq.put("clientId", "pkey_tUsjZ1aL8UhvJnNibssfEGo6Y4MhSzXT");
		mapReq.put("secret", "skey_D1ZL5MW4bKW7clFW2Vz3jH8sm2k7FUfWiu5wh1aL8Uivo6RMNOa74wxfSYo5ylmk");
		this.httpPostAuth(mapReq);
	}
	
	public void authRefresh(String reqJson) throws Exception {
		if (reqJson == null) {
			authRefresh();
		} else {
			Map<String,String> mapReq = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
			this.httpPostAuth(mapReq);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	private String POST_AUTH_HTTP_URL = "/v1/auth";
	
	private int idxUrl = 0;
	private int cntUrl = -1;
	
	private synchronized void httpPostAuth(Map<String,String> mapReq) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.cntUrl = this.lnsEnvAuthProperties.getLightnetUrl().length;
			this.idxUrl = this.lnsEnvAuthProperties.getLightnetStartIdx();
		}
		
		if (Flag.flag) {
			AccessToken.set(null);
			
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Map<String,String>> reqHttpEntity = new HttpEntity<>(mapReq, reqHeaders);
			
			for (;this.idxUrl >= 0 && this.idxUrl < this.cntUrl; this.idxUrl = (++ this.idxUrl) % this.cntUrl) {
				try {
					log.info("================ idxUrl: {}, cntUrl: {} ================", this.idxUrl, this.cntUrl);
					log.info("KANG-20200623 >>>>> lightnetUrl        = {}", this.lnsEnvAuthProperties.getLightnetUrl()[this.idxUrl]);
					log.info("KANG-20200623 >>>>> POST_AUTH_HTTP_URL = {}", POST_AUTH_HTTP_URL);
					
					ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							this.lnsEnvAuthProperties.getLightnetUrl()[idxUrl] + POST_AUTH_HTTP_URL
							, HttpMethod.POST
							, reqHttpEntity
							, String.class);
					
					AccessToken.set(response.getHeaders().get("AccessToken").get(0));
					
					log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
					log.info("KANG-20200623 >>>>> AccessToken = {}", AccessToken.get());
					log.info("=====================================================");
					if (response.getStatusCodeValue() == 200)
						break;
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("KANG-20200724 >>>>> Exception.message = {}", e.getMessage());
				}
			}
		}
	}
}
