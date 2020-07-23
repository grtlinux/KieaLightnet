package org.tain.socket;

import java.net.Socket;

import org.tain.object.LnsPacket;
import org.tain.scheduler.ValidateScheduler;
import org.tain.utils.CurrentInfo;

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
		
		LnsPacket reqPacket = null;
		LnsPacket resPacket = null;
		try {
			do {
				reqPacket = this.streamPacket.recvPacket();
				log.info("SERVER >>>>> " + reqPacket.toPrettyJson());
				
				switch (reqPacket.getDivision()) {
				case "0101":
					resPacket = ValidateScheduler.process(reqPacket);
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
					//response = TransactionIdScheduler.process(request);
					break;
				default:
					break;
				}
				resPacket = this.streamPacket.sendPacket(resPacket);
			} while(resPacket != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
