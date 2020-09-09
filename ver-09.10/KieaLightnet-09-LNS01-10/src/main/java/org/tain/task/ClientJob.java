package org.tain.task;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.object.list11.req._ReqList11Data;
import org.tain.object.lns.LnsStream;
import org.tain.object.lns.LnsStreamPacket;
import org.tain.object.test.req._ReqTestData;
import org.tain.object.test.req._ReqTestName;
import org.tain.object.test.res._ResTestData;
import org.tain.properties.ProjEnvJsonProperties;
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
	
	@Autowired
	private ProjEnvJsonProperties projEnvJsonProperties;
	
	@Async(value = "clientTask")
	public void clientJob(String param) throws Exception {
		log.info("KANG-20200907 >>>>> START param = {}, {}", param, CurrentInfo.get());
		
		switch (this.projEnvJsonProperties.getTrxName()) {
		case "list11":
			this.list11();
			break;
		case "test":
			this.test();
			break;
		default:
			break;
		}
		
		log.info("KANG-20200907 >>>>> END   param = {}, {}", param, CurrentInfo.get());
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void list11() throws Exception {
		log.info("KANG-20200907 >>>>> {}", CurrentInfo.get());
		
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
			String reqStream = TransferStrAndJson.getStream(reqData);
			reqLnsStream = new LnsStream(String.format("%04d%7.7s%s", reqStream.length() + 7, "0200701", reqStream));
			JsonPrint.getInstance().printPrettyJson("REQ.lnsStream", reqLnsStream);
		}
		
		if (Flag.flag) {
			// send
			lnsStreamPacket.sendStream(reqLnsStream);
		}
		
		////////////////////////////////////////////////////
		/*
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
		*/
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void test() throws Exception {
		log.info("KANG-20200907 >>>>> {}", CurrentInfo.get());
		
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
