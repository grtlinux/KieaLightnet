package org.tain.task.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.object.lns.LnsStream;
import org.tain.object.lns.LnsStreamPacket;
import org.tain.object.test.req._ReqTestData;
import org.tain.object.test.req._ReqTestName;
import org.tain.object.test.res._ResTestData;
import org.tain.queue.LnsStreamPacketQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.SubString;
import org.tain.utils.TransferStrAndJson;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestProcess {

	@Autowired
	private LnsStreamPacketQueue lnsStreamPacketQueue;
	
	public void process() throws Exception {
		log.info("KANG-20200907 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsStreamPacket lnsStreamPacket = null;
			
			if (Flag.flag) {
				// get StreamPacket from queue
				lnsStreamPacket = this.lnsStreamPacketQueue.get();  // blocking
				log.info("KANG-20200907 >>>>> REMOTE_INFO = {}", lnsStreamPacket);
			}
			
			////////////////////////////////////////////////////
			LnsStream reqLnsStream = null;
			if (Flag.flag) {
				// req
				_ReqTestName name = new _ReqTestName();
				name.setFirstName("Seok");
				name.setMiddleName("Kiea");
				name.setLastName("Kang");
				
				_ReqTestData data = new _ReqTestData();
				data.setTitle("REQ_TITLE");
				data.setMessage("MESSAGE");
				data.setName(name);
				JsonPrint.getInstance().printPrettyJson("REQ.data", data);
				
				String reqStream = TransferStrAndJson.getStream(data);
				reqLnsStream = new LnsStream(String.format("%04d%7.7s%s", reqStream.length() + 7, "0200991", reqStream));
				JsonPrint.getInstance().printPrettyJson("REQ.lnsStream", reqLnsStream);
			}
			
			if (Flag.flag) {
				// send
				lnsStreamPacket.sendStream(reqLnsStream);
			}
			
			////////////////////////////////////////////////////
			LnsStream resLnsStream = null;
			if (Flag.flag) {
				// recv
				resLnsStream = lnsStreamPacket.recvStream();
				JsonPrint.getInstance().printPrettyJson("RES.lnsStream", resLnsStream);
			}
			
			if (Flag.flag) {
				// res
				_ResTestData data = new _ResTestData();
				TransferStrAndJson.subString = new SubString(resLnsStream.getContent());
				data = (_ResTestData) TransferStrAndJson.getObject(data);
				JsonPrint.getInstance().printPrettyJson("RES.data", data);
			}
		}
	}
}
