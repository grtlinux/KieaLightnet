package org.tain.socket;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.tain.object.Packet;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamClientWorkerThread extends Thread {

	private Socket socket = null;
	
	private StreamPacket streamPacket = null;
	
	public StreamClientWorkerThread(Socket socket) throws Exception {
		this.socket = socket;
		this.streamPacket = new StreamPacket(this.socket);
	}
	
	@Override
	public void run() {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) Sleep.run(2 * 1000);
		
		try {
			IntStream.rangeClosed(1, 24 * 60 * 6).forEach(index -> {
				String request = String.format("Hello, world!!!! index is %d...", index);
				Packet packet = this.streamPacket.sendPacket(request);
				if (Flag.flag) System.out.println("CLIENT >>>>> " + packet);
				
				Sleep.run(10 * 1000);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
