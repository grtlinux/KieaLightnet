package org.tain.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ServerMainJob {

	@Async(value = "serverMainTask")
	public void serverMainJob(String param) throws Exception {
		//log.info("KANG-20200721 >>>>> START param = {}, {}", param, CurrentInfo.get());
		
		if (Flag.flag) {
			// server socket listen
			int sec = 10;
			log.info("KANG-20200721 >>>>> SLEEP param = {}, sec = {}, {}", param, sec, CurrentInfo.get());
			Sleep.run(sec * 1000);
		}
		
		//log.info("KANG-20200721 >>>>> END   param = {}, {}", param, CurrentInfo.get());
	}
}
