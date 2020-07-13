package org.tain;

import java.io.File;

import org.tain.object.ValidateReq;
import org.tain.object.ValidateRes;
import org.tain.utils.Flag;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestValidate {

	public static void main(String[] args) throws Exception {
		if (!Flag.flag) test00();
		if (!Flag.flag) test01();
		if (Flag.flag) test02();
		if (!Flag.flag) test03();
		if (Flag.flag) test04();
	}

	private static void test00() throws Exception {
		if (Flag.flag) {
			// read validate_req
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/validate_req.json"));
			System.out.println("----------------------------- validate_req.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			// read validate_req2
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/validate_req2.json"));
			System.out.println("----------------------------- validate_req2.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			// read validate_res
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/validate_res.json"));
			System.out.println("----------------------------- validate_res.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			// read validate_res2
			JsonNode jsonNode = new ObjectMapper().readTree(new File("src/test/java/org/tain/validate_res2.json"));
			System.out.println("----------------------------- validate_res2.json ----------------------------");
			System.out.println(">>>>> " + jsonNode.toPrettyString());
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test01() throws Exception {
		if (Flag.flag) {
			// read validate_req
			System.out.println("----------------------------- validate_req.json ----------------------------");
			ValidateReq validateReq = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_req.json"), ValidateReq.class);
			System.out.println(">>>>> " + validateReq);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + validateReq.getSender().getAddress().getPostalCode());
			}
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				//objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.CUSTOM);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
				String jsonValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateReq);
				System.out.println(">>>>> " + jsonValidateReq);
			}
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test02() throws Exception {
		if (!Flag.flag) {
			// read validate_req2
			System.out.println("----------------------------- validate_req2.json ----------------------------");
			ValidateReq validateReq = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_req2.json"), ValidateReq.class);
			System.out.println(">>>>> " + validateReq);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + validateReq.getSender().getAddress().getPostalCode());
			}
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				//objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.CUSTOM);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
				String jsonValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateReq);
				System.out.println(">>>>> " + jsonValidateReq);
			}
		}
		
		if (Flag.flag) {
			System.out.println("----------------------------- validate_req2.json ----------------------------");
			ValidateReq validateReq = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_req2.json"), ValidateReq.class);
			//ValidateRes validateRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_res2.json"), ValidateRes.class);
			System.out.println(">>>>> " + validateReq);
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				String jsonValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateReq);
				System.out.println(">>>>> " + jsonValidateReq);
			}
			
			String strValidateReq = null;
			if (Flag.flag) {
				StringBuffer sb = new StringBuffer();
				
				sb.append(String.format("0101"));
				sb.append(String.format("REQ"));
				
				sb.append(String.format("%-20s", nvl(validateReq.getSource().getTransactionId())));                 // source.transactionId
				sb.append(String.format("%-10s", nvl(validateReq.getSource().getOperatorCode())));                  // source.operatorCode
				sb.append(String.format("%-10s", nvl(validateReq.getSource().getCountry())));                       // source.country
				sb.append(String.format("%-20s", nvl(validateReq.getSource().getSend().getAmount())));              // source.send.amount
				sb.append(String.format("%-10s", nvl(validateReq.getSource().getSend().getCurrency())));            // source.send.currency
				
				sb.append(String.format("%-20s", nvl(validateReq.getSender().getFirstName())));                     // sender.firstName
				sb.append(String.format("%-20s", nvl(validateReq.getSender().getLastName())));                      // sender.lastName
				sb.append(String.format("%-10s", nvl(validateReq.getSender().getNationalityCountryCode())));        // sender.nationalityCountryCode
				sb.append(String.format("%-20s", nvl(validateReq.getSender().getIdNumber())));                      // sender.idNumber
				sb.append(String.format("%-20s", nvl(validateReq.getSender().getAddress().getAddress())));          // sender.address.address
				sb.append(String.format("%-20s", nvl(validateReq.getSender().getAddress().getCity())));             // sender.address.city
				sb.append(String.format("%-10s", nvl(validateReq.getSender().getAddress().getCountryCode())));      // sender.address.countryCode
				sb.append(String.format("%-20s", nvl(validateReq.getSender().getAddress().getPostalCode())));       // sender.address.postalCode
				sb.append(String.format("%-20s", nvl(validateReq.getSender().getMobilePhone().getNumber())));       // sender.mobilePhone.number
				sb.append(String.format("%-10s", nvl(validateReq.getSender().getMobilePhone().getCountryCode())));  // sender.mobilePhone.countryCode
				
				sb.append(String.format("%-20s", nvl(validateReq.getDestination().getTransactionId())));            // destination.transactionId
				sb.append(String.format("%-10s", nvl(validateReq.getDestination().getOperatorCode())));             // destination.operatorCode
				sb.append(String.format("%-10s", nvl(validateReq.getDestination().getCountry())));                  // destination.country
				sb.append(String.format("%-20s", nvl(validateReq.getDestination().getWithdrawalId())));             // destination.withdrawalId
				sb.append(String.format("%-20s", nvl(validateReq.getDestination().getReceive().getAmount())));      // destination.receive.amount
				sb.append(String.format("%-10s", nvl(validateReq.getDestination().getReceive().getCurrency())));    // destination.receive.currency
				
				sb.append(String.format("%-20s", nvl(validateReq.getReceiver().getFirstName())));                   // receiver.firstName
				sb.append(String.format("%-20s", nvl(validateReq.getReceiver().getLastName())));                    // receiver.lastName
				sb.append(String.format("%-20s", nvl(validateReq.getReceiver().getBankCode())));                    // receiver.bankCode
				sb.append(String.format("%-20s", nvl(validateReq.getReceiver().getAccountId())));                   // receiver.accountId
				
				sb.append(String.format("%-20s", nvl(validateReq.getDeliveryMethod())));                            // deliveryMethod
				sb.append(String.format("%-50s", nvl(validateReq.getRemark())));                                    // remark
				
				//sb.append(String.format("%-20s", nvl(validateReq.getFee().getAmount())));                           // fee.amount
				//sb.append(String.format("%-10s", nvl(validateReq.getFee().getCurrency())));                         // fee.currency
				//sb.append(String.format("%-20s", nvl(validateReq.getFee().getModel())));                            // fee.model
				//sb.append(String.format("%-20s", nvl(validateReq.getRate().getFrom().getAmount())));                // rate.from.amount
				//sb.append(String.format("%-10s", nvl(validateReq.getRate().getFrom().getCurrency())));              // rate.from.currency
				//sb.append(String.format("%-20s", nvl(validateReq.getRate().getTo().getAmount())));                  // rate.to.amount
				//sb.append(String.format("%-10s", nvl(validateReq.getRate().getTo().getCurrency())));                // rate.to.currency
				
				//sb.append(String.format("%-50s", nvl(validateReq.getTransactionId())));                             // transactionId
				//sb.append(String.format("%-20s", nvl(validateReq.getStatus())));                                    // status
				
				//sb.append(String.format("%-20s", nvl(validateReq.getSaveReport())));                                // saveReport
				//sb.append(String.format("%-30s", nvl(validateReq.getRemittanceType())));                            // remittanceType
				
				int length = 4 + sb.length();
				sb.insert(0, String.format("%04d", length));
				
				if (Flag.flag) System.out.printf(">>>>> (%04d) [%s]\n", sb.length(), sb.toString());
				
				strValidateReq = sb.toString();
			}
			
			//strValidateReq = "04910101REQ9974531076200937              KOR       1000.01             USD       NDBXMX              GYQMNB              KOR       idNumber            senderAddress       senderCity          THA       senderZipCode       881111111           66                            scb       THA                                               THB       SOPIDA              WANGKIATKUL         SICOTHBK            6032668977          account_deposit     This is SCB test remark                           ";
			//strValidateReq = "04910101REQ9974531076200937              KOR2      1000.01             USD       NDBXMX              GYQMNB              KOR       idNumber            senderAddress       senderCity          THA       senderZipCode       881111111           66                            scb       THA                                               THB       SOPIDA              WANGKIATKUL         SICOTHBK            6032668977          account_deposit     This is SCB test remark                           ";
			
			ValidateReq dummyValidateReq = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_req_dummy.json"), ValidateReq.class);
			if (Flag.flag) {
				System.out.printf(">>>>> (%d) [%s]\n", strValidateReq.length(), strValidateReq);
				
				int offset = 0;
				int size = -1;
				
				int length = -1;
				String division = null;
				String type = null;
				
				size = 4; length = Integer.valueOf(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 4; division = nvl(strValidateReq.substring(offset, offset+size).trim()); offset += size;
				size = 3; type = nvl(strValidateReq.substring(offset, offset+size).trim()); offset += size;
				
				size = 20; dummyValidateReq.getSource().setTransactionId(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateReq.getSource().setOperatorCode(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateReq.getSource().setCountry(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getSource().getSend().setAmount(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateReq.getSource().getSend().setCurrency(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyValidateReq.getSender().setFirstName(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getSender().setLastName(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateReq.getSender().setNationalityCountryCode(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getSender().setIdNumber(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getSender().getAddress().setAddress(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getSender().getAddress().setCity(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateReq.getSender().getAddress().setCountryCode(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getSender().getAddress().setPostalCode(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getSender().getMobilePhone().setNumber(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateReq.getSender().getMobilePhone().setCountryCode(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyValidateReq.getDestination().setTransactionId(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateReq.getDestination().setOperatorCode(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateReq.getDestination().setCountry(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getDestination().setWithdrawalId(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getDestination().getReceive().setAmount(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateReq.getDestination().getReceive().setCurrency(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyValidateReq.getReceiver().setFirstName(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getReceiver().setLastName(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getReceiver().setBankCode(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateReq.getReceiver().setAccountId(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyValidateReq.setDeliveryMethod(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				size = 50; dummyValidateReq.setRemark(nvl(strValidateReq.substring(offset, offset+size).trim())); offset += size;
				
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
					String jsonDummyValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dummyValidateReq);
					System.out.println(">>>>> " + jsonDummyValidateReq);
				}
			}
		}
		
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test03() throws Exception {
		if (Flag.flag) {
			// read validate_res
			System.out.println("----------------------------- validate_res.json ----------------------------");
			ValidateRes validateRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_res.json"), ValidateRes.class);
			System.out.println(">>>>> " + validateRes);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + validateRes.getData().getSender().getAddress().getPostalCode());
			}
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				//objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.CUSTOM);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
				String jsonValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateRes);
				System.out.println(">>>>> " + jsonValidateReq);
			}
		}
		System.out.println("--------------------------- E N D -------------------------------");
	}

	private static void test04() throws Exception {
		if (!Flag.flag) {
			// read validate_res2
			System.out.println("----------------------------- validate_res2.json ----------------------------");
			ValidateRes validateRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_res2.json"), ValidateRes.class);
			System.out.println(">>>>> " + validateRes);
			
			if (Flag.flag) {
				System.out.println(">>>>> " + validateRes.getData().getSender().getAddress().getPostalCode());
			}
			
			if (Flag.flag) {
				ObjectMapper objectMapper = new ObjectMapper();
				//objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.CUSTOM);
				//objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
				String jsonValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateRes);
				System.out.println(">>>>> " + jsonValidateReq);
			}
		}
		
		if (Flag.flag) {
			System.out.println("----------------------------- validate_res2.json ----------------------------");
			ValidateRes validateRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_res2.json"), ValidateRes.class);
			//ValidateRes validateRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_res2.json"), ValidateRes.class);
			System.out.println(">>>>> " + validateRes);
			
			if (Flag.flag) {
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
				
				sb.append(String.format("%-20s", nvl(validateRes.getData().getSource().getTransactionId())));                 // data.source.transactionId
				sb.append(String.format("%-10s", nvl(validateRes.getData().getSource().getOperatorCode())));                  // data.source.operatorCode
				sb.append(String.format("%-10s", nvl(validateRes.getData().getSource().getCountry())));                       // data.source.country
				sb.append(String.format("%-20s", nvl(validateRes.getData().getSource().getSend().getAmount())));              // data.source.send.amount
				sb.append(String.format("%-10s", nvl(validateRes.getData().getSource().getSend().getCurrency())));            // data.source.send.currency
				
				sb.append(String.format("%-20s", nvl(validateRes.getData().getSender().getFirstName())));                     // data.sender.firstName
				sb.append(String.format("%-20s", nvl(validateRes.getData().getSender().getLastName())));                      // data.sender.lastName
				sb.append(String.format("%-10s", nvl(validateRes.getData().getSender().getNationalityCountryCode())));        // data.sender.nationalityCountryCode
				sb.append(String.format("%-20s", nvl(validateRes.getData().getSender().getIdNumber())));                      // data.sender.idNumber
				sb.append(String.format("%-20s", nvl(validateRes.getData().getSender().getAddress().getAddress())));          // data.sender.address.address
				sb.append(String.format("%-20s", nvl(validateRes.getData().getSender().getAddress().getCity())));             // data.sender.address.city
				sb.append(String.format("%-10s", nvl(validateRes.getData().getSender().getAddress().getCountryCode())));      // data.sender.address.countryCode
				sb.append(String.format("%-20s", nvl(validateRes.getData().getSender().getAddress().getPostalCode())));       // data.sender.address.postalCode
				sb.append(String.format("%-20s", nvl(validateRes.getData().getSender().getMobilePhone().getNumber())));       // data.sender.mobilePhone.number
				sb.append(String.format("%-10s", nvl(validateRes.getData().getSender().getMobilePhone().getCountryCode())));  // data.sender.mobilePhone.countryCode
				
				sb.append(String.format("%-20s", nvl(validateRes.getData().getDestination().getTransactionId())));            // data.destination.transactionId
				sb.append(String.format("%-10s", nvl(validateRes.getData().getDestination().getOperatorCode())));             // data.destination.operatorCode
				sb.append(String.format("%-10s", nvl(validateRes.getData().getDestination().getCountry())));                  // data.destination.country
				sb.append(String.format("%-20s", nvl(validateRes.getData().getDestination().getWithdrawalId())));             // data.destination.withdrawalId
				sb.append(String.format("%-20s", nvl(validateRes.getData().getDestination().getReceive().getAmount())));      // data.destination.receive.amount
				sb.append(String.format("%-10s", nvl(validateRes.getData().getDestination().getReceive().getCurrency())));    // data.destination.receive.currency
				
				sb.append(String.format("%-20s", nvl(validateRes.getData().getReceiver().getFirstName())));                   // data.receiver.firstName
				sb.append(String.format("%-20s", nvl(validateRes.getData().getReceiver().getLastName())));                    // data.receiver.lastName
				sb.append(String.format("%-20s", nvl(validateRes.getData().getReceiver().getBankCode())));                    // data.receiver.bankCode
				sb.append(String.format("%-20s", nvl(validateRes.getData().getReceiver().getAccountId())));                   // data.receiver.accountId
				
				sb.append(String.format("%-20s", nvl(validateRes.getData().getDeliveryMethod())));                            // data.deliveryMethod
				sb.append(String.format("%-50s", nvl(validateRes.getData().getRemark())));                                    // data.remark
				
				sb.append(String.format("%-20s", nvl(validateRes.getData().getFee().getAmount())));                           // data.fee.amount
				sb.append(String.format("%-10s", nvl(validateRes.getData().getFee().getCurrency())));                         // data.fee.currency
				sb.append(String.format("%-20s", nvl(validateRes.getData().getFee().getModel())));                            // data.fee.model
				sb.append(String.format("%-20s", nvl(validateRes.getData().getRate().getFrom().getAmount())));                // data.rate.from.amount
				sb.append(String.format("%-10s", nvl(validateRes.getData().getRate().getFrom().getCurrency())));              // data.rate.from.currency
				sb.append(String.format("%-20s", nvl(validateRes.getData().getRate().getTo().getAmount())));                  // data.rate.to.amount
				sb.append(String.format("%-10s", nvl(validateRes.getData().getRate().getTo().getCurrency())));                // data.rate.to.currency
				
				sb.append(String.format("%-50s", nvl(validateRes.getData().getTransactionId())));                             // data.transactionId
				sb.append(String.format("%-20s", nvl(validateRes.getData().getStatus())));                                    // data.status
				
				sb.append(String.format("%-20s", nvl(validateRes.getData().getSaveReport())));                                // data.saveReport
				sb.append(String.format("%-30s", nvl(validateRes.getData().getRemittanceType())));                            // data.remittanceType
				
				sb.append(String.format("%-20s", nvl(validateRes.getStatus())));                                              // status
				sb.append(String.format("%-50s", nvl(validateRes.getMessage())));                                             // message
				
				int length = 4 + sb.length();
				sb.insert(0, String.format("%04d", length));
				
				if (Flag.flag) System.out.printf(">>>>> (%04d) [%s]\n", sb.length(), sb.toString());
				
				strValidateRes = sb.toString();
			}
			
			//strValidateRes = "07610102RES9974531076200937    hanwha    KOR       1000.0100           USD       NDBXMX              GYQMNB              KOR       idNumber            senderAddress       senderCity          THA       senderZipCode       881111111           66        D014017804533540    scb       THA                           31040.5700          THB       SOPIDA              WANGKIATKUL         SICOTHBK            6032668977          account_deposit                                                       0.7000              USD       included            1.0000              USD       31.0620             THB       62bbb4b8-fe03-40d2-6f30-92baf54da82d              PENDING                                 MESSAGING                     success             OK                  ";
			//strValidateRes = "07610102RES9974531076200937    hanwha    KOR2      1000.0100           USD       NDBXMX              GYQMNB              KOR       idNumber            senderAddress       senderCity          THA       senderZipCode       881111111           66        D014017804533540    scb       THA                           31040.5700          THB       SOPIDA              WANGKIATKUL         SICOTHBK            6032668977          account_deposit                                                       0.7000              USD       included            1.0000              USD       31.0620             THB       62bbb4b8-fe03-40d2-6f30-92baf54da82d              PENDING                                 MESSAGING                     success             OK                  ";
			//strValidateRes = "07910102RES9974531076200937    hanwha    KOR       1000.0100           USD       NDBXMX              GYQMNB              KOR       idNumber            senderAddress       senderCity          THA       senderZipCode       881111111           66        D014017804533540    scb       THA                           31040.5700          THB       SOPIDA              WANGKIATKUL         SICOTHBK            6032668977          account_deposit                                                       0.7000              USD       included            1.0000              USD       31.0620             THB       62bbb4b8-fe03-40d2-6f30-92baf54da82d              PENDING                                 MESSAGING                     success             OK                                                ";
			//strValidateRes = "07910102RES9974531076200937    hanwha    KOR2      1000.0100           USD       NDBXMX              GYQMNB              KOR       idNumber            senderAddress       senderCity          THA       senderZipCode       881111111           66        D014017804533540    scb       THA                           31040.5700          THB       SOPIDA              WANGKIATKUL         SICOTHBK            6032668977          account_deposit                                                       0.7000              USD       included            1.0000              USD       31.0620             THB       62bbb4b8-fe03-40d2-6f30-92baf54da82d              PENDING                                 MESSAGING                     success             OK                                                ";
			
			ValidateRes dummyValidateRes = new ObjectMapper().readValue(new File("src/test/java/org/tain/validate_res_dummy.json"), ValidateRes.class);
			if (Flag.flag) {
				System.out.printf(">>>>> (%d) [%s]\n", strValidateRes.length(), strValidateRes);
				
				int offset = 0;
				int size = -1;
				
				int length = -1;
				String division = null;
				String type = null;
				
				size = 4; length = Integer.valueOf(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 4; division = nvl(strValidateRes.substring(offset, offset+size).trim()); offset += size;
				size = 3; type = nvl(strValidateRes.substring(offset, offset+size).trim()); offset += size;
				
				size = 20; dummyValidateRes.getData().getSource().setTransactionId(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getSource().setOperatorCode(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getSource().setCountry(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getSource().getSend().setAmount(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getSource().getSend().setCurrency(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyValidateRes.getData().getSender().setFirstName(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getSender().setLastName(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getSender().setNationalityCountryCode(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getSender().setIdNumber(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getSender().getAddress().setAddress(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getSender().getAddress().setCity(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getSender().getAddress().setCountryCode(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getSender().getAddress().setPostalCode(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getSender().getMobilePhone().setNumber(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getSender().getMobilePhone().setCountryCode(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyValidateRes.getData().getDestination().setTransactionId(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getDestination().setOperatorCode(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getDestination().setCountry(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getDestination().setWithdrawalId(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getDestination().getReceive().setAmount(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getDestination().getReceive().setCurrency(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyValidateRes.getData().getReceiver().setFirstName(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getReceiver().setLastName(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getReceiver().setBankCode(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getReceiver().setAccountId(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyValidateRes.getData().setDeliveryMethod(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 50; dummyValidateRes.getData().setRemark(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyValidateRes.getData().getFee().setAmount(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getFee().setCurrency(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getFee().setModel(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getRate().getFrom().setAmount(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getRate().getFrom().setCurrency(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().getRate().getTo().setAmount(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 10; dummyValidateRes.getData().getRate().getTo().setCurrency(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				
				size = 50; dummyValidateRes.getData().setTransactionId(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 20; dummyValidateRes.getData().setStatus(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyValidateRes.getData().setSaveReport(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 30; dummyValidateRes.getData().setRemittanceType(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				
				size = 20; dummyValidateRes.setStatus(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				size = 50; dummyValidateRes.setMessage(nvl(strValidateRes.substring(offset, offset+size).trim())); offset += size;
				
				if (Flag.flag) {
					System.out.printf(">>>>> %d %s %s\n", length, division, type);
					
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
					String jsonDummyValidateReq = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dummyValidateRes);
					System.out.println(">>>>> " + jsonDummyValidateReq);
				}
			}
		}
		
		System.out.println("--------------------------- E N D -------------------------------");
	}
	
	private static String nvl(String str) {
		if (str == null || "null".equals(str)) {
			return "";
		} else if ("".equals(str)) {
			return null;
		}
		return str;
	}
}
