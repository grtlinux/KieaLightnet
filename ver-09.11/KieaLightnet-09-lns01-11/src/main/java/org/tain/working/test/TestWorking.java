package org.tain.working.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.object.lns.LnsStream;
import org.tain.queue.LnsQueueObject;
import org.tain.queue.LnsRecvQueue;
import org.tain.queue.LnsSendQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestWorking {

	@Autowired
	private LnsSendQueue lnsSendQueue;
	
	public void test01() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		Sleep.run(3 * 1000);
		
		if (Flag.flag) {
			LnsRecvQueue lnsRecvQueue = new LnsRecvQueue();
			LnsStream reqLnsStream = null;
			if (Flag.flag) {
				String reqStream = "0123020010099999920201016125525125525                                                               "
						+ "KORUSD100.50         THATHB";
				reqLnsStream = new LnsStream(reqStream);
				log.info("LNS01.REQ >>>>> reqLnsStream = {}", JsonPrint.getInstance().toPrettyJson(reqLnsStream));
			}
			
			if (Flag.flag) {
				LnsQueueObject lnsQueueObject = LnsQueueObject.builder()
						.lnsStream(reqLnsStream)
						.lnsRecvQueue(lnsRecvQueue)
						.build();
				this.lnsSendQueue.set(lnsQueueObject);
			}
			
			LnsStream resLnsStream = null;
			if (Flag.flag) {
				resLnsStream = (LnsStream) lnsRecvQueue.get();
				log.info("LNS01.RES >>>>> resLnsStream = {}", JsonPrint.getInstance().toPrettyJson(resLnsStream));
			}
		}
	}
}
