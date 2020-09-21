package org.tain.working.apis;

import org.tain.object.ApisObject;
import org.tain.object.customer.req._ReqCustomerData;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.StringTools;
import org.tain.utils.SubString;
import org.tain.utils.TransferStrAndJson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class _CustomerAnalyze {

	private String rootPath;
	private ApisObject apisObject;
	
	public _CustomerAnalyze(String rootPath, ApisObject apisObject) {
		this.rootPath = rootPath;
		this.apisObject = apisObject;
	}
	
	public void analyze() {
		if (Flag.flag) analyzeReq01();
	}
	
	private void analyzeReq01() {
		String reqStream = null;
		
		if (Flag.flag) {
			try {
				String jsonReqData = StringTools.stringFromFile(this.rootPath + this.apisObject.getReqJson());
				
				_ReqCustomerData reqData = new ObjectMapper().readValue(jsonReqData, _ReqCustomerData.class);
				reqStream = TransferStrAndJson.getStream(reqData);
				System.out.printf("stream of Req >>>>> [%s]%n", reqStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.flag) {
			try {
				TransferStrAndJson.subString = new SubString(reqStream);
				_ReqCustomerData reqData = new _ReqCustomerData();
				reqData = (_ReqCustomerData) TransferStrAndJson.getObject(reqData);
				System.out.printf("json of Req >>>>> [%s]%n", JsonPrint.getInstance().toPrettyJson(reqData));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
