package org.tain.task;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.object.lns.LnsJson;
import org.tain.properties.ProjEnvJobProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.LnsHttpClient;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthJob {

	@Autowired
	private ProjEnvJobProperties projEnvJobProperties;
	
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
		
		String reqJson = null;
		if (Flag.flag) {
			Map<String,String> map = new HashMap<>();
			map.put("clientId", this.projEnvJobProperties.getAuthClientId());
			map.put("secret", this.projEnvJobProperties.getAuthSecret());
			
			reqJson = JsonPrint.getInstance().toPrettyJson(map);
			log.info(">>>>> REQ.reqJson  = {}", reqJson);
		}
		
		LnsJson lnsJson = null;
		if (Flag.flag) {
			lnsJson = LnsJson.builder().name("Auth").build();
			lnsJson.setHttpUrl("http://localhost:18081/v1.0/auth/lightnet");
			lnsJson.setHttpMethod("POST");
			lnsJson.setReqJsonData(reqJson);
			
			lnsJson = LnsHttpClient.post(lnsJson);
			log.info(">>>>> RES-1.lnsJson  = {}", JsonPrint.getInstance().toPrettyJson(lnsJson));
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
