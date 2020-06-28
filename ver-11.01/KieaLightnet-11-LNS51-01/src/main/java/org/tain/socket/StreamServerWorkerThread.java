package org.tain.socket;

import java.net.Socket;

import org.tain.object.Packet;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

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
				
				Sleep.run(1000);
				
				//Map<String,Object> map = new HashMap<>();
				//map.put("message", "ACKNOWLEDGE by KIEA");
				//map.put("status", "success");
				
				String response = "{\"message\": \"ACKNOWLEDGE by KIEA\", \"status\": \"success\"}";
				
				this.streamPacket.sendPacket(response);
				
			} while(this.packet != null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.socket != null)
				try { this.socket.close(); } catch (Exception e) {}
		}
	}
}
