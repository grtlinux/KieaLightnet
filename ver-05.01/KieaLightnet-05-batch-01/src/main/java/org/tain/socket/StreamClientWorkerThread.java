package org.tain.socket;

import java.net.Socket;
import java.util.stream.IntStream;

import org.tain.object.Message;
import org.tain.object.Packet;
import org.tain.queue.MessageQueue;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamClientWorkerThread extends Thread {

	private Socket socket = null;
	private StreamPacket streamPacket = null;
	private MessageQueue messageQueue = null;
	
	public StreamClientWorkerThread(Socket socket) throws Exception {
		this.socket = socket;
		this.streamPacket = new StreamPacket(this.socket);
		this.messageQueue = new MessageQueue();
	}
	
	@Override
	public void run() {
		if (!Flag.flag) {
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
		
		if (Flag.flag) {
			while (true) {
				Message message = this.messageQueue.get();
				String request = message.getData();
				log.info("KANG-20200628 >>>>> request: [{}]", request);
				
				Packet packet = this.streamPacket.sendPacket(request);
				if (Flag.flag) System.out.println("CLIENT >>>>> " + packet);
				
				// TODO: KANG-20200628: recvPacket
				//this.packet = this.streamPacket.recvPacket();
				
				String response = packet.getData();
				log.info("KANG-20200628 >>>>> response: [{}]", response);
				message.setDataToQueue(response);
			}
		}
	}
	
	public void setMessage(Message message) {
		this.messageQueue.set(message);
	}
}
