package org.tain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.httpClient.LnsHttpClient;
import org.tain.mapper.LnsJsonNode;
import org.tain.properties.ProjEnvJobProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthJob {

	@Autowired
	private ProjEnvJobProperties projEnvJobProperties;
	
	@Autowired
	private LnsHttpClient lnsHttpClient;
	
	///////////////////////////////////////////////////////////////////////////
	
	@Async
	public void authJob(String param) {
		log.info("KANG-20200908 >>>>> START param = {}, {}", param, CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				do {
					log.info(">>>>> AuthJob.process()..");
					process();
					
					Sleep.run(this.projEnvJobProperties.getAuthLoopSec() * 1000);
				} while (true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		log.info("KANG-20200907 >>>>> END   param = {}, {}", param, CurrentInfo.get());
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void process() throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		LnsJsonNode lnsJsonNode = null;
		if (Flag.flag) {
			lnsJsonNode = new LnsJsonNode();
			lnsJsonNode.put("clientId", this.projEnvJobProperties.getAuthClientId());
			lnsJsonNode.put("secret", this.projEnvJobProperties.getAuthSecret());
			
			log.info("AuthJob >>>>> REQ.lnsJsonNode  = {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			lnsJsonNode.put("httpUrl", "http://localhost:18081/v1.1/auth/lightnet");
			lnsJsonNode.put("httpMethod", "POST");
			
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("AuthJob >>>>> RES.lnsJsonNode  = {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			/*
			lnsJson = LnsJson.builder().name("Auth").build();
			lnsJson.setHttpUrl("http://localhost:18081/v1.0/auth");
			lnsJson.setHttpMethod("POST");
			lnsJson.setReqJsonData(reqJson);
			
			lnsJson = LnsHttpClient.post(lnsJson);
			log.info(">>>>> RES-2.lnsJson  = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
			*/
		}
	}
}
