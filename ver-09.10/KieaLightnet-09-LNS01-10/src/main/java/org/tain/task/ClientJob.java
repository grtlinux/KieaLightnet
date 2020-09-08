package org.tain.task;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.object.lns.LnsStream;
import org.tain.object.lns.LnsStreamPacket;
import org.tain.object.test.req._ReqData;
import org.tain.object.test.req._ReqName;
import org.tain.object.test.res._ResData;
import org.tain.queue.LnsStreamPacketQueue;
import org.tain.queue.WakeClientTaskQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.Sleep;
import org.tain.utils.SubString;
import org.tain.utils.TransferStrAndJson;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClientJob {

	@Autowired
	private WakeClientTaskQueue wakeClientTaskQueue;
	
	@Autowired
	private LnsStreamPacketQueue lnsStreamPacketQueue;
	
	@Async(value = "clientTask")
	public void clientJob(String param) throws Exception {
		log.info("KANG-20200907 >>>>> START param = {}, {}", param, CurrentInfo.get());
		
		LnsStreamPacket lnsStreamPacket = null;
		
		if (Flag.flag) {
			// get StreamPacket from queue
			lnsStreamPacket = this.lnsStreamPacketQueue.get();  // blocking
			log.info("KANG-20200907 >>>>> REMOTE_INFO = {}", lnsStreamPacket);
		}
		
		////////////////////////////////////////////////////
		LnsStream reqLnsStream = null;
		if (Flag.flag) {
			// get req
			_ReqName name = new _ReqName();
			name.setFirstName("Seok");
			name.setMiddleName("Kiea");
			name.setLastName("Kang");
			
			_ReqData data = new _ReqData();
			data.setTitle("REQ_TITLE");
			data.setMessage("MESSAGE");
			data.setName(name);
			JsonPrint.getInstance().printPrettyJson("REQ :", data);
			
			String reqStream = TransferStrAndJson.getStream(data);
			reqLnsStream = new LnsStream(String.format("%04d%7.7s%s", reqStream.length() + 7, "0200200", reqStream));
		}
		
		if (Flag.flag) {
			// send
			JsonPrint.getInstance().printPrettyJson("REQ", reqLnsStream);
			lnsStreamPacket.sendStream(reqLnsStream);
		}
		
		////////////////////////////////////////////////////
		LnsStream resLnsStream = null;
		if (Flag.flag) {
			// recv
			resLnsStream = lnsStreamPacket.recvStream();
			JsonPrint.getInstance().printPrettyJson("RES", resLnsStream);
		}
		
		if (Flag.flag) {
			// get res
			_ResData data = new _ResData();
			TransferStrAndJson.subString = new SubString(resLnsStream.getContent());
			data = (_ResData) TransferStrAndJson.getObject(data);
			JsonPrint.getInstance().printPrettyJson("RES :", data);
		}
		
		log.info("KANG-20200907 >>>>> END   param = {}, {}", param, CurrentInfo.get());
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Deprecated
	public void serverJob_202009070941(String param) throws Exception {
		//log.info("KANG-20200721 >>>>> START param = {}, {}", param, CurrentInfo.get());
		
		if (Flag.flag) {
			// server socket listen
			Random random = new Random(new Date().getTime());
			int sec = 4 + random.nextInt(5);
			log.info("KANG-20200721 >>>>> SLEEP param = {}, sec = {}, {}", param, sec, CurrentInfo.get());
			Sleep.run(sec * 1000);
		}
		
		//log.info("KANG-20200721 >>>>> END   param = {}, {}", param, CurrentInfo.get());
		
		this.wakeClientTaskQueue.set(null);
	}
}
