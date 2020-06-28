package org.tain.socket;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
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
			// Random random = new Random(new Date().getTime());
			
			IntStream.rangeClosed(1, 24 * 60 * 6).forEach(index -> {
				
				String request = null;
				int switchNumber = new Random(new Date().getTime()).nextInt(3);
				//switchNumber = 2;
				System.out.println(">>>>> switchNumber = " + switchNumber);
				switch(switchNumber) {
				case 0:
					request = String.format("0101[%d] REQUEST Hello, Batch!", index);  // 8085/batch/list
					break;
				case 1:
					request = String.format("0201[%d] REQUEST Hello, Detail!", index);  // detail
					break;
				case 2:
					request = String.format("0301[%d] REQUEST Hello, List!", index);  // list
					break;
				default:
					break;
				}
				
				Packet packet = this.streamPacket.sendPacket(request);
				if (Flag.flag) System.out.println("CLIENT REQ >>>>> " + packet);
				
				packet = this.streamPacket.recvPacket();
				if (Flag.flag) System.out.println("CLIENT RES >>>>> " + packet);

				Sleep.run(10 * 1000);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
