package org.tain.scheduler;

import org.tain.object.LnsPacket;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateScheduler {

	public static LnsPacket process(LnsPacket request) throws Exception {
		log.info("KANG-20200623 >>>>> {} request.json: {}", CurrentInfo.get(), JsonPrint.getInstance().toPrettyJson(request));
		
		//LnsPacket response = null;
		if (Flag.flag) {
			
		}
		
		return request;
	}
}
