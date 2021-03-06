package org.tain.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.tain.config.SkipSSLConfig;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/link"})
@Slf4j
public class LinkController {

	@Value("${lightnet.url}")
	private String lightnetUrl;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	// {"status":"fail","message":"invalid transactionID or callerOperatorCode"}
	private String POST_COMMIT_HTTPS_URL = "/v1.1/remittances.commit";
	
	@PostMapping(value = {"/commit0"})
	public ResponseEntity<?> commit(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
		}
		
		String accessToken = getAccessToken("/commit");
		System.out.println(">>>>> accessToken = " + accessToken);

		ResponseEntity<String> response = null;

		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);

			HttpEntity<String> httpEntity = new HttpEntity<>(_httpEntity.getBody(), headers);

			RestTemplate restTemplate = SkipSSLConfig.getRestTemplate(1);
			for (int i=0; i < 5; i++) {
				response = restTemplate.exchange(lightnetUrl + POST_COMMIT_HTTPS_URL, HttpMethod.POST, httpEntity, String.class);
				
				log.info("=====================================================");
				log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
				log.info("KANG-20200623 >>>>> POST {}", POST_COMMIT_HTTPS_URL);
				log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
				log.info("KANG-20200623 >>>>> response.getBody()            = {}", response.getBody());
				log.info("=====================================================");
				
				if (response.getStatusCodeValue() != 200) {
					try { Thread.sleep(3000); } catch (InterruptedException e) {}
					continue;
				}
				
				if (Flag.flag) {
					// Pretty Print
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.registerModule(new JavaTimeModule());
					objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					
					JsonNode jsonNode = objectMapper.readTree(response.getBody());
					//String json = jsonNode.at("/").toPrettyString();
					String json = jsonNode.toPrettyString();
					System.out.println(">>>>> json: " + json);
				}
				break;
			}
		}
		
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	private String POST_VALIDATE0_HTTPS_URL = "/v1.1/remittances.validate";
	
	@PostMapping(value = {"/validate0"})
	public ResponseEntity<?> validate0(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
		}
		
		String accessToken = getAccessToken("/validate0");
		System.out.println(">>>>> accessToken = " + accessToken);

		String response = null;
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);
			
			HttpEntity<String> httpEntity = new HttpEntity<>(_httpEntity.getBody(), headers);
			
			response = SkipSSLConfig.getRestTemplate(1).postForObject(lightnetUrl + POST_VALIDATE0_HTTPS_URL, httpEntity, String.class);
			
			log.info("=====================================================");
			log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
			log.info("KANG-20200623 >>>>> POST {}", POST_VALIDATE0_HTTPS_URL);
			log.info("KANG-20200623 >>>>> response  = {}", response);
			log.info("=====================================================");
			
			System.out.println(">>>>> response: " + response);
			
			if (Flag.flag) {
				// Pretty Print
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				JsonNode jsonNode = objectMapper.readTree(response);
				//String json = jsonNode.at("/").toPrettyString();
				String json = jsonNode.toPrettyString();
				System.out.println(">>>>> json: " + json);
			}
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	*/
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	private String GET_LIST_HTTPS_URL = "/v1/remittances";
	//private String GET_LIST_HTTPS_URL = "/v1.1/remittances";
	
	@PostMapping(value = {"/list0"})
	public ResponseEntity<?> list(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		MultiValueMap<String, String> reqMap = new LinkedMultiValueMap<>();
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
			
			Map<String,String> map = new ObjectMapper().readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>() {});
			reqMap.setAll(map);
		}
		
		String accessToken = getAccessToken("/list");
		System.out.println(">>>>> accessToken = " + accessToken);

		ResponseEntity<String> response = null;
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);
			
			HttpEntity<String> httpEntity = new HttpEntity<>(headers);
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(lightnetUrl + GET_LIST_HTTPS_URL)
					.queryParams(reqMap)
					.build(true);
			
			response = SkipSSLConfig.getRestTemplate(1).exchange(builder.toString(), HttpMethod.GET, httpEntity, String.class);
			
			log.info("=====================================================");
			log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
			log.info("KANG-20200623 >>>>> GET {}", builder.toString());
			log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
			log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
			log.info("KANG-20200623 >>>>> response.getBody()            = {}", response.getBody());
			log.info("=====================================================");
			
			System.out.println(">>>>> response: " + response);
			
			if (Flag.flag) {
				// Pretty Print
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				JsonNode jsonNode = objectMapper.readTree(response.getBody());
				//String json = jsonNode.at("/").toPrettyString();
				String json = jsonNode.toPrettyString();
				System.out.println(">>>>> json: " + json);
			}
		}
		
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
	*/
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	//private String GET_LIST_HTTPS_URL = "/v1/remittances";
	private String GET_LIST1_HTTPS_URL = "/v1.1/remittances";
	
	@PostMapping(value = {"/list10"})
	public ResponseEntity<?> list1(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		MultiValueMap<String, String> reqMap = new LinkedMultiValueMap<>();
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
			
			Map<String,String> map = new ObjectMapper().readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>() {});
			reqMap.setAll(map);
		}
		
		String accessToken = getAccessToken("/list");
		System.out.println(">>>>> accessToken = " + accessToken);

		ResponseEntity<String> response = null;
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);
			
			HttpEntity<String> httpEntity = new HttpEntity<>(headers);
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(lightnetUrl + GET_LIST1_HTTPS_URL)
					.queryParams(reqMap)
					.build(true);
			
			response = SkipSSLConfig.getRestTemplate(1).exchange(builder.toString(), HttpMethod.GET, httpEntity, String.class);
			
			log.info("=====================================================");
			log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
			log.info("KANG-20200623 >>>>> GET {}", builder.toString());
			log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
			log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
			log.info("KANG-20200623 >>>>> response.getBody()            = {}", response.getBody());
			log.info("=====================================================");
			
			System.out.println(">>>>> response: " + response);
			
			if (Flag.flag) {
				// Pretty Print
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				JsonNode jsonNode = objectMapper.readTree(response.getBody());
				//String json = jsonNode.at("/").toPrettyString();
				String json = jsonNode.toPrettyString();
				System.out.println(">>>>> json: " + json);
			}
		}
		
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
	*/
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private String POST_LOCAL_AUTH_HTTP_URL = "http://localhost:18081/v0.1/auth/auth";

	private String getAccessToken(String subTitle) throws Exception {
		Map<String,Object> resMap = null;
		
		String reqJson = "{"
			+ "\"title\": \"/link" + subTitle + "\""
			+ "}";
		
		ResponseEntity<String> response = null;
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> request = new HttpEntity<>(reqJson, headers);

			RestTemplate restTemplate = SkipSSLConfig.getRestTemplate(0);
			for (int i=0; i < 5; i++) {
				response = restTemplate.exchange(POST_LOCAL_AUTH_HTTP_URL, HttpMethod.POST, request, String.class);
				
				log.info("=====================================================");
				log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
				log.info("KANG-20200623 >>>>> POST {}", POST_LOCAL_AUTH_HTTP_URL);
				log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
				log.info("KANG-20200623 >>>>> response.getBody()            = {}", response.getBody());
				log.info("=====================================================");
				
				if (response.getStatusCodeValue() != 200) {
					try { Thread.sleep(3000); } catch (InterruptedException e) {}
					continue;
				}
				resMap = new ObjectMapper().readValue(response.getBody(), new TypeReference<Map<String,Object>>(){});
				break;
			}
			
			String accessToken = (String) resMap.get("accessToken");
			return accessToken;
		}
		
		return null;
	}
}
