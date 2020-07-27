package org.tain.socket;

import java.net.Socket;

import org.tain.object.LnsPacket;
import org.tain.object.Message;
import org.tain.object.Packet;
import org.tain.queue.MessageQueue;
import org.tain.utils.Flag;

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
		if (Flag.flag) {
			while (true) {
				Message message = this.messageQueue.get();
				// String request = message.getData();
				LnsPacket reqPacket = new LnsPacket(message.getData());
				log.info("KANG-20200628 >>>>> reqPacket.json: {}", reqPacket.toPrettyJson());
				
				reqPacket = this.streamPacket.sendPacket(reqPacket);
				
				LnsPacket resPacket = this.streamPacket.recvPacket();
				
				log.info("KANG-20200628 >>>>> resPacket.json: {}", resPacket.toPrettyJson());
				message.setDataToQueue(resPacket.getData());
			}
		}
	}
	
	public void setMessage(Message message) {
		this.messageQueue.set(message);
	}
}
