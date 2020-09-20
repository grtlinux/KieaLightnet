package org.tain.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tain.object.auth.req._ReqAuthData;
import org.tain.object.auth.res._ResAuthData;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Deprecated
@Component
@Slf4j
public class AccessToken {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	public String get() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		_ReqAuthData reqAuthData = null;
		if (Flag.flag) {
			reqAuthData = new _ReqAuthData();
		}
		
		_ResAuthData resAuthData = null;
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(JsonPrint.getInstance().toJson(reqAuthData), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						this.projEnvUrlProperties.getAuth() + "/auth"
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info("===============================================================");
				log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> getBody()            = {}", response.getBody());
				log.info("===============================================================");
				
				resAuthData = new ObjectMapper().readValue(response.getBody(), _ResAuthData.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String accessToken = resAuthData.getAccessToken();
		
		return accessToken;
	}
}
