package org.tain;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMapperDetail {

	public static void main(String[] args) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) requestStreamToJson();
	}

	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	
	private static String MAPPER_DETAIL_S2J_HTTPS_URL = "http://localhost:8086/mapper/detail/request/s2j";
	
	private static void requestStreamToJson() throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			String paramJson = ""
				+ "{"
				+ "\"job\":\"/mapper/detail/request/s2j\","
				+ "\"streamData\": \"00110Hello    World    1234567890         0010020030004\""
				+ "}";
			
			HttpEntity<String> httpEntity = new HttpEntity<>(paramJson, headers);
			
			// RestTemplate
			RestTemplate restTemplate = new RestTemplate();
			//String response = restTemplate.postForObject(MAPPER_DETAIL_S2J_HTTPS_URL, httpEntity, String.class);
			ResponseEntity<String> response = restTemplate.exchange(MAPPER_DETAIL_S2J_HTTPS_URL, HttpMethod.POST, httpEntity, String.class);
			
			log.info("=====================================================");
			log.info("KANG-20200623 >>>>> {}", response);
			log.info("=====================================================");
			
			if (Flag.flag) {
				// Pretty Print
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				//JsonNode jsonNode = objectMapper.readTree(response.getBody());
				//String json = jsonNode.toPrettyString();
				//String json = jsonNode.at("/jsonData").toPrettyString();
				//jsonNode = objectMapper.readTree(jsonNode.at("/jsonData").toString());
				//System.out.println(">>>>> json: " + jsonNode.toPrettyString());
				Map<String,String> map = objectMapper.readValue(response.getBody(), new TypeReference<Map<String,String>>(){});
				map.forEach((key,value) -> {
					System.out.printf(">>>>> map [%s] => [%s]\n", key, value);
				});
				
				Map<String,String> subMap = objectMapper.readValue(map.get("jsonData"), new TypeReference<Map<String,String>>(){});
				subMap.forEach((key,value) -> {
					System.out.printf(">>>>> subMap [%s] => [%s]\n", key, value);
				});
			}
		}
	}
}
