package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.object.lns.LnsMessage;
import org.tain.socket.StreamClient;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TrIdWorking {

	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////

	public void get() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		for (int i=0; i < 1; i++) {
			// REQ
			String req = "0057" + "0200100" + "12345678901234567890ABCDEF12345678901234567890KANG";
			System.out.println(">>>>> req: " + req);
			
			// RES
			String res = this.callStreamClient(req);
			System.out.println(">>>>> res: " + res);
			
			// wait
			Sleep.run(1000);
		}
		
		Sleep.run(2000);
		if (!Flag.flag) System.exit(0);  // TODO: remove later
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////

	@Autowired
	private StreamClient streamClient;
	
	private String callStreamClient(String req) throws Exception {
		LnsMessage message = new LnsMessage();
		message.setData(req);
		
		// for send data to socket
		this.streamClient.getThread().setMessage(message);
		
		// for recv data from socket
		String res = message.getDataFromQueue();
		
		return res;
	}
}
