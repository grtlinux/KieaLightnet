package org.tain.socket;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StreamClient {

	private static final String host = "127.0.0.1";
	private static final int port = 19091;
	private StreamClientWorkerThread thread = null;
	
	@Bean
	public void jobStreamClient() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		if (Flag.flag) {
			// socket connection
			Socket socket = null;
			try {
				socket = new Socket();
				socket.connect(new InetSocketAddress(host, port));
				System.out.println(">>>>>  Client Connection is OK!!!");
				
				this.thread = new StreamClientWorkerThread(socket);
				this.thread.start();
				
			} catch (Exception e) {
				System.out.println(">>>>> EXCEPTION: " + e.getMessage());
				System.exit(-1);
			}
		}
		
		if (!Flag.flag) {
			/*
			// socket connection
			while (true) {
				Socket socket = null;
				try {
					socket = new Socket();
					socket.connect(new InetSocketAddress(host, port));
					System.out.println(">>>>>  Connection is OK!!!");
					
					this.thread = new StreamClientWorkerThread(socket);
					this.thread.start();
					
					if (Flag.flag) {
						while (true) {
							Sleep.run(5 * 1000);
							if (this.thread.isAlive() == false)
								break;
							System.out.println(">>>>>  thread is alive...");
						}
						System.out.println(">>>>>  thread was killed...");
					}
				} catch (Exception e) {
					System.out.println(">>>>> EXCEPTION: " + e.getMessage());
				}
				if (socket != null) try { socket.close(); } catch (Exception e) {}
				Sleep.run(5 * 1000);
			}
			*/
		}
	}
	
	public StreamClientWorkerThread getThread() {
		return this.thread;
	}
}
