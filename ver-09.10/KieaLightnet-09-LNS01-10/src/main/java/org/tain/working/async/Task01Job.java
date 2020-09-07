package org.tain.working.async;

import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Task01Job {

	//@Async(value = "task01Task")
	public void task01Job(String param) throws Exception {
		log.info("KANG-20200721 >>>>> param = {}, {}", param, CurrentInfo.get());
		
		if (Flag.flag) {
			for (int i=0; i < 3; i++) {
				Sleep.run(2000);
				System.out.println(">>>>> Hello, no-" + i);
			}
		}
	}
}
