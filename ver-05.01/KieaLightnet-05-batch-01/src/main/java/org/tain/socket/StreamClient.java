package org.tain.socket;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StreamClient {

	private static final String host = "127.0.0.1";
	//private static final String host = "localhost";
	private static final int port = 9095;
	private StreamClientWorkerThread thread = null;
	
	@Bean
	public void jobStreamClient() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(host, port));
			
			this.thread = new StreamClientWorkerThread(socket);
			this.thread.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public StreamClientWorkerThread getThread() {
		return this.thread;
	}
}
