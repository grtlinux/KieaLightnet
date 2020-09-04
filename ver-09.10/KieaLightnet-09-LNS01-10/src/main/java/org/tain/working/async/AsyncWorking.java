package org.tain.working.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AsyncWorking {

	@Autowired
	private Task01Job task01Job;
	
	public void runningTaskThread() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			for (int i=0; i < 1; i++) {
				try {
					this.task01Job.task01Job("PARAM-" + i);
				} catch (Exception e) {
					//System.out.println(">>>>> EXCEPTION: " + e.getMessage());
					Sleep.run(10 * 1000);
				}
			}
		}
	}
}
