package org.tain.socket;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
				if (Flag.flag) System.out.println("LNS02 >>>>> REQ: " + this.packet);
				
				Sleep.run(1000);
				
				Map<String,Object> map = new HashMap<>();
				map.put("message", "ACKNOWLEDGE");
				map.put("status", "success");
				
				String response = "{\"message\": \"ACKNOWLEDGE\", \"status\": \"success\"}";
				response = "0602 LNS02 callback Hello, world!!!";
				if (Flag.flag) System.out.println("LNS02 >>>>> RES: " + response);
				
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
