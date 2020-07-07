package org.tain.queue;

import java.util.LinkedList;

import org.tain.object.Message;

public class MessageQueue {

	private final LinkedList<Message> queue = new LinkedList<>();
	
	public synchronized void set(Message message) {
		this.queue.addLast(message);
		this.notifyAll();
	}
	
	public synchronized Message get() {
		while (this.queue.size() <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		return this.queue.removeFirst();
	}
}
