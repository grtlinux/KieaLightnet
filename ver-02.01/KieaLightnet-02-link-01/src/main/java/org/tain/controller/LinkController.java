package org.tain.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
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

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	// {"status":"fail","message":"invalid transactionID or callerOperatorCode"}
	private String COMMIT_HTTPS_URL = "https://test-public.lightnetapis.io/v1.1/remittances.commit";
	
	@PostMapping(value = {"/commit"})
	public ResponseEntity<?> commit(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
		}
		
		String accessToken = getAccessToken();
		System.out.println(">>>>> accessToken = " + accessToken);

		ResponseEntity<String> response = null;

		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);

			HttpEntity<String> httpEntity = new HttpEntity<>(_httpEntity.getBody(), headers);

			SkipSSLConfig.skip();
			RestTemplate restTemplate = new RestTemplate();
			for (int i=0; i < 5; i++) {
				response = restTemplate.exchange(COMMIT_HTTPS_URL, HttpMethod.POST, httpEntity, String.class);
				
				//response.getStatusCodeValue();
				//response.getStatusCode();
				//response.getHeaders();
				//response.getBody();
				
				log.info("=====================================================");
				log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
				log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
				log.info("KANG-20200623 >>>>> response.getBody()            = {}", response.getBody());
				log.info("=====================================================");
				
				if (response.getStatusCodeValue() != 200) {
					try { Thread.sleep(3000); } catch (InterruptedException e) {}
					continue;
				}
				break;
			}
		}
		
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private String VALIDATE_HTTPS_URL = "https://test-public.lightnetapis.io/v1.1/remittances.validate";
	
	@PostMapping(value = {"/validate"})
	public ResponseEntity<?> validate(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
		}
		
		String accessToken = getAccessToken();
		System.out.println(">>>>> accessToken = " + accessToken);

		ResponseEntity<String> response = null;

		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);

			HttpEntity<String> httpEntity = new HttpEntity<>(_httpEntity.getBody(), headers);

			SkipSSLConfig.skip();
			RestTemplate restTemplate = new RestTemplate();
			for (int i=0; i < 5; i++) {
				response = restTemplate.exchange(VALIDATE_HTTPS_URL, HttpMethod.POST, httpEntity, String.class);
				
				//response.getStatusCodeValue();
				//response.getStatusCode();
				//response.getHeaders();
				//response.getBody();
				
				log.info("=====================================================");
				log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
				log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
				log.info("KANG-20200623 >>>>> response.getBody()            = {}", response.getBody());
				log.info("=====================================================");
				
				if (response.getStatusCodeValue() != 200) {
					try { Thread.sleep(3000); } catch (InterruptedException e) {}
					continue;
				}
				break;
			}
		}
		
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private String VALIDATE0_HTTPS_URL = "https://test-public.lightnetapis.io/v1.1/remittances.validate";
	
	@PostMapping(value = {"/validate0"})
	public ResponseEntity<?> validate0(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
		}
		
		String accessToken = getAccessToken();
		System.out.println(">>>>> accessToken = " + accessToken);

		String response = null;
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);
			
			HttpEntity<String> httpEntity = new HttpEntity<>(_httpEntity.getBody(), headers);
			
			SkipSSLConfig.skip();
			response = new RestTemplate().postForObject(VALIDATE0_HTTPS_URL, httpEntity, String.class);
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
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private String LIST_HTTPS_URL = "https://test-public.lightnetapis.io/v1.1/remittances";
	
	@PostMapping(value = {"/list"})
	public ResponseEntity<?> list(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		MultiValueMap<String, String> reqMap = new LinkedMultiValueMap<>();
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
			
			Map<String,Object> map = new ObjectMapper().readValue(_httpEntity.getBody(), new TypeReference<Map<String,Object>>() {});
			map.forEach((key, value) -> {
				reqMap.add(key, (String)value);
			}); 
		}
		
		String accessToken = getAccessToken();
		System.out.println(">>>>> accessToken = " + accessToken);

		ResponseEntity<String> response = null;
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);
			
			HttpEntity<String> httpEntity = new HttpEntity<>(headers);
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(LIST_HTTPS_URL)
					.queryParams(reqMap)
					//.queryParam("sourceCountry", "KOR")
					//.queryParam("destinationCountry", "KHM")
					//.queryParam("destinationOperatorCode", "lyhour")
					//.queryParam("withdrawableAmount", "1.500")
					//.queryParam("transactionCurrency", "USD")
					//.queryParam("deliveryMethod", "cash")
					.build(true);
			
			SkipSSLConfig.skip();
			response = new RestTemplate().exchange(builder.toString(), HttpMethod.GET, httpEntity, String.class);
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
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private String DETAIL_HTTPS_URL = "https://test-public.lightnetapis.io/v1/remittances.detail";
	
	@PostMapping(value = {"/detail"})
	public ResponseEntity<?> detail(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		MultiValueMap<String, String> reqMap = new LinkedMultiValueMap<>();
		if (Flag.flag) {
			System.out.println(">>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println(">>>>> Body = " + _httpEntity.getBody());
			
			Map<String,Object> map = new ObjectMapper().readValue(_httpEntity.getBody(), new TypeReference<Map<String,Object>>() {});
			map.forEach((key, value) -> {
				reqMap.add(key, (String)value);
			}); 
		}
		
		String accessToken = getAccessToken();
		System.out.println(">>>>> accessToken = " + accessToken);

		ResponseEntity<String> response = null;
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);
			
			HttpEntity<String> httpEntity = new HttpEntity<>(headers);
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(DETAIL_HTTPS_URL)
					.queryParams(reqMap)
					//.queryParam("sourceCountry", "KOR")
					//.queryParam("destinationCountry", "KHM")
					//.queryParam("destinationOperatorCode", "lyhour")
					//.queryParam("withdrawableAmount", "1.500")
					//.queryParam("transactionCurrency", "USD")
					//.queryParam("deliveryMethod", "cash")
					.build(true);
			
			SkipSSLConfig.skip();
			response = new RestTemplate().exchange(builder.toString(), HttpMethod.GET, httpEntity, String.class);
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

	private String HTTP_URL = "http://localhost:8081/auth";

	private String getAccessToken() throws Exception {
		Map<String,Object> reqMap = null;
		Map<String,Object> resMap = null;
		if (Flag.flag) {
			String reqJson = "{"
				+ "\"title\": \"detail\""
				+ "}";
			reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,Object>>(){});
		}
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("title", (String) reqMap.get("title"));
			
			HttpEntity<Map<String,Object>> request = new HttpEntity<>(parameters, headers);

			SkipSSLConfig.skip();
			RestTemplate restTemplate = new RestTemplate();
			for (int i=0; i < 5; i++) {
				ResponseEntity<String> response = restTemplate.exchange(HTTP_URL, HttpMethod.POST, request, String.class);
				
				//response.getStatusCodeValue();
				//response.getStatusCode();
				//response.getHeaders();
				//response.getBody();
				
				log.info("=====================================================");
				log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
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
