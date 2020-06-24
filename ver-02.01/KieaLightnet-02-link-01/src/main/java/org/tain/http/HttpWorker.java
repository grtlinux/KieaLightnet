package org.tain.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpWorker {

	public static ResponseEntity<?> post(String httpUrl, MultiValueMap<String,String> reqMap) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		ResponseEntity<String> response = null;
		
		if (Flag.flag) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(reqMap, httpHeaders);
			
			response = new RestTemplate().exchange(httpUrl, HttpMethod.POST, request, String.class);
			//response.getStatusCodeValue();  // 200
			//response.getStatusCode();       // 200 Ok
			//response.getHeaders();          // .....
			//response.getBody();             // JSON Data
		}
		
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
	
	public static ResponseEntity<?> get(String httpUrl, MultiValueMap<String,String> reqMap) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		ResponseEntity<String> response = null;
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.set("Authorization", "Bearer " + accessToken);
			HttpEntity<String> httpEntity = new HttpEntity<>(headers);
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
					.path("")
					.queryParams(reqMap)
					//.queryParam("operatorCode", "")
					//.queryParam("offset", "1")
					//.queryParam("limit", "20")
					//.queryParam("from", "")
					//.queryParam("to", "")
					.build(false)
					.encode();
			
			response = new RestTemplate().exchange(builder.toString(), HttpMethod.GET, httpEntity, String.class);
			//response.getStatusCodeValue();  // 200
			//response.getStatusCode();       // 200 Ok
			//response.getHeaders();          // .....
			//response.getBody();             // JSON Data

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
}
