package org.tain.data;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.tain.config.SkipSSLConfig;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessToken {

	private static String POST_LOCAL_AUTH_HTTP_URL = "http://localhost:18081/v0.1/auth/auth";

	public static String getAccessToken(String subTitle) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		String accessToken = null;
		
		String reqJson = null;
		if (Flag.flag) {
			reqJson = "{"
					+ "\"title\": \"/link" + subTitle + "\""
					+ "}";
		}
		
		ResponseEntity<String> response = null;
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(reqJson, headers);
			
			response = SkipSSLConfig.getRestTemplate(0).exchange(POST_LOCAL_AUTH_HTTP_URL, HttpMethod.POST, request, String.class);
			
			Map<String,Object> resMap = new ObjectMapper().readValue(response.getBody(), new TypeReference<Map<String,Object>>(){});
			
			accessToken = (String) resMap.get("accessToken");
		}
		
		return accessToken;
	}
}
