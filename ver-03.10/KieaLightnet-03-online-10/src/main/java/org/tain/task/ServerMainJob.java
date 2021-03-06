package org.tain.task;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.object.lns.LnsStreamPacket;
import org.tain.properties.ProjEnvJobProperties;
import org.tain.queue.LnsStreamPacketQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ServerMainJob {

	@Autowired
	private ProjEnvJobProperties projEnvJobProperties;
	
	@Autowired
	private LnsStreamPacketQueue lnsStreamPacketQueue;
	
	@Async(value = "serverMainTask")
	public void serverMainJob(String param) throws Exception {
		log.info("KANG-20200907 >>>>> START param = {}, {}", param, CurrentInfo.get());
		
		if (Flag.flag) {
			ServerSocket serverSocket = new ServerSocket();
			int port = this.projEnvJobProperties.getListenPort();
			InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", port);
			serverSocket.bind(inetSocketAddress);
			
			try {
				while (true) {
					log.info(">>>>> waiting for client connection with port={}....INFO = {}", port, inetSocketAddress);
					Socket socket = serverSocket.accept(); // block waiting for connect
					log.info(">>>>> client connection is OK!!!");
					
					if (Flag.flag) this.lnsStreamPacketQueue.set(new LnsStreamPacket(socket));
					
					Sleep.run(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (serverSocket != null) try { serverSocket.close(); } catch (Exception e) {}
			}
		}
		
		log.info("KANG-20200907 >>>>> END   param = {}, {}", param, CurrentInfo.get());
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Deprecated
	public void serverMainJob_202009070941(String param) throws Exception {
		
		if (Flag.flag) {
			// server socket listen
			int sec = 10;
			log.info("KANG-20200721 >>>>> SLEEP param = {}, sec = {}, {}", param, sec, CurrentInfo.get());
			Sleep.run(sec * 1000);
		}
	}
}
