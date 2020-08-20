package org.tain.working;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.object.list._Data;
import org.tain.object.list._Source;
import org.tain.properties.LnsEnvVirtualProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ListWorking {

	@Autowired
	private LnsEnvVirtualProperties lnsEnvVirtualProperties;
	
	public void loading() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (!Flag.flag) {
			String strJson = null;
			
			strJson = "{\"source\": {\n" + 
					"                \"transactionId\": \"AUTOMATE-SCB-12345\",\n" + 
					"                \"operatorCode\": \"hanwha\",\n" + 
					"                \"country\": \"KOR\",\n" + 
					"                \"send\": {\n" + 
					"                    \"amount\": \"2.0000\",\n" + 
					"                    \"currency\": \"USD\"\n" + 
					"                }\n" + 
					"            }}";
			
			strJson = "{\n" + 
					"                \"transactionId\": \"AUTOMATE-SCB-12345\",\n" + 
					"                \"operatorCode\": \"hanwha\",\n" + 
					"                \"country\": \"KOR\",\n" + 
					"                \"send\": {\n" + 
					"                    \"amount\": \"2.0000\",\n" + 
					"                    \"currency\": \"USD\"\n" + 
					"                }\n" + 
					"            }";
			
			ObjectMapper objectMapper = new ObjectMapper();
			//objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			//objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			_Source source = objectMapper.readValue(strJson, _Source.class);
			System.out.println(">>>>> " + source);
		}
		
		if (!Flag.flag) {
			String strJson = null;
			strJson = "{\n" + 
					"            \"transactionId\": \"42b1ea8e-a685-40da-42fd-1b87d5956151\",\n" + 
					"            \"deliveryMethod\": \"account_deposit\",\n" + 
					"            \"source\": {\n" + 
					"                \"transactionId\": \"AUTOMATE-SCB-12345\",\n" + 
					"                \"operatorCode\": \"hanwha\",\n" + 
					"                \"country\": \"KOR\",\n" + 
					"                \"send\": {\n" + 
					"                    \"amount\": \"2.0000\",\n" + 
					"                    \"currency\": \"USD\"\n" + 
					"                }\n" + 
					"            },\n" + 
					"            \"destination\": {\n" + 
					"                \"transactionId\": \"2560633009\",\n" + 
					"                \"operatorCode\": \"scb\",\n" + 
					"                \"country\": \"THA\",\n" + 
					"                \"receive\": {\n" + 
					"                    \"amount\": \"37175.9600\",\n" + 
					"                    \"currency\": \"THB\"\n" + 
					"                }\n" + 
					"            },\n" + 
					"            \"fee\": {\n" + 
					"                \"amount\": \"0.0000\",\n" + 
					"                \"currency\": \"\",\n" + 
					"                \"model\": \"included\"\n" + 
					"            },\n" + 
					"            \"rate\": {\n" + 
					"                \"from\": {\n" + 
					"                    \"amount\": \"1.0000\",\n" + 
					"                    \"currency\": \"USD\"\n" + 
					"                },\n" + 
					"                \"to\": {\n" + 
					"                    \"amount\": \"30.1234\",\n" + 
					"                    \"currency\": \"THB\"\n" + 
					"                }\n" + 
					"            },\n" + 
					"            \"sender\": {\n" + 
					"                \"address\": {\n" + 
					"                    \"address\": \"senderAddress\",\n" + 
					"                    \"city\": \"senderCity\",\n" + 
					"                    \"countryCode\": \"THA\",\n" + 
					"                    \"postalCode\": \"senderZipCode\"\n" + 
					"                },\n" + 
					"                \"firstName\": \"SKANTE\",\n" + 
					"                \"idNumber\": \"idNumber\",\n" + 
					"                \"lastName\": \"MYCYBX\",\n" + 
					"                \"mobilePhone\": {\n" + 
					"                    \"countryCode\": \"66\",\n" + 
					"                    \"number\": \"881111111\"\n" + 
					"                },\n" + 
					"                \"nationalityCountryCode\": \"KOR\"\n" + 
					"            },\n" + 
					"            \"receiver\": {\n" + 
					"                \"accountId\": \"receiverAccountId\",\n" + 
					"                \"bankCode\": \"SICOTHBK\",\n" + 
					"                \"firstName\": \"BZIDWB\",\n" + 
					"                \"lastName\": \"USBARH\"\n" + 
					"            },\n" + 
					"            \"status\": \"MTO_INITIAL_REJECTED\",\n" + 
					"            \"remittanceType\": \"MESSAGING\"\n" + 
					"        }";
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			_Data data = objectMapper.readValue(strJson, _Data.class);
			System.out.println(">>>>> " + data);
			String str = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
			System.out.println(">>>>> " + str);
		}
		
		if (Flag.flag) {
			String strJson = null;
		}
		
		if (Flag.flag) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			
			JsonNode jsonNode = objectMapper.readTree(new File(System.getenv("HOME") + this.lnsEnvVirtualProperties.getList1File() + "0"));
			String json = jsonNode.at("/data").toPrettyString();
			//System.out.println(">>>>> " + json);
			
			List<_Data> lstData = objectMapper.readValue(json, new TypeReference<List<_Data>>() {});
			for (_Data data : lstData) {
				System.out.println(">>>>> " + data);
			}
		}
	}
}
