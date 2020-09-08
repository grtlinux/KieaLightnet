package org.tain.working.authJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.task.AuthJob;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthJobWorking {

	@Autowired
	private AuthJob authJob;
	
	public void running() {
		log.info("KANG-20200908 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			for (int i=0; i < 1; i++) {
				try {
					this.authJob.authJob("PARAM-" + i);
				} catch (Exception e) {
					//System.out.println(">>>>> EXCEPTION: " + e.getMessage());
					Sleep.run(10 * 1000);
				}
			}
		}
	}
}
