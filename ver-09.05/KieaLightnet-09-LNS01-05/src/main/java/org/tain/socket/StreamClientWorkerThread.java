package org.tain.socket;

import java.net.Socket;

import org.tain.object.lns.LnsMessage;
import org.tain.object.lns.LnsStream;
import org.tain.queue.MessageQueue;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

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
				LnsMessage message = this.messageQueue.get();
				// String request = message.getData();
				LnsStream reqStream = new LnsStream(message.getData());
				log.info("KANG-20200628 >>>>> reqStream.json: {}", JsonPrint.getInstance().toPrettyJson(reqStream));
				
				reqStream = this.streamPacket.sendStream(reqStream);
				
				LnsStream resStream = this.streamPacket.recvStream();
				
				log.info("KANG-20200628 >>>>> resStream.json: {}", JsonPrint.getInstance().toPrettyJson(resStream));
				message.setDataToQueue(resStream.getData());
			}
		}
	}
	
	public void setMessage(LnsMessage message) {
		this.messageQueue.set(message);
	}
}
