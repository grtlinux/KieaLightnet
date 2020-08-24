package org.tain.socket;

import java.net.Socket;

import org.tain.object.lns.LnsStream;
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
			try {
				do {
					reqLnsStream = this.streamPacket.recvStream();
					log.info("SERVER req >>>>> " + JsonPrint.getInstance().toPrettyJson(reqLnsStream));
					
					Sleep.run(1000);
					String resData = reqLnsStream.getData().replace("12345", "54321");
					resLnsStream = new LnsStream(resData);
					resLnsStream.setTrTypeCode("0210100");
					resLnsStream.combind();
					
					resLnsStream = this.streamPacket.sendStream(resLnsStream);
					log.info("SERVER res >>>>> " + JsonPrint.getInstance().toPrettyJson(resLnsStream));
				} while(resLnsStream != null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.flag) {
			// test and real
			LnsStream reqLnsStream = null;
			LnsStream resLnsStream = null;
			try {
				do {
					reqLnsStream = this.streamPacket.recvStream();
					log.info("SERVER >>>>> " + JsonPrint.getInstance().toPrettyJson(reqLnsStream));
					
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
				} while(resLnsStream != null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
