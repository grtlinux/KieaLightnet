package org.tain.working.apis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.tain.object.amend.req._ReqAmendData;
import org.tain.object.auth.AuthReqJson;
import org.tain.object.auth.req._ReqAuthData;
import org.tain.object.commit.req._ReqCommitData;
import org.tain.object.customers.req._ReqCustomersData;
import org.tain.object.detail.DetailReqJson;
import org.tain.object.detail.req._ReqDetailData;
import org.tain.object.histories.HistoriesReqJson;
import org.tain.object.histories.req._ReqHistoriesData;
import org.tain.object.refund.req._ReqRefundData;
import org.tain.object.validate.ValidateReqJson;
import org.tain.object.validate.req._ReqValidateData;
import org.tain.object.validate.req._ReqValidateSource;
import org.tain.properties.ProjEnvJobProperties;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApisWorking {

	@Autowired
	private ProjEnvJobProperties projEnvJobProperties;
	
	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	private String accessToken;
	private String lnsTransactionId;
	private String srcTransactionId;
	private String dstTransactionId;
	
	///////////////////////////////////////////////////////////////////////////
	
	public void transaction() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		switch (this.projEnvJobProperties.getTransaction()) {
		case "auth":          // 1. auth
			this.auth();
			break;
		case "detail":        // 2. detail
			this.auth();
			this.detail();
			break;
		case "validate":      // 3. validate
			this.auth();
			this.validate();
			break;
		case "commit":        // 4. commit
			this.auth();
			this.validate();
			this.commit();
			break;
		case "amend":         // 5. amend : 수정, 정정
			this.auth();
			this.validate();
			this.commit();
			this.amend();
			break;
		case "refund":        // 6. refund : 반환, 돌려주다
			this.auth();
			this.validate();
			this.commit();
			this.refund();
			break;
		case "customers":     // 7. customers : select
			this.auth();
			this.customers();
			break;
		case "histories":     // 8. histories : select
			this.auth();
			this.histories();
			break;
		default:
			this.auth();
			break;
		}
		
		if (Flag.flag) System.exit(0);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void auth() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== START: 1. authentication(POST) =============");
			
			Map<String,String> reqMap = null;
			String reqJson = null;
			if (Flag.flag) {
				_ReqAuthData reqData = new _ReqAuthData();
				reqData.setClientId(this.projEnvJobProperties.getAuthClientId());
				reqData.setSecret(this.projEnvJobProperties.getAuthSecret());
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				reqJson = AuthReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson of req~Data  = {}", reqJson);
				
				reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				reqJson = JsonPrint.getInstance().toPrettyJson(reqMap);
				log.info(">>>>> REQ.reqJson of reqMap  = {}", reqJson);
			}
			
			String httpUrl = null;
			if (Flag.flag) {
				httpUrl = this.projEnvUrlProperties.getLightnet1() + "/auth";
				log.info(">>>>> httpUrl              = {}", httpUrl);
			}
			
			if (Flag.flag) {
				try {
					HttpHeaders reqHeaders = new HttpHeaders();
					reqHeaders.setContentType(MediaType.APPLICATION_JSON);
					HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
					
					ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, HttpMethod.POST
							, reqHttpEntity
							, String.class);
					
					this.accessToken = response.getHeaders().get("AccessToken").get(0);
					
					log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> getBody()            = {}", response.getBody());
					
					log.info(">>>>> accessToken          = {}", this.accessToken);
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
				}
			}
			
			log.info("===============================================================");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void detail() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== START: 2. detail(GET) ==============");
			
			Map<String,String> reqMap = null;
			String reqJson = null;
			if (Flag.flag) {
				_ReqDetailData reqData = new _ReqDetailData();
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				reqJson = DetailReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson of req~Data  = {}", reqJson);
				
				reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				reqJson = JsonPrint.getInstance().toPrettyJson(reqMap);
				log.info(">>>>> REQ.reqJson of reqMap  = {}", reqJson);
			}
			
			String httpUrl = null;
			if (Flag.flag) {
				httpUrl = this.projEnvUrlProperties.getLightnet11() + "/remittances.detail";
				log.info(">>>>> httpUrl              = {}", httpUrl);
			}
			
			UriComponents builder = null;
			if (Flag.flag) {
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				log.info(">>>>> builder.toString     = {}", builder.toString());
			}
			
			if (Flag.flag) {
				try {
					HttpHeaders reqHeaders = new HttpHeaders();
					reqHeaders.setContentType(MediaType.APPLICATION_JSON);
					reqHeaders.set("Authorization", "Bearer " + accessToken);
					HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqHeaders);
					
					ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							builder.toString()
							, HttpMethod.GET
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
				}
			}
			
			log.info("===============================================================");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void validate() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== START: 3. validate(POST) =============");
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqValidateData reqData = new _ReqValidateData();
				_ReqValidateSource source = new _ReqValidateSource();
				source.setTransactionId("43246826351573" + "00");
				reqData.setSource(source);
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				reqJson = ValidateReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson of req~Data  = {}", reqJson);
			}
			
			String httpUrl = null;
			if (Flag.flag) {
				httpUrl = this.projEnvUrlProperties.getLightnet11() + "/remittances.validate";
				log.info(">>>>> httpUrl              = {}", httpUrl);
			}
			
			if (Flag.flag) {
				try {
					HttpHeaders reqHeaders = new HttpHeaders();
					reqHeaders.setContentType(MediaType.APPLICATION_JSON);
					reqHeaders.set("Authorization", "Bearer " + accessToken);
					HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
					
					ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, HttpMethod.POST
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
					
					this.lnsTransactionId = jsonNode.at("/data/transactionId").asText();
					this.srcTransactionId = jsonNode.at("/data/source/transactionId").asText();
					this.dstTransactionId = jsonNode.at("/data/destination/transactionId").asText();
					log.info(">>>>> lnsTransactionId     = {}", this.lnsTransactionId);
					log.info(">>>>> srcTransactionId     = {}", this.srcTransactionId);
					log.info(">>>>> dstTransactionId     = {}", this.dstTransactionId);
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
				}
			}
			
			log.info("===============================================================");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void commit() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== START: 4. commit(POST) ===============");
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqCommitData reqData = new _ReqCommitData();
				reqData.setTransactionId(this.lnsTransactionId);
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				//reqJson = ValidateReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson of req~Data  = {}", reqJson);
			}
			
			String httpUrl = null;
			if (Flag.flag) {
				httpUrl = this.projEnvUrlProperties.getLightnet11() + "/remittances.commit";
				log.info(">>>>> httpUrl              = {}", httpUrl);
			}
			
			if (Flag.flag) {
				try {
					HttpHeaders reqHeaders = new HttpHeaders();
					reqHeaders.setContentType(MediaType.APPLICATION_JSON);
					reqHeaders.set("Authorization", "Bearer " + accessToken);
					HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
					
					ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, HttpMethod.POST
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
					
					this.lnsTransactionId = jsonNode.at("/data/transactionId").asText();
					this.srcTransactionId = jsonNode.at("/data/source/transactionId").asText();
					this.dstTransactionId = jsonNode.at("/data/destination/transactionId").asText();
					log.info(">>>>> lnsTransactionId     = {}", this.lnsTransactionId);
					log.info(">>>>> srcTransactionId     = {}", this.srcTransactionId);
					log.info(">>>>> dstTransactionId     = {}", this.dstTransactionId);
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
				}
			}
			
			log.info("===============================================================");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void amend() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== START: 5. amend(POST) ================");
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqAmendData reqData = new _ReqAmendData();
				reqData.setTransactionId(this.lnsTransactionId);
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				//reqJson = ValidateReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson of req~Data  = {}", reqJson);
			}
			
			String httpUrl = null;
			if (Flag.flag) {
				httpUrl = this.projEnvUrlProperties.getLightnet11() + "/remittances.amend";
				log.info(">>>>> httpUrl              = {}", httpUrl);
			}
			
			if (Flag.flag) {
				try {
					HttpHeaders reqHeaders = new HttpHeaders();
					reqHeaders.setContentType(MediaType.APPLICATION_JSON);
					reqHeaders.set("Authorization", "Bearer " + accessToken);
					HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
					
					ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, HttpMethod.POST
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
				}
			}
			
			log.info("===============================================================");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void refund() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== START: 6. refund(POST) ===============");
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqRefundData reqData = new _ReqRefundData();
				reqData.setTransactionId(this.lnsTransactionId);
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				//reqJson = ValidateReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson of req~Data  = {}", reqJson);
			}
			
			String httpUrl = null;
			if (Flag.flag) {
				httpUrl = this.projEnvUrlProperties.getLightnet11() + "/remittances.refund";
				log.info(">>>>> httpUrl              = {}", httpUrl);
			}
			
			if (Flag.flag) {
				try {
					HttpHeaders reqHeaders = new HttpHeaders();
					reqHeaders.setContentType(MediaType.APPLICATION_JSON);
					reqHeaders.set("Authorization", "Bearer " + accessToken);
					HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
					
					ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, HttpMethod.POST
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
				}
			}
			
			log.info("===============================================================");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void customers() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== START: 7. customers(GET) ===========");
			
			Map<String,String> reqMap = null;
			String reqJson = null;
			if (Flag.flag) {
				_ReqCustomersData reqData = new _ReqCustomersData();
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				//reqJson = CustomersReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson of req~Data  = {}", reqJson);
				
				reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				reqJson = JsonPrint.getInstance().toPrettyJson(reqMap);
				log.info(">>>>> REQ.reqJson of reqMap  = {}", reqJson);
			}
			
			String httpUrl = null;
			if (Flag.flag) {
				httpUrl = this.projEnvUrlProperties.getLightnet11() + "/remittances/customers";
				log.info(">>>>> httpUrl              = {}", httpUrl);
			}
			
			UriComponents builder = null;
			if (Flag.flag) {
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				log.info(">>>>> builder.toString     = {}", builder.toString());
			}
			
			if (Flag.flag) {
				try {
					HttpHeaders reqHeaders = new HttpHeaders();
					reqHeaders.setContentType(MediaType.APPLICATION_JSON);
					reqHeaders.set("Authorization", "Bearer " + accessToken);
					HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqHeaders);
					
					ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							builder.toString()
							, HttpMethod.GET
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
				}
			}
			
			log.info("===============================================================");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void histories() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("================== START: 8. histories(GET) ===========");
			
			Map<String,String> reqMap = null;
			String reqJson = null;
			if (Flag.flag) {
				_ReqHistoriesData reqData = new _ReqHistoriesData();
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				reqJson = HistoriesReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson of req~Data  = {}", reqJson);
				
				reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				reqJson = JsonPrint.getInstance().toPrettyJson(reqMap);
				log.info(">>>>> REQ.reqJson of reqMap  = {}", reqJson);
			}
			
			String httpUrl = null;
			if (Flag.flag) {
				httpUrl = this.projEnvUrlProperties.getLightnet11() + "/remittances";
				log.info(">>>>> httpUrl              = {}", httpUrl);
			}
			
			UriComponents builder = null;
			if (Flag.flag) {
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				log.info(">>>>> builder.toString     = {}", builder.toString());
			}
			
			if (Flag.flag) {
				try {
					HttpHeaders reqHeaders = new HttpHeaders();
					reqHeaders.setContentType(MediaType.APPLICATION_JSON);
					reqHeaders.set("Authorization", "Bearer " + accessToken);
					HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqHeaders);
					
					ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							builder.toString()
							, HttpMethod.GET
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
				}
			}
			
			log.info("===============================================================");
		}
	}
}
