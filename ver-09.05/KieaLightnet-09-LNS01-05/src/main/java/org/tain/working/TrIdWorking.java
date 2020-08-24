package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.annotation.SubString;
import org.tain.object.lns.LnsMessage;
import org.tain.object.trid.req._TridReqData;
import org.tain.object.trid.res._TridResData;
import org.tain.socket.StreamClient;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TrIdWorking {

	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////

	public void testStreamJson() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			_TridReqData reqData = new _TridReqData();
			
			System.out.println("1 >>>>> " + JsonPrint.getInstance().toPrettyJson(reqData));
			
			reqData.setStatus("STATUS");
			reqData.setMessage("MESSAGE: Hello world");
			System.out.println("2 >>>>> " + JsonPrint.getInstance().toPrettyJson(reqData));
			
			System.out.println("3 >>>>> [" + reqData.getStream() + "]");
			
			_TridResData resData = new _TridResData();
			resData.getObject(new SubString(reqData.getStream()));
			resData.getTrid().setTridCode("1234567890");
			System.out.println("4 >>>>> " + JsonPrint.getInstance().toPrettyJson(reqData));
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////

	public void get() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		
		_TridReqData reqData = null;
		String stream = null;
		
		for (int i=0; i < 1; i++) {
			reqData = new _TridReqData();
			stream = reqData.getStream();
			// REQ
			String req = String.format("%04d%s%s", 7 + stream.length(), "0200100", stream);
			System.out.println(">>>>> req: [" + req + "]");
			
			// RES
			String res = this.callStreamClient(req);
			System.out.println(">>>>> res: [" + res + "]");
			
			// wait
			Sleep.run(1000);
		}
		
		Sleep.run(2000);
		if (Flag.flag) System.exit(0);  // TODO: remove later
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
