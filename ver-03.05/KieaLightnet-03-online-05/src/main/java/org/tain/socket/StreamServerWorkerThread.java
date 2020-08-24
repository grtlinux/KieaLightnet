package org.tain.socket;

import java.net.Socket;

import org.tain.annotation.SubString;
import org.tain.object.lns.LnsStream;
import org.tain.object.trid.req._TridReqData;
import org.tain.object.trid.res._TridResData;
import org.tain.object.trid.res._TridResTrid;
import org.tain.scheduler.TrIdScheduler;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamServerWorkerThread extends Thread {

	private Socket socket = null;
	private StreamPacket streamPacket = null;
	
	public StreamServerWorkerThread(Socket socket) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		this.socket = socket;
		this.streamPacket = new StreamPacket(this.socket);
	}
	
	@Override
	public void run() {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// test
			LnsStream reqLnsStream = null;
			LnsStream resLnsStream = null;
			_TridReqData reqData = null;
			_TridResData resData = null;
			try {
				do {
					reqLnsStream = this.streamPacket.recvStream();
					log.info("SERVER req >>>>> " + JsonPrint.getInstance().toPrettyJson(reqLnsStream));
					
					if (Flag.flag) {
						// req
						reqData = new _TridReqData();
						reqData.getObject(new SubString(reqLnsStream.getContent()));
						if (Flag.flag) JsonPrint.getInstance().printPrettyJson("REQ_DATA", reqData);
					}
					
					if (Flag.flag) {
						Sleep.run(2000);
						
						// req -> res
						resData = new _TridResData();
						_TridResTrid trid = new _TridResTrid();
						trid.setCommand("trid.ses");
						trid.setTridCode("HWyyMMddHHmmA999");
						resData.setTrid(trid);
						resData.setStatus("RES_STATUS");
						resData.setMessage("RES_MESSAGE: Hello, Kiea....");
						
						resLnsStream = new LnsStream();
						resLnsStream.setTrTypeCode("0210100");
						resLnsStream.setContent(resData.getStream());
						resLnsStream.combind();
					}
					
					if (Flag.flag) {
						// res
						resData = new _TridResData();
						resData.getObject(new SubString(resLnsStream.getContent()));
						if (Flag.flag) JsonPrint.getInstance().printPrettyJson("RES_DATA", resData);
					}
					
					resLnsStream = this.streamPacket.sendStream(resLnsStream);
					log.info("SERVER res >>>>> " + JsonPrint.getInstance().toPrettyJson(resLnsStream));
				} while(resLnsStream != null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.flag) {
			// test and real
			int seq = 0;
			LnsStream reqLnsStream = null;
			LnsStream resLnsStream = null;
			try {
				do {
					reqLnsStream = this.streamPacket.recvStream();
					log.info("[{}]SERVER REQ >>>>> {}", ++seq, JsonPrint.getInstance().toPrettyJson(reqLnsStream));
					
					switch (reqLnsStream.getTrTypeCode()) {
					case "0200100":
						resLnsStream = TrIdScheduler.process(reqLnsStream);
						break;
					/*
					case "0201":
						//response = CommitScheduler.process(request);
						break;
					case "0301":
						//response = DetailScheduler.process(request);
						break;
					case "0401":
						//response = ListScheduler.process(request);
						break;
					case "0501":
						//response = List2Scheduler.process(request);
						break;
					case "0601":
						//response = CallbackScheduler.process(request);
						break;
					case "0701":
						resLnsStream = TridScheduler.process(reqLnsStream);
						break;
					*/
					default:
						break;
					}
					resLnsStream = this.streamPacket.sendStream(resLnsStream);
					log.info("[{}]SERVER REQ >>>>> {}", seq, JsonPrint.getInstance().toPrettyJson(resLnsStream));
				} while(resLnsStream != null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
