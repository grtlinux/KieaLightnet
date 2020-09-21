package org.tain.data;

import org.tain.object.lns.LnsJson;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.LnsHttpClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LnsData {

	private static LnsData instance = null;
	
	public static LnsData getInstance() {
		if (instance == null) {
			instance = new LnsData();
		}
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private String accessToken = null;
	
	public synchronized void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public synchronized String getAccessToken() {
		return this.accessToken;
	}
	
	public synchronized String getHttpAccessToken() {
		LnsJson lnsJson = null;
		if (Flag.flag) {
			try {
				lnsJson = new LnsJson();
				lnsJson.setName("Link Call Auth");
				lnsJson.setHttpUrl("http://localhost:18081/v1.0/auth");
				lnsJson.setHttpMethod("POST");
				lnsJson = LnsHttpClient.post(lnsJson);
				log.info(">>>>> Link Call Auth: lnsJson  = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
				
				JsonNode jsonNode = new ObjectMapper().readTree(lnsJson.getResJsonData());
				this.accessToken = jsonNode.get("accessToken").asText();
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		
		return this.accessToken;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private String srcTransactionId = null;
	
	public synchronized void setSrcTrId(String srcTransactionId) {
		this.srcTransactionId = srcTransactionId;
	}
	
	public synchronized String getSrcTrId() {
		return this.srcTransactionId;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private String lnsTransactionId = null;
	
	public synchronized void setLnsTrId(String lnsTransactionId) {
		this.lnsTransactionId = lnsTransactionId;
	}
	
	public synchronized String getLnsTrId() {
		return this.lnsTransactionId;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private String dstTransactionId = null;
	
	public synchronized void setDstTrId(String dstTransactionId) {
		this.dstTransactionId = dstTransactionId;
	}
	
	public synchronized String getDstTrId() {
		return this.dstTransactionId;
	}
	
	///////////////////////////////////////////////////////////////////////////
}
