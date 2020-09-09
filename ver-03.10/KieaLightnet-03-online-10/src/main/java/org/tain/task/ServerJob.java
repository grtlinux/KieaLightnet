package org.tain.task;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.object.lns.LnsStream;
import org.tain.object.lns.LnsStreamPacket;
import org.tain.object.test.req._ReqTestData;
import org.tain.object.test.res._ResTestData;
import org.tain.object.test.res._ResTestName;
import org.tain.queue.LnsStreamPacketQueue;
import org.tain.queue.WakeServerTaskQueue;
import org.tain.task.process.List11Process;
import org.tain.task.process.TestProcess;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.Sleep;
import org.tain.utils.SubString;
import org.tain.utils.TransferStrAndJson;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ServerJob {

	@Autowired
	private WakeServerTaskQueue wakeServerTaskQueue;
	
	@Autowired
	private LnsStreamPacketQueue lnsStreamPacketQueue;
	
	@Autowired
	private List11Process list11Process;
	
	@Autowired
	private TestProcess testProcess;
	
	@Async(value = "serverTask")
	public void serverJob(String param) throws Exception {
		log.info("KANG-20200908 >>>>> START param = {}, {}", param, CurrentInfo.get());
		
		LnsStreamPacket lnsStreamPacket = null;
		if (Flag.flag) {
			lnsStreamPacket = this.lnsStreamPacketQueue.get();  // blocking
			log.info("KANG-20200907 >>>>> lnsStreamPacket: REMOTE_INFO = {}", lnsStreamPacket);
		}
		
		////////////////////////////////////////////////////
		if (Flag.flag) {
			try {
				LnsStream reqLnsStream = null;
				LnsStream resLnsStream = null;
				do {
					// recv
					reqLnsStream = lnsStreamPacket.recvStream();
					if (Flag.flag) JsonPrint.getInstance().printPrettyJson("REQ.lnsStream", reqLnsStream);
					
					// process
					switch (reqLnsStream.getTypeCode()) {
					case "0200701":  // list11
						resLnsStream = this.list11Process.process(reqLnsStream);
						break;
					case "0200991":  // test
						resLnsStream = this.testProcess.process(reqLnsStream);
						break;
					default:
						break;
					}
					
					// send
					lnsStreamPacket.sendStream(resLnsStream);
					if (Flag.flag) JsonPrint.getInstance().printPrettyJson("RES.lnsStream", resLnsStream);
				} while (true);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	public void serverJob_202009081035(String param) throws Exception {
		log.info("KANG-20200907 >>>>> START param = {}, {}", param, CurrentInfo.get());
		
		LnsStreamPacket lnsStreamPacket = null;
		
		if (Flag.flag) {
			lnsStreamPacket = this.lnsStreamPacketQueue.get();  // blocking
			log.info("KANG-20200907 >>>>> REMOTE_INFO = {}", lnsStreamPacket);
		}
		
		////////////////////////////////////////////////////
		LnsStream reqLnsStream = null;
		if (Flag.flag) {
			// recv
			reqLnsStream = lnsStreamPacket.recvStream();
			if (Flag.flag) JsonPrint.getInstance().printPrettyJson("REQ", reqLnsStream);
		}
		
		if (Flag.flag) {
			// get res
			_ReqTestData data = new _ReqTestData();
			TransferStrAndJson.subString = new SubString(reqLnsStream.getContent());
			data = (_ReqTestData) TransferStrAndJson.getObject(data);
			JsonPrint.getInstance().printPrettyJson("REQ :", data);
		}
		
		Sleep.run(1000);
		
		////////////////////////////////////////////////////
		LnsStream resLnsStream = null;
		if (Flag.flag) {
			// get res
			_ResTestName name = new _ResTestName();
			name.setFirstName("SEOK");
			//name.setMiddleName("");
			name.setLastName("KANG");
			
			_ResTestData data = new _ResTestData();
			data.setTitle("Res_Title");
			data.setMessage("Message");
			data.setStatus("Status");
			data.setName(name);
			JsonPrint.getInstance().printPrettyJson("RES :", data);
			
			String resStream = TransferStrAndJson.getStream(data);
			resLnsStream = new LnsStream(String.format("%04d%7.7s%s", resStream.length() + 7, "0210991", resStream));
		}
		
		if (Flag.flag) {
			// send
			if (Flag.flag) JsonPrint.getInstance().printPrettyJson("RES", resLnsStream);
			lnsStreamPacket.sendStream(resLnsStream);
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
		
		this.wakeServerTaskQueue.set(null);
	}
}
