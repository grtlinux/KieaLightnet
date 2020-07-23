package org.tain.scheduler;

import org.tain.object.LnsPacket;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateScheduler {

	public static LnsPacket process(LnsPacket request) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		return request;
	}
}
