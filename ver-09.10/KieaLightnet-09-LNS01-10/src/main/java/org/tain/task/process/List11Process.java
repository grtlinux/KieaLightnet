package org.tain.task.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.object.list11.req._ReqList11Data;
import org.tain.object.list11.res._ResList11Fep;
import org.tain.object.lns.LnsStream;
import org.tain.object.lns.LnsStreamPacket;
import org.tain.queue.LnsStreamPacketQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.SubString;
import org.tain.utils.TransferStrAndJson;

import lombok.extern.slf4j.Slf4j;

@Deprecated
@Component
@Slf4j
public class List11Process {

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
				_ReqList11Data reqData = new _ReqList11Data();
				JsonPrint.getInstance().printPrettyJson("REQ.reqData", reqData);
				
				String reqStream = TransferStrAndJson.getStream(reqData);
				reqLnsStream = new LnsStream(String.format("%04d%7.7s%s", reqStream.length() + 7, "0200701", reqStream));
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
				_ResList11Fep resData = new _ResList11Fep();
				TransferStrAndJson.subString = new SubString(resLnsStream.getContent());
				resData = (_ResList11Fep) TransferStrAndJson.getObject(resData);
				JsonPrint.getInstance().printPrettyJson("RES.resData", resData);
			}
		}
	}
}
