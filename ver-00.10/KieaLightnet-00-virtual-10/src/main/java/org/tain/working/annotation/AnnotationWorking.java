package org.tain.working.annotation;

import org.springframework.stereotype.Component;
import org.tain.object.dummy._Dummy;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.SubString;
import org.tain.utils.TransferStrAndJson;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AnnotationWorking {

	public void test01_dummy() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		String stream = "";
		if (Flag.flag) {
			// getStream
			_Dummy dummy = new _Dummy();
			JsonPrint.getInstance().printPrettyJson("DUMMY", dummy);
			
			stream = TransferStrAndJson.getStream(dummy);
			System.out.println(">>>>> STREAM: [" + stream + "]");
		}
		
		if (Flag.flag) {
			stream = "456.123                       KRWSUBDUMMY                                   1234567                2025    123000000.456000            1234.567ACCEPT                           1234567                2025    123000000.456000            1234.567";
			stream = "456.123                       KRWKANGSEOK                                   1234567                2025    123000000.456000            1234.567DENY                             1234567                2025    123000000.456000            1234.567";
		}
		
		if (Flag.flag) {
			// getObject
			_Dummy dummy = new _Dummy();
			JsonPrint.getInstance().printPrettyJson("BEFORE:", dummy);
			
			TransferStrAndJson.subString = new SubString(stream);
			dummy = (_Dummy) TransferStrAndJson.getObject(dummy);
			JsonPrint.getInstance().printPrettyJson("AFTER :", dummy);
		}
	}
}
