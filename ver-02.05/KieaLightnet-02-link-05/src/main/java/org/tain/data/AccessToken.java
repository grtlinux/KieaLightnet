package org.tain.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tain.properties.LnsEnvLinkProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccessToken {

	@Autowired
	private LnsEnvLinkProperties lnsEnvLinkProperties;
	
	public String getAccessToken(String subTitle) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		Map<String,String> mapReq = new HashMap<>();
		if (Flag.flag) {
			// TODO: make
			mapReq.put("title", "/link" + subTitle);
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Map<String,String>> request = new HttpEntity<>(mapReq, headers);
			
			response = RestTemplateConfig.get(RestTemplateType.NORMAL).exchange(
					this.lnsEnvLinkProperties.getAuthUrl()
					, HttpMethod.POST
					, request
					, String.class);
		}
		
		Map<String,String> mapRes = new HashMap<>();
		if (Flag.flag) {
			mapRes = new ObjectMapper().readValue(response.getBody(), new TypeReference<Map<String,String>>(){});
		}
		
		String accessToken = null;
		if (Flag.flag) {
			accessToken = mapRes.get("accessToken");
		}
		
		return accessToken;
	}
}
