package org.tain.socket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StreamServer {

	private ServerSocket serverSocket = null;
	
	private static final int LISTEN_PORT = 9092;
	
	@Bean
	public void jobStreamServer() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		try {
			this.serverSocket = new ServerSocket();
			this.serverSocket.bind(new InetSocketAddress("localhost", LISTEN_PORT));
			
			while (true) {
				log.info("KANG-20200623 >>>>> Waiting for a client connection.....");
				Socket socket = this.serverSocket.accept();
				new StreamServerWorkerThread(socket).start();
				
				Sleep.run(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}
