package org.tain.utils;

import java.util.LinkedList;

import org.tain.object.Packet;

public class PacketQueue {

	private final LinkedList<Packet> queue = new LinkedList<>();
	
	public synchronized Packet get() {
		while (this.queue.size() <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return this.queue.removeFirst();
	}
	
	public synchronized void put(Packet packet) {
		this.queue.addLast(packet);
		notifyAll();
	}
}
