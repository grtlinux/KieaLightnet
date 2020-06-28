package org.tain.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.tain.object.Packet;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

public class StreamPacket {

	private Socket socket = null;
	
	private InetSocketAddress inetSocketAddress = null;
	
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	
	public StreamPacket(Socket socket) throws Exception {
		this.socket = socket;
		
		this.inetSocketAddress = (InetSocketAddress) this.socket.getRemoteSocketAddress();
		
		this.inputStream = this.socket.getInputStream();
		this.outputStream = this.socket.getOutputStream();
	}
	
	public void close() {
		if (this.outputStream != null) {
			try { this.outputStream.close(); } catch (IOException e) {}
		}
		
		if (this.inputStream != null) {
			try { this.inputStream.close(); } catch (IOException e) {}
		}
		
		if (this.socket != null) {
			try { this.socket.close(); } catch (IOException e) {}
		}
	}
	
	public String getRemoteInfo() {
		return this.inetSocketAddress.toString();
	}
	
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////

	private static int DEFAULT_LENGTH_SIZE = 4;
	private Packet packet = null;
	
	private int getLength() throws Exception {
		byte[] bytLength = recv(DEFAULT_LENGTH_SIZE);
		String strLength = new String(bytLength, "UTF-8");
		int retLength = Integer.parseInt(strLength);
		return retLength;
	}
	
	private String getData(int length) throws Exception {
		byte[] bytBuffer = recv(length - DEFAULT_LENGTH_SIZE);
		String buffer = new String(bytBuffer, "UTF-8");
		return buffer;
	}
	
	public Packet recvPacket() {
		try {
			this.packet = new Packet();
			this.packet.setLength(this.getLength());
			this.packet.setData(this.getData(this.packet.getLength()));
		} catch (Exception e) {
			if (Flag.flag) e.printStackTrace();
			this.packet = null;
		} finally {
			this.close();
		}
		return this.packet;
	}
	
	public Packet sendPacket(String data) {
		try {
			this.packet = new Packet();
			this.packet.setData(data);
			this.packet.setLength(data.length() + DEFAULT_LENGTH_SIZE);
			
			byte[] bytPacket = String.format("%0" + DEFAULT_LENGTH_SIZE + "d%s", this.packet.getLength(), this.packet.getData()).getBytes();
			int nsend = this.send(bytPacket);
			if (nsend != this.packet.getLength()) {
				throw new IOException("ERROR: wrong send....");
			}
		} catch (Exception e) {
			if (Flag.flag) e.printStackTrace();
			this.packet = null;
		} finally {
			this.close();
		}
		return this.packet;
	}
	
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////
	
	public byte[] recv(int length) throws Exception {
		byte[] buffer = new byte[length];
		
		int nread = 0;
		int offset = 0;
		int sleepCount = 0;
		int maxSleepCount = 20;
		
		while (length > 0) {
			nread = this.inputStream.read(buffer, offset, length);  // blocking for read
			if (nread < 0) {
				// no data, no available
				throw new IOException("ERROR: return valuue of read is nagative....");
			} else if (nread == 0) {
				Sleep.run(1000);
				sleepCount ++;
				if (sleepCount > maxSleepCount) {
					throw new IOException("ERROR: looping more than maxSleepCount..");
				}
				continue;
			}
			
			sleepCount = 0;
			
			offset += nread;
			length -= nread;
		}
		
		return buffer;
	}
	
	public int send(byte[] buffer) throws Exception {
		int length = buffer.length;
		int offset = 0;
		
		this.outputStream.write(buffer, offset, length);
		
		return length;
	}
}
