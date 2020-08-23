package org.tain.queue;

import java.util.LinkedList;

import org.tain.object.lns.LnsMessage;

public class MessageQueue {

	private final LinkedList<LnsMessage> queue = new LinkedList<>();
	
	public synchronized void set(LnsMessage message) {
		this.queue.addLast(message);
		this.notifyAll();
	}
	
	public synchronized LnsMessage get() {
		while (this.queue.size() <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		return this.queue.removeFirst();
	}
}
