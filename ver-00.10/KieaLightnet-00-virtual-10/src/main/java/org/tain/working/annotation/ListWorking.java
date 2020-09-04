package org.tain.working.annotation;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.object.list.res._Data;
import org.tain.object.list.res._Source;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.SubString;
import org.tain.utils.TransferStrAndJson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ListWorking {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
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
			System.out.println(">>>>> [" + source + "]");
			if (Flag.flag) System.exit(0);
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
			System.out.println(">>>>> [" + data + "]");
			
			String str = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
			System.out.println(">>>>> " + str);
			if (Flag.flag) System.exit(0);
		}
		
		if (!Flag.flag) {
			String strJson = null;
			strJson = "[ {\n" + 
					"  \"transactionId\" : \"42b1ea8e-a685-40da-42fd-1b87d5956151\",\n" + 
					"  \"deliveryMethod\" : \"account_deposit\",\n" + 
					"  \"source\" : {\n" + 
					"    \"transactionId\" : \"AUTOMATE-SCB-12345\",\n" + 
					"    \"operatorCode\" : \"hanwha\",\n" + 
					"    \"country\" : \"KOR\",\n" + 
					"    \"send\" : {\n" + 
					"      \"amount\" : \"2.0000\",\n" + 
					"      \"currency\" : \"USD\"\n" + 
					"    }\n" + 
					"  },\n" + 
					"  \"destination\" : {\n" + 
					"    \"transactionId\" : \"2560633009\",\n" + 
					"    \"operatorCode\" : \"scb\",\n" + 
					"    \"country\" : \"THA\",\n" + 
					"    \"receive\" : {\n" + 
					"      \"amount\" : \"37175.9600\",\n" + 
					"      \"currency\" : \"THB\"\n" + 
					"    }\n" + 
					"  },\n" + 
					"  \"fee\" : {\n" + 
					"    \"amount\" : \"0.0000\",\n" + 
					"    \"currency\" : \"\",\n" + 
					"    \"model\" : \"included\"\n" + 
					"  },\n" + 
					"  \"rate\" : {\n" + 
					"    \"from\" : {\n" + 
					"      \"amount\" : \"1.0000\",\n" + 
					"      \"currency\" : \"USD\"\n" + 
					"    },\n" + 
					"    \"to\" : {\n" + 
					"      \"amount\" : \"30.1234\",\n" + 
					"      \"currency\" : \"THB\"\n" + 
					"    }\n" + 
					"  },\n" + 
					"  \"sender\" : {\n" + 
					"    \"address\" : {\n" + 
					"      \"address\" : \"senderAddress\",\n" + 
					"      \"city\" : \"senderCity\",\n" + 
					"      \"countryCode\" : \"THA\",\n" + 
					"      \"postalCode\" : \"senderZipCode\"\n" + 
					"    },\n" + 
					"    \"firstName\" : \"SKANTE\",\n" + 
					"    \"idNumber\" : \"idNumber\",\n" + 
					"    \"lastName\" : \"MYCYBX\",\n" + 
					"    \"mobilePhone\" : {\n" + 
					"      \"countryCode\" : \"66\",\n" + 
					"      \"number\" : \"881111111\"\n" + 
					"    },\n" + 
					"    \"nationalityCountryCode\" : \"KOR\"\n" + 
					"  },\n" + 
					"  \"receiver\" : {\n" + 
					"    \"accountId\" : \"receiverAccountId\",\n" + 
					"    \"bankCode\" : \"SICOTHBK\",\n" + 
					"    \"firstName\" : \"BZIDWB\",\n" + 
					"    \"lastName\" : \"USBARH\"\n" + 
					"  },\n" + 
					"  \"status\" : \"MTO_INITIAL_REJECTED\",\n" + 
					"  \"remittanceType\" : \"MESSAGING\"\n" + 
					"} ]";
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setSerializationInclusion(Include.NON_NULL);
				List<_Data> lstData = objectMapper.readValue(strJson, new TypeReference<List<_Data>>() {});
				for (_Data data : lstData) {
					System.out.println("ARR_DATA >>>>> [" + data + "]");
				}
			}
			if (Flag.flag) System.exit(0);
		}
		
		if (Flag.flag) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			
			JsonNode jsonNode = objectMapper.readTree(new File(System.getenv("HOME") + this.projEnvParamProperties.getList1File()));
			String json = jsonNode.at("/data").toPrettyString();
			if (Flag.flag) System.out.println("/data >>>>> " + json);
			
			List<_Data> lstData = objectMapper.readValue(json, new TypeReference<List<_Data>>() {});
			
			if (Flag.flag) {
				PrintWriter printFileWriter = new PrintWriter(new File(System.getenv("HOME") + "/LIST2.txt"));
				String stream = TransferStrAndJson.getStream(lstData.get(0));
				int rowSize = stream.length() + 2;
				rowSize += 5;   // display sequence
				if (Flag.flag) {
					// header HH
					LocalDate yesterday = LocalDate.now().plus(Period.ofDays(-1));
					StringBuffer sb = new StringBuffer();
					sb.append("HH");
					sb.append("00000");   // display sequence
					sb.append(yesterday.format(DateTimeFormatter.BASIC_ISO_DATE));
					sb.append("000000");
					sb.append(yesterday.format(DateTimeFormatter.BASIC_ISO_DATE));
					sb.append("235959");
					printFileWriter.println(String.format("%-" + rowSize + "." + rowSize + "s", sb.toString()));
				}
				int idx = 0;
				for (_Data data : lstData) {
					// data DD
					++idx;
					System.out.println("==================== getStream ==================================");
					String strStream = TransferStrAndJson.getStream(data);
					if (Flag.flag) System.out.println("FILE_DATA >>>>> [" + strStream + "]");
					printFileWriter.print("DD");
					printFileWriter.print(String.format("%05d", idx));   // display sequence
					printFileWriter.print(strStream);
					printFileWriter.println();
					
					System.out.println("-------------------- setStream ----------------------------------");
					_Data data2 = new _Data();
					
					TransferStrAndJson.subString = new SubString(strStream);
					data2 = (_Data) TransferStrAndJson.getObject(data2);
					JsonPrint.getInstance().printPrettyJson(data2);
				}
				if (Flag.flag) {
					// tail TT
					StringBuffer sb = new StringBuffer();
					sb.append("TT");
					sb.append("00000");   // display sequence
					sb.append(String.format("%010d", idx));
					printFileWriter.println(String.format("%-" + rowSize + "." + rowSize + "s", sb.toString()));
				}
				
				printFileWriter.close();
			}
			
			if (Flag.flag) System.exit(0);
		}
	}
}
