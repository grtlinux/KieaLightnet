package org.tain.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.httpClient.LnsHttpClient;
import org.tain.mapper.LnsJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LnsData {

	@Autowired
	private LnsHttpClient lnsHttpClient;
	
	public String getAccessToken() throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		LnsJsonNode lnsJsonNode = null;
		if (Flag.flag) {
			lnsJsonNode = new LnsJsonNode();
			
			log.info("LINK.getAccessToken >>>>> REQ.lnsJsonNode  = {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			lnsJsonNode.put("httpUrl", "http://localhost:18081/v1.1/auth");
			lnsJsonNode.put("httpMethod", "POST");
			
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("LINK.getAccessToken >>>>> RES.lnsJsonNode  = {}", lnsJsonNode.toPrettyString());
		}
		
		String strAccessToken = null;
		if (Flag.flag) {
			strAccessToken = lnsJsonNode.getText("accessToken");
		}
		
		return strAccessToken;
	}
	public void setAccessToken(String accessToken) {
	}
}
