package org.tain.scheduler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.tain.config.SkipSSLConfig;
import org.tain.data.AccessToken;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthScheduler {

	@Scheduled(fixedRate = 10 * 60 * 1000)
	public void scheduleJob() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (!Flag.flag) Sleep.run(10 * 1000);
		
		httpPostAuth();
	}

	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	@Value("${lightnet.url}")
	private String lightnetUrl;
	private String POST_AUTH_HTTP_URL = "/v1/auth";
	
	private void httpPostAuth() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		String reqJson = ""
			+ "{"
			+ "\"clientId\": \"pkey_tUsjZ1aL8UhvJnNibssfEGo6Y4MhSzXT\","
			+ "\"secret\": \"skey_D1ZL5MW4bKW7clFW2Vz3jH8sm2k7FUfWiu5wh1aL8Uivo6RMNOa74wxfSYo5ylmk\""
			+ "}";
		
		if (Flag.flag) {
			AccessToken.set(null);
			
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);

			RestTemplate restTemplate = SkipSSLConfig.getRestTemplate(1);
			ResponseEntity<String> response = null;
			for (int i=0; i < 5; i++) {
				response = restTemplate.exchange(lightnetUrl + POST_AUTH_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
				
				AccessToken.set(response.getHeaders().get("AccessToken").get(0));
				
				log.info("=====================================================");
				log.info("KANG-20200623 >>>>> lightnetUrl        = {}", lightnetUrl);
				log.info("KANG-20200623 >>>>> POST_AUTH_HTTP_URL = {}", POST_AUTH_HTTP_URL);
				log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
				log.info("KANG-20200623 >>>>> AccessToken = {}", AccessToken.get());
				log.info("=====================================================");
				
				if (response.getStatusCodeValue() == 200) {
					break;
				}
				
				log.info("KANG-20200618 >>>>> {} after 5sec, retry to connect.....", CurrentInfo.get());
				try { Thread.sleep(5000); } catch (InterruptedException e) {}
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void httpPostAuth0() throws Exception {
		Map<String,Object> reqMap = null;
		if (Flag.flag) {
			String reqJson = "{"
				+ "\"clientId\": \"pkey_tUsjZ1aL8UhvJnNibssfEGo6Y4MhSzXT\","
				+ "\"secret\": \"skey_D1ZL5MW4bKW7clFW2Vz3jH8sm2k7FUfWiu5wh1aL8Uivo6RMNOa74wxfSYo5ylmk\""
				+ "}";
			reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,Object>>(){});
		}
		
		if (Flag.flag) {
			AccessToken.set(null);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("clientId", (String) reqMap.get("clientId"));
			parameters.put("secret", (String) reqMap.get("secret"));
			
			HttpEntity<Map<String,Object>> request = new HttpEntity<>(parameters, headers);

			RestTemplate restTemplate = SkipSSLConfig.getRestTemplate(1);
			for (int i=0; i < 5; i++) {
				ResponseEntity<String> response = restTemplate.exchange(POST_AUTH_HTTP_URL, HttpMethod.POST, request, String.class);
				
				//response.getStatusCodeValue();
				//response.getStatusCode();
				//response.getHeaders();
				//response.getBody();
				
				String accessToken = response.getHeaders().get("AccessToken").get(0);
				AccessToken.set(accessToken);
				
				log.info("=====================================================");
				log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
				log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
				log.info("KANG-20200623 >>>>> AccessToken = {}", AccessToken.get());
				log.info("=====================================================");
				
				if (response.getStatusCodeValue() == 200) {
					break;
				}
				
				log.info("KANG-20200618 >>>>> {} after 5sec, retry to connect.....", CurrentInfo.get());
				try { Thread.sleep(5000); } catch (InterruptedException e) {}
			}
		}
	}
}
