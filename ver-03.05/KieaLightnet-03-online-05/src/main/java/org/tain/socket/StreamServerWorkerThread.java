package org.tain.socket;

import java.net.Socket;

import org.tain.object.LnsPacket;
import org.tain.scheduler.TridScheduler;
import org.tain.scheduler.ValidateScheduler;
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
			LnsPacket reqLnsPacket = null;
			LnsPacket resLnsPacket = null;
			try {
				do {
					reqLnsPacket = this.streamPacket.recvPacket();
					log.info("SERVER >>>>> " + JsonPrint.getInstance().toPrettyJson(reqLnsPacket));
					Sleep.run(1000);
					String data = reqLnsPacket.getData().replace("12345", "54321");
					resLnsPacket = new LnsPacket(data);
					resLnsPacket = this.streamPacket.sendPacket(resLnsPacket);
				} while(resLnsPacket != null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.flag) {
			// test and real
			LnsPacket reqLnsPacket = null;
			LnsPacket resLnsPacket = null;
			try {
				do {
					reqLnsPacket = this.streamPacket.recvPacket();
					log.info("SERVER >>>>> " + JsonPrint.getInstance().toPrettyJson(reqLnsPacket));
					
					switch (reqLnsPacket.getDivision()) {
					case "0101":
						resLnsPacket = ValidateScheduler.process(reqLnsPacket);
						break;
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
						resLnsPacket = TridScheduler.process(reqLnsPacket);
						break;
					default:
						break;
					}
					resLnsPacket = this.streamPacket.sendPacket(resLnsPacket);
				} while(resLnsPacket != null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
