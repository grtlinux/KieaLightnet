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
import org.tain.object.auth.req._ReqAuthData;
import org.tain.object.commit.req._ReqCommitData;
import org.tain.object.customers.req._ReqCustomersData;
import org.tain.object.detail.req._ReqDetailData;
import org.tain.object.histories.req._ReqHistoriesData;
import org.tain.object.refund.req._ReqRefundData;
import org.tain.object.validate.ValidateReqJson;
import org.tain.object.validate.req._ReqValidateData;
import org.tain.properties.ProjEnvJobProperties;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.TransactionId;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
			
			String httpPath = "/auth";
			String method = "post";
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqAuthData reqData = new _ReqAuthData();
				reqData.setClientId(this.projEnvJobProperties.getAuthClientId());
				reqData.setSecret(this.projEnvJobProperties.getAuthSecret());
				
				//reqJson = AuthReqJson.get_20200913();
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				log.info(">>>>> REQ.reqJson        = {}", reqJson);
			}
			
			String httpUrl = null;
			HttpMethod httpMethod = null;
			HttpEntity<String> reqHttpEntity = null;
			ResponseEntity<String> response = null;
			
			if ("POST".equalsIgnoreCase(method)) {
				// post method
				httpMethod = HttpMethod.POST;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl        = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			} else if ("GET".equalsIgnoreCase(method)) {
				// get method
				httpMethod = HttpMethod.GET;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl 1      = {} {}", httpMethod, httpUrl);
				
				Map<String,String> reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				httpUrl = builder.toString();
				log.info(">>>>> REQ.httpUrl 2      = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqHeaders);
			}
			
			if (Flag.flag) {
				try {
					response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, httpMethod
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> RES.getBody()            = {}", response.getBody());
					
					this.accessToken = response.getHeaders().get("AccessToken").get(0);
					log.info(">>>>> RES.accessToken          = {}", this.accessToken);
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
					log.error("ERROR RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.error("ERROR RES.getStatusCode()      = {}", response.getStatusCode());
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
			log.info("================== START: 2. detail(GET) =====================");
			
			String httpPath = "/remittances.detail";
			String method = "get";
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqDetailData reqData = new _ReqDetailData();
				//reqJson = DetailReqJson.get_20200913();
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				log.info(">>>>> REQ.reqJson        = {}", reqJson);
			}
			
			String httpUrl = null;
			HttpMethod httpMethod = null;
			HttpEntity<String> reqHttpEntity = null;
			ResponseEntity<String> response = null;
			
			if ("POST".equalsIgnoreCase(method)) {
				// post method
				httpMethod = HttpMethod.POST;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl        = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			} else if ("GET".equalsIgnoreCase(method)) {
				// get method
				httpMethod = HttpMethod.GET;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl 1      = {} {}", httpMethod, httpUrl);
				
				Map<String,String> reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				httpUrl = builder.toString();
				log.info(">>>>> REQ.httpUrl 2      = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + this.accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqHeaders);
			}
			
			if (Flag.flag) {
				try {
					response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, httpMethod
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> RES.getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
					log.error("ERROR RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.error("ERROR RES.getStatusCode()      = {}", response.getStatusCode());
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
			log.info("================== START: 3. validate(POST) ===================");
			
			String httpPath = "/remittances.validate";
			String method = "post";
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqValidateData reqData = new _ReqValidateData();
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				reqJson = ValidateReqJson.get_20200913();
				//log.info(">>>>> REQ.reqJson        = {}", reqJson);
				
				JsonNode jsonNode = new ObjectMapper().readTree(reqJson);
				((ObjectNode) jsonNode.at("/source")).put("transactionId", TransactionId.get());
				((ObjectNode) jsonNode.at("/sender")).put("firstName", "KANG" + TransactionId.getRandString());
				((ObjectNode) jsonNode.at("/sender")).putNull("middleName");
				reqJson = jsonNode.toPrettyString();
				log.info(">>>>> REQ.reqJson        = {}", reqJson);
			}
			
			String httpUrl = null;
			HttpMethod httpMethod = null;
			HttpEntity<String> reqHttpEntity = null;
			ResponseEntity<String> response = null;
			
			if ("POST".equalsIgnoreCase(method)) {
				// post method
				httpMethod = HttpMethod.POST;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl        = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			} else if ("GET".equalsIgnoreCase(method)) {
				// get method
				httpMethod = HttpMethod.GET;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl 1      = {} {}", httpMethod, httpUrl);
				
				Map<String,String> reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				httpUrl = builder.toString();
				log.info(">>>>> REQ.httpUrl 2      = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + this.accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqHeaders);
			}
			
			if (Flag.flag) {
				try {
					response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, httpMethod
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> RES.getBody()            = {}", response.getBody());
					
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
					log.error("ERROR RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.error("ERROR RES.getStatusCode()      = {}", response.getStatusCode());
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
			
			String httpPath = "/remittances.commit";
			String method = "post";
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqCommitData reqData = new _ReqCommitData();
				reqData.setTransactionId(this.lnsTransactionId);
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				//reqJson = ValidateReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson        = {}", reqJson);
			}
			
			String httpUrl = null;
			HttpMethod httpMethod = null;
			HttpEntity<String> reqHttpEntity = null;
			ResponseEntity<String> response = null;
			
			if ("POST".equalsIgnoreCase(method)) {
				// post method
				httpMethod = HttpMethod.POST;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl        = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			} else if ("GET".equalsIgnoreCase(method)) {
				// get method
				httpMethod = HttpMethod.GET;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl 1      = {} {}", httpMethod, httpUrl);
				
				Map<String,String> reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				httpUrl = builder.toString();
				log.info(">>>>> REQ.httpUrl 2      = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + this.accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqHeaders);
			}
			
			if (Flag.flag) {
				try {
					response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, httpMethod
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> RES.getBody()            = {}", response.getBody());
					
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
					log.error("ERROR RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.error("ERROR RES.getStatusCode()      = {}", response.getStatusCode());
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
			
			String httpPath = "/remittances.amend";
			String method = "post";
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqAmendData reqData = new _ReqAmendData();
				reqData.setTransactionId(this.lnsTransactionId);
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				//reqJson = ValidateReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson        = {}", reqJson);
			}
			
			String httpUrl = null;
			HttpMethod httpMethod = null;
			HttpEntity<String> reqHttpEntity = null;
			ResponseEntity<String> response = null;
			
			if ("POST".equalsIgnoreCase(method)) {
				// post method
				httpMethod = HttpMethod.POST;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl        = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			} else if ("GET".equalsIgnoreCase(method)) {
				// get method
				httpMethod = HttpMethod.GET;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl 1      = {} {}", httpMethod, httpUrl);
				
				Map<String,String> reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				httpUrl = builder.toString();
				log.info(">>>>> REQ.httpUrl 2      = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + this.accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqHeaders);
			}
			
			if (Flag.flag) {
				try {
					response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, httpMethod
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> RES.getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
					log.error("ERROR RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.error("ERROR RES.getStatusCode()      = {}", response.getStatusCode());
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
			
			String httpPath = "/remittances.refund";
			String method = "post";
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqRefundData reqData = new _ReqRefundData();
				reqData.setTransactionId(this.lnsTransactionId);
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				//reqJson = ValidateReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson        = {}", reqJson);
			}
			
			String httpUrl = null;
			HttpMethod httpMethod = null;
			HttpEntity<String> reqHttpEntity = null;
			ResponseEntity<String> response = null;
			
			if ("POST".equalsIgnoreCase(method)) {
				// post method
				httpMethod = HttpMethod.POST;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl        = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			} else if ("GET".equalsIgnoreCase(method)) {
				// get method
				httpMethod = HttpMethod.GET;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl 1      = {} {}", httpMethod, httpUrl);
				
				Map<String,String> reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				httpUrl = builder.toString();
				log.info(">>>>> REQ.httpUrl 2      = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + this.accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqHeaders);
			}
			
			if (Flag.flag) {
				try {
					response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, httpMethod
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> RES.getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
					log.error("ERROR RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.error("ERROR RES.getStatusCode()      = {}", response.getStatusCode());
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
			
			String httpPath = "/remittances/customers";
			httpPath = "/remittances.customers-lookup";
			String method = "get";
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqCustomersData reqData = new _ReqCustomersData();
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				//reqJson = CustomersReqJson.get_20200913();
				log.info(">>>>> REQ.reqJson        = {}", reqJson);
			}
			
			String httpUrl = null;
			HttpMethod httpMethod = null;
			HttpEntity<String> reqHttpEntity = null;
			ResponseEntity<String> response = null;
			
			if ("POST".equalsIgnoreCase(method)) {
				// post method
				httpMethod = HttpMethod.POST;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl        = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			} else if ("GET".equalsIgnoreCase(method)) {
				// get method
				httpMethod = HttpMethod.GET;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl 1      = {} {}", httpMethod, httpUrl);
				
				Map<String,String> reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				httpUrl = builder.toString();
				log.info(">>>>> REQ.httpUrl 2      = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + this.accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqHeaders);
			}
			
			if (Flag.flag) {
				try {
					response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, httpMethod
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> RES.getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
					log.error("ERROR RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.error("ERROR RES.getStatusCode()      = {}", response.getStatusCode());
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
			
			String httpPath = "/remittances";
			String method = "get";
			
			String reqJson = null;
			if (Flag.flag) {
				_ReqHistoriesData reqData = new _ReqHistoriesData();
				//reqJson = HistoriesReqJson.get_20200913();
				reqJson = JsonPrint.getInstance().toPrettyJson(reqData);
				log.info(">>>>> REQ.reqJson        = {}", reqJson);
			}
			
			String httpUrl = null;
			HttpMethod httpMethod = null;
			HttpEntity<String> reqHttpEntity = null;
			ResponseEntity<String> response = null;
			
			if ("POST".equalsIgnoreCase(method)) {
				// post method
				httpMethod = HttpMethod.POST;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl        = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);
			} else if ("GET".equalsIgnoreCase(method)) {
				// get method
				httpMethod = HttpMethod.GET;
				switch (httpPath) {
				case "/auth":
					httpUrl = this.projEnvUrlProperties.getLightnet1() + httpPath;
					break;
				default:
					httpUrl = this.projEnvUrlProperties.getLightnet11() + httpPath;
					break;
				}
				log.info(">>>>> REQ.httpUrl 1      = {} {}", httpMethod, httpUrl);
				
				Map<String,String> reqMap = new ObjectMapper().readValue(reqJson, new TypeReference<Map<String,String>>(){});
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.setAll(reqMap);
				
				UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
						.queryParams(map)
						.build(true);
				httpUrl = builder.toString();
				log.info(">>>>> REQ.httpUrl 2      = {} {}", httpMethod, httpUrl);
				
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				if (!"/auth".equals(httpPath)) reqHeaders.set("Authorization", "Bearer " + this.accessToken);
				log.info(">>>>> REQ.reqHeaders     = {}", reqHeaders);
				
				reqHttpEntity = new HttpEntity<>(reqHeaders);
			}
			
			if (Flag.flag) {
				try {
					response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
							httpUrl
							, httpMethod
							, reqHttpEntity
							, String.class);
					
					log.info(">>>>> RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.info(">>>>> RES.getStatusCode()      = {}", response.getStatusCode());
					log.info(">>>>> RES.getBody()            = {}", response.getBody());
					
					JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
					log.info(">>>>> jsonNode             = {}", jsonNode.toPrettyString());
				} catch (Exception e) {
					//e.printStackTrace();
					log.error("ERROR >>>>> {}", e.getMessage());
					log.error("ERROR RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
					log.error("ERROR RES.getStatusCode()      = {}", response.getStatusCode());
				}
			}
			
			log.info("===============================================================");
		}
	}
}
