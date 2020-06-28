package org.tain.socket;

import java.net.Socket;
import java.util.stream.IntStream;

import org.tain.object.Packet;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

public class StreamClientWorkerThread extends Thread {

	private Socket socket = null;
	
	private StreamPacket streamPacket = null;
	
	public StreamClientWorkerThread(Socket socket) throws Exception {
		this.socket = socket;
		this.streamPacket = new StreamPacket(this.socket);
	}
	
	@Override
	public void run() {
		if (Flag.flag) {
			try {
				IntStream.rangeClosed(1, 10).forEach(index -> {
					String request = String.format("Hello, world!!!! index is %d...to LNS02", index);
					Packet packet = this.streamPacket.sendPacket(request);
					if (Flag.flag) System.out.println("CLIENT >>>>> " + packet);
					
					Sleep.run(10 * 1000);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
