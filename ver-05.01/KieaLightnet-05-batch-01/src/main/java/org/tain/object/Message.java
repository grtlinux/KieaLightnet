package org.tain.object;

import org.tain.queue.MessageQueue;

public class Message {

	private String data;
	private MessageQueue returnQueue = new MessageQueue();
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getData() {
		return this.data;
	}
	
	public void setDataToQueue(String data) {
		this.setData(data);
		this.returnQueue.set(this);
	}
	
	public String getDataFromQueue() {
		Message message = this.returnQueue.get();  // blocking
		return message.getData();
	}
	
	public MessageQueue getQueue() {
		return this.returnQueue;
	}
	
	public String toString() {
		return "Message=[data:" + this.data + "]";
	}
}
