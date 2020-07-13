package org.tain.controller;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.object.ValidateReq;
import org.tain.object.ValidateRes;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/mapper/validate"})
@Slf4j
public class ValidateController {

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	@PostMapping(value = {"/s2j"})
	public ResponseEntity<?> streamToJson(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println("MAPPER >>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println("MAPPER >>>>> Body = " + _httpEntity.getBody());
		}
		
		Map<String,String> map = null;
		String data = null;
		if (Flag.flag) {
			// mapping process
			ObjectMapper objectMapper = new ObjectMapper();
			map = objectMapper.readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
			
			data = map.get("data");
			System.out.println("MAPPER >>>>> data = [" + data + "]");
			
			// link process (REQ -> RES)
			//String retData = "{\"receiver\":{\"firstName\":\"SOPIDA\",\"lastName\":\"WANGKIATKUL\",\"bankCode\":\"SICOTHBK\""
			//		+ ",\"accountId\":\"6032668977\"},\"deliveryMethod\":\"account_deposit\",\"sender\":{\"firstName\":\"NDBXMX\""
			//		+ ",\"lastName\":\"GYQMNB\",\"address\":{\"address\":\"senderAddress\",\"city\":\"senderCity\",\"countryCode\":\"THA\""
			//		+ ",\"postalCode\":\"senderZipCode\"},\"nationalityCountryCode\":\"KOR\",\"mobilePhone\":{\"number\":\"881111111\""
			//		+ ",\"countryCode\":\"66\"},\"idNumber\":\"idNumber\"},\"destination\":{\"country\":\"THA\",\"receive\":{\"currency\":\"THB\"}"
			//		+ ",\"operatorCode\":\"scb\"},\"remark\":\"This is SCB test remark\",\"source\":{\"country\":\"KOR\""
			// 		+ ",\"send\":{\"amount\":\"1000.01\",\"currency\":\"USD\"},\"transactionId\":\"9974531076200937\"}}";
			String retData = requestStreamToJson(data);
			
			//map.put("retData", Convert.quote(retData));
			map.put("retData", retData);
			System.out.println("MAPPER >>>>> map: " + map);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
	
	//strValidateReq = "04910101REQ9974531076200937              KOR       1000.01             USD       NDBXMX              GYQMNB              KOR       idNumber            senderAddress       senderCity          THA       senderZipCode       881111111           66                            scb       THA                                               THB       SOPIDA              WANGKIATKUL         SICOTHBK            6032668977          account_deposit     This is SCB test remark                           ";
	//strValidateReq = "04910101REQ9974531076200937              KOR2      1000.01             USD       NDBXMX              GYQMNB              KOR       idNumber            senderAddress       senderCity          THA       senderZipCode       881111111           66                            scb       THA                                               THB       SOPIDA              WANGKIATKUL         SICOTHBK            6032668977          account_deposit     This is SCB test remark                           ";

	@Value("${json.file.validateReqDummy.path}")
	private String jsonFileValidateReqDummyPath;

	private String requestStreamToJson(String data) throws Exception {
		
		ValidateReq dummyValidateReq = new ObjectMapper().readValue(new File(System.getenv("HOME") + this.jsonFileValidateReqDummyPath), ValidateReq.class);
		String jsonDummyValidateReq = "";
		
		if (Flag.flag) {
			System.out.printf(">>>>> (%d) [%s]\n", data.length(), data);
			
			int offset = 0;
			int size = -1;
			
			int length = -1;
			String division = null;
			String type = null;
			
			size = 4; length = Integer.valueOf(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 4; division = nvl1(data.substring(offset, offset+size).trim()); offset += size;
			size = 3; type = nvl1(data.substring(offset, offset+size).trim()); offset += size;
			
			size = 20; dummyValidateReq.getSource().setTransactionId(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 10; dummyValidateReq.getSource().setOperatorCode(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 10; dummyValidateReq.getSource().setCountry(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getSource().getSend().setAmount(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 10; dummyValidateReq.getSource().getSend().setCurrency(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			
			size = 20; dummyValidateReq.getSender().setFirstName(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getSender().setLastName(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 10; dummyValidateReq.getSender().setNationalityCountryCode(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getSender().setIdNumber(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getSender().getAddress().setAddress(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getSender().getAddress().setCity(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 10; dummyValidateReq.getSender().getAddress().setCountryCode(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getSender().getAddress().setPostalCode(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getSender().getMobilePhone().setNumber(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 10; dummyValidateReq.getSender().getMobilePhone().setCountryCode(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			
			size = 20; dummyValidateReq.getDestination().setTransactionId(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 10; dummyValidateReq.getDestination().setOperatorCode(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 10; dummyValidateReq.getDestination().setCountry(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getDestination().setWithdrawalId(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getDestination().getReceive().setAmount(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 10; dummyValidateReq.getDestination().getReceive().setCurrency(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			
			size = 20; dummyValidateReq.getReceiver().setFirstName(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getReceiver().setLastName(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getReceiver().setBankCode(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 20; dummyValidateReq.getReceiver().setAccountId(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			
			size = 20; dummyValidateReq.setDeliveryMethod(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			size = 50; dummyValidateReq.setRemark(nvl1(data.substring(offset, offset+size).trim())); offset += size;
			
			//size = 20; dummyValidateReq.getFee().setAmount(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
			//size = 10; dummyValidateReq.getFee().setCurrency(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
			//size = 20; dummyValidateReq.getFee().setModel(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
			//size = 20; dummyValidateReq.getRate().getFrom().setAmount(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
			//size = 10; dummyValidateReq.getRate().getFrom().setCurrency(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
			//size = 20; dummyValidateReq.getRate().getTo().setAmount(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
			//size = 10; dummyValidateReq.getRate().getTo().setCurrency(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
			
			//size = 50; dummyValidateReq.setTransactionId(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
			//size = 20; dummyValidateReq.setStatus(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
			
			//size = 20; dummyValidateReq.setSaveReport(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
			//size = 30; dummyValidateReq.setRemittanceType(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
			
			
			if (Flag.flag) {
				System.out.printf(">>>>> %d %s %s\n", length, division, type);
				
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				//jsonDummyValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dummyValidateReq);
				jsonDummyValidateReq = objectMapper.writeValueAsString(dummyValidateReq);
				//System.out.println(">>>>> " + jsonDummyValidateReq);
			}
		}
		return jsonDummyValidateReq;
	}

	private static String nvl1(String str) {
		if ("null".equals(str) || "".equals(str)) {
			return null;
		}
		return str;
	}

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	@PostMapping(value = {"/j2s"})
	public ResponseEntity<?> jsonToStream(HttpEntity<String> _httpEntity) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println("MAPPER >>>>> Headers = " + _httpEntity.getHeaders());
			System.out.println("MAPPER >>>>> Body = " + _httpEntity.getBody());
		}
		
		Map<String,String> map = null;
		String data = null;
		if (Flag.flag) {
			// mapping process
			ObjectMapper objectMapper = new ObjectMapper();
			map = objectMapper.readValue(_httpEntity.getBody(), new TypeReference<Map<String,String>>(){});
			
			data = map.get("data");
			System.out.println("MAPPER >>>>> data = [" + data + "]");
			
			//if (Flag.flag) {
			//	JsonNode jsonNode = new ObjectMapper().readTree(data);
			//	System.out.println("MAPPER >>>>> REQ JSON: " + jsonNode.toPrettyString());
			//}
			//map.put("retData", Convert.quote("0102AAAA        1234567890  ABC1002003        Hello    "));
			
			String retData = responseJsonToStream(data);
			
			map.put("retData", retData);
			System.out.println("MAPPER >>>>> map: " + map);
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
	}
	
	//strValidateRes = "07610102RES9974531076200937    hanwha    KOR       1000.0100           USD       NDBXMX              GYQMNB              KOR       idNumber            senderAddress       senderCity          THA       senderZipCode       881111111           66        D014017804533540    scb       THA                           31040.5700          THB       SOPIDA              WANGKIATKUL         SICOTHBK            6032668977          account_deposit                                                       0.7000              USD       included            1.0000              USD       31.0620             THB       62bbb4b8-fe03-40d2-6f30-92baf54da82d              PENDING                                 MESSAGING                     success             OK                  ";
	//strValidateRes = "07610102RES9974531076200937    hanwha    KOR2      1000.0100           USD       NDBXMX              GYQMNB              KOR       idNumber            senderAddress       senderCity          THA       senderZipCode       881111111           66        D014017804533540    scb       THA                           31040.5700          THB       SOPIDA              WANGKIATKUL         SICOTHBK            6032668977          account_deposit                                                       0.7000              USD       included            1.0000              USD       31.0620             THB       62bbb4b8-fe03-40d2-6f30-92baf54da82d              PENDING                                 MESSAGING                     success             OK                  ";

	private String responseJsonToStream(String data) throws Exception {
		
		ValidateRes validateRes = new ObjectMapper().readValue(data, ValidateRes.class);
		System.out.println(">>>>> " + validateRes);
		
		if (!Flag.flag) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			String jsonValidateRes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateRes);
			System.out.println(">>>>> " + jsonValidateRes);
		}
		
		String strValidateRes = null;
		if (Flag.flag) {
			StringBuffer sb = new StringBuffer();
			
			sb.append(String.format("0102"));
			sb.append(String.format("RES"));
			
			if ("success".equals(validateRes.getStatus())) {
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getSource().getTransactionId())));                 // data.source.transactionId
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getSource().getOperatorCode())));                  // data.source.operatorCode
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getSource().getCountry())));                       // data.source.country
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getSource().getSend().getAmount())));              // data.source.send.amount
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getSource().getSend().getCurrency())));            // data.source.send.currency
				
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getSender().getFirstName())));                     // data.sender.firstName
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getSender().getLastName())));                      // data.sender.lastName
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getSender().getNationalityCountryCode())));        // data.sender.nationalityCountryCode
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getSender().getIdNumber())));                      // data.sender.idNumber
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getSender().getAddress().getAddress())));          // data.sender.address.address
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getSender().getAddress().getCity())));             // data.sender.address.city
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getSender().getAddress().getCountryCode())));      // data.sender.address.countryCode
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getSender().getAddress().getPostalCode())));       // data.sender.address.postalCode
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getSender().getMobilePhone().getNumber())));       // data.sender.mobilePhone.number
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getSender().getMobilePhone().getCountryCode())));  // data.sender.mobilePhone.countryCode
				
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getDestination().getTransactionId())));            // data.destination.transactionId
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getDestination().getOperatorCode())));             // data.destination.operatorCode
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getDestination().getCountry())));                  // data.destination.country
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getDestination().getWithdrawalId())));             // data.destination.withdrawalId
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getDestination().getReceive().getAmount())));      // data.destination.receive.amount
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getDestination().getReceive().getCurrency())));    // data.destination.receive.currency
				
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getReceiver().getFirstName())));                   // data.receiver.firstName
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getReceiver().getLastName())));                    // data.receiver.lastName
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getReceiver().getBankCode())));                    // data.receiver.bankCode
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getReceiver().getAccountId())));                   // data.receiver.accountId
				
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getDeliveryMethod())));                            // data.deliveryMethod
				sb.append(String.format("%-50s", nvl2(validateRes.getData().getRemark())));                                    // data.remark
				
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getFee().getAmount())));                           // data.fee.amount
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getFee().getCurrency())));                         // data.fee.currency
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getFee().getModel())));                            // data.fee.model
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getRate().getFrom().getAmount())));                // data.rate.from.amount
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getRate().getFrom().getCurrency())));              // data.rate.from.currency
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getRate().getTo().getAmount())));                  // data.rate.to.amount
				sb.append(String.format("%-10s", nvl2(validateRes.getData().getRate().getTo().getCurrency())));                // data.rate.to.currency
				
				sb.append(String.format("%-50s", nvl2(validateRes.getData().getTransactionId())));                             // data.transactionId
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getStatus())));                                    // data.status
				
				sb.append(String.format("%-20s", nvl2(validateRes.getData().getSaveReport())));                                // data.saveReport
				sb.append(String.format("%-30s", nvl2(validateRes.getData().getRemittanceType())));                            // data.remittanceType
				
				sb.append(String.format("%-20s", nvl2(validateRes.getStatus())));                                              // status
				sb.append(String.format("%-50s", nvl2(validateRes.getMessage())));                                             // message
			} else {
				sb.append(String.format("%-20s", ""));  // data.source.transactionId
				sb.append(String.format("%-10s", ""));  // data.source.operatorCode
				sb.append(String.format("%-10s", ""));  // data.source.country
				sb.append(String.format("%-20s", ""));  // data.source.send.amount
				sb.append(String.format("%-10s", ""));  // data.source.send.currency
				
				sb.append(String.format("%-20s", ""));  // data.sender.firstName
				sb.append(String.format("%-20s", ""));  // data.sender.lastName
				sb.append(String.format("%-10s", ""));  // data.sender.nationalityCountryCode
				sb.append(String.format("%-20s", ""));  // data.sender.idNumber
				sb.append(String.format("%-20s", ""));  // data.sender.address.address
				sb.append(String.format("%-20s", ""));  // data.sender.address.city
				sb.append(String.format("%-10s", ""));  // data.sender.address.countryCode
				sb.append(String.format("%-20s", ""));  // data.sender.address.postalCode
				sb.append(String.format("%-20s", ""));  // data.sender.mobilePhone.number
				sb.append(String.format("%-10s", ""));  // data.sender.mobilePhone.countryCode
				
				sb.append(String.format("%-20s", ""));  // data.destination.transactionId
				sb.append(String.format("%-10s", ""));  // data.destination.operatorCode
				sb.append(String.format("%-10s", ""));  // data.destination.country
				sb.append(String.format("%-20s", ""));  // data.destination.withdrawalId
				sb.append(String.format("%-20s", ""));  // data.destination.receive.amount
				sb.append(String.format("%-10s", ""));  // data.destination.receive.currency
				
				sb.append(String.format("%-20s", ""));  // data.receiver.firstName
				sb.append(String.format("%-20s", ""));  // data.receiver.lastName
				sb.append(String.format("%-20s", ""));  // data.receiver.bankCode
				sb.append(String.format("%-20s", ""));  // data.receiver.accountId
				
				sb.append(String.format("%-20s", ""));  // data.deliveryMethod
				sb.append(String.format("%-50s", ""));  // data.remark
				
				sb.append(String.format("%-20s", ""));  // data.fee.amount
				sb.append(String.format("%-10s", ""));  // data.fee.currency
				sb.append(String.format("%-20s", ""));  // data.fee.model
				sb.append(String.format("%-20s", ""));  // data.rate.from.amount
				sb.append(String.format("%-10s", ""));  // data.rate.from.currency
				sb.append(String.format("%-20s", ""));  // data.rate.to.amount
				sb.append(String.format("%-10s", ""));  // data.rate.to.currency
				
				sb.append(String.format("%-50s", ""));  // data.transactionId
				sb.append(String.format("%-20s", ""));  // data.status
				
				sb.append(String.format("%-20s", ""));  // data.saveReport
				sb.append(String.format("%-30s", ""));  // data.remittanceType
				
				sb.append(String.format("%-20s", nvl2(validateRes.getStatus())));                                              // status
				sb.append(String.format("%-50s", nvl2(validateRes.getMessage())));                                             // message
			}
			
			int length = 4 + sb.length();
			sb.insert(0, String.format("%04d", length));
			
			if (Flag.flag) System.out.printf(">>>>> (%04d) [%s]\n", sb.length(), sb.toString());
			
			strValidateRes = sb.toString();
		}
		
		return strValidateRes;
	}
	
	private static String nvl2(String str) {
		if (str == null || "null".equals(str)) {
			return "";
		}
		return str;
	}
}
