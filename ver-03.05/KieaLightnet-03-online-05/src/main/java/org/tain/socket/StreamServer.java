package org.tain.socket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.properties.LnsEnvJobProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StreamServer {

	@Autowired
	private LnsEnvJobProperties lnsEnvJobProperties;
	
	@Bean
	public void jobStreamServer() throws Exception {
		log.info("KANG-20200623 >>>>> {} listen-port: {}", CurrentInfo.get(), this.lnsEnvJobProperties.getListenPort());
		
		if (Flag.flag) {
			new Thread("StreamServer") {
				// StreamServer Thread
				private ServerSocket serverSocket = null;
				private int listenPort = -1;
				
				public Thread setListenPort(int listenPort) {
					this.listenPort = listenPort;
					return this;
				}
				
				@Override
				public void run() {
					try {
						this.serverSocket = new ServerSocket();
						this.serverSocket.bind(new InetSocketAddress("localhost", this.listenPort));
						
						while (true) {
							log.info(">>>>> waiting for connection with port={}.....", this.listenPort);
							Socket socket = this.serverSocket.accept();   // block waiting for connect
							log.info(">>>>>  Server Connection is OK!!!");
							
							new StreamServerWorkerThread(socket).start();
							
							Sleep.run(1000);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (this.serverSocket != null)
							try { this.serverSocket.close(); } catch (Exception e) {}
					}
				}
			}.setListenPort(this.lnsEnvJobProperties.getListenPort()).start();
		}
	}
}
