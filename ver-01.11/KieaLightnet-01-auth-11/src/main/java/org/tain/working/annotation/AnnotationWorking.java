package org.tain.working.annotation;

import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AnnotationWorking {

	public void test00_dummy() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
	}
	
	/*
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
		
		if (Flag.flag) {
			double myValue = 0.0000021d;
			
			// option 1. print bare double
			System.out.println("1. " + myValue);
			
			// option 2. use decimalFormat
			DecimalFormat df = new DecimalFormat("#");
			df.setMaximumFractionDigits(8);
			System.out.println("2. " + df.format(myValue));
			
			// option 3. use printf
			System.out.printf("3. %.9f%n", myValue);
			
			// option 4. convert toBigDecimal and ask for to plainString()
			System.out.println("4. " + new BigDecimal(myValue).toPlainString());
			
			// option 5. String.format
			System.out.println("5. " + String.format("%.12f", myValue));
		}
		
		if (Flag.flag) {
			double myValue = 123000000.456000d;
			
			// option 1. print bare double
			System.out.println("1. " + myValue);
			
			// option 2. use decimalFormat
			DecimalFormat df = new DecimalFormat("#");
			df.setMaximumFractionDigits(8);
			System.out.println("2. " + df.format(myValue));
			
			// option 3. use printf
			System.out.printf("3. %.9f%n", myValue);
			
			// option 4. convert toBigDecimal and ask for to plainString()
			System.out.println("4. " + new BigDecimal(myValue).toPlainString());
			
			// option 5. String.format
			System.out.println("5. " + String.format("%.12f", myValue));
		}
		
		if (Flag.flag) {
			double myValue = 0.00000021d;
			myValue = 123000000.456000d;
			//DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
			DecimalFormat df = new DecimalFormat("0");
			df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
			System.out.println(">>>>> " + df.format(myValue)); // Output: 0.00000021
		}
	}
	
	public void test02_testObjectAndJson() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// req
			_ReqTestName name = new _ReqTestName();
			name.setFirstName("Seok");
			name.setMiddleName("Kiea");
			name.setLastName("Kang");
			
			_ReqTestData data = new _ReqTestData();
			data.setTitle("TITLE");
			data.setMessage("MESSAGE");
			data.setName(name);
			
			JsonPrint.getInstance(JsonPrintType.DEFAULT).printPrettyJson("REQ_DEFAULT", data);
			JsonPrint.getInstance(JsonPrintType.STEP01).printPrettyJson("REQ_STEP01", data);
			JsonPrint.getInstance(JsonPrintType.NORMAL).printPrettyJson("REQ_NORMAL", data);
		}
		
		if (Flag.flag) {
			// Xml <-> Json
			_ReqTestName name = new _ReqTestName();
			name.setFirstName("Seok");
			name.setMiddleName("Kiea");
			name.setLastName("Kang");
			
			_ReqTestData data = new _ReqTestData();
			data.setTitle("TITLE");
			data.setMessage("MESSAGE");
			data.setName(name);
			
			//String strJson = JsonPrint.getInstance(JsonPrintType.DEFAULT).toPrettyJson(data);
			
			XmlMapper xmlMapper = new XmlMapper();
			String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
			System.out.println(">>>>> " + xml);
			
			_ReqTestData reqData = xmlMapper.readValue(xml, _ReqTestData.class);
			System.out.println(">>>>> " + reqData);
		}
	}
	*/
}
