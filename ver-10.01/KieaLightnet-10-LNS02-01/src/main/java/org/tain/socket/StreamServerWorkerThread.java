package org.tain.socket;

import java.net.Socket;

import org.tain.object.Packet;
import org.tain.utils.Flag;

public class StreamServerWorkerThread extends Thread {

	private Socket socket = null;
	private StreamPacket streamPacket = null;
	private Packet packet = null;
	
	public StreamServerWorkerThread(Socket socket) throws Exception {
		this.socket = socket;
		this.streamPacket = new StreamPacket(this.socket);
	}
	
	@Override
	public void run() {
		try {
			do {
				this.packet = this.streamPacket.recvPacket();
				if (Flag.flag) System.out.println("SERVER >>>>> " + this.packet);
				
				//if (this.packet == null)
				//	break;
			} while(this.packet != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
