package org.tain.working.infoTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.mapper.LnsJsonNode;
import org.tain.mapper.LnsMstInfo;
import org.tain.mapper.LnsStreamToJson;
import org.tain.task.MapperReaderJob;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.enums.OptionType;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InfoTest01Working {

	@Autowired
	private MapperReaderJob mapperReaderJob;
	
	public void test01() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = new LnsJsonNode();
				lnsJsonNode.put("reqResType", "0700000");
			}
			if (Flag.flag) {
				
				LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(lnsJsonNode.getText("reqResType"));
				log.info("MAPPER.get = {}", JsonPrint.getInstance().toPrettyJson(lnsMstInfo));
			}
		}
	}
	
	/*
	 *  LnsElementInfo.option TEST
	 */
	public void test02() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("KANG-20210228 >>>>> STEP-01");
			int num = 1234;
			log.info("KANG-20210228 >>>>>>>>>> num = {}", num);
			String strNum = Integer.toBinaryString(num);
			log.info("KANG-20210228 >>>>>>>>>> strNum = {}", strNum);
			int num2 = Integer.parseInt(strNum, 2);
			log.info("KANG-20210228 >>>>>>>>>> num2 = {}", num2);
		}
		
		if (Flag.flag) {
			int num = 0;
			int val = 0;
			log.info("KANG-20210228 >>>>> STEP-02");
			log.info("KANG-20210228 >>>>>>>>>> RM_NULL = {}", OptionType.RM_NULL.getValue());
			log.info("KANG-20210228 >>>>>>>>>> RM_SPACE = {}", OptionType.RM_SPACE.getValue());
			log.info("KANG-20210228 >>>>>>>>>> OPTION = {}", OptionType.OPTION16.getValue());
			
			num = 131;
			val = num & OptionType.RM_NULL.getValue();
			log.info("KANG-20210228 >>>>>>>>>> num = {}", Integer.toBinaryString(num));
			log.info("KANG-20210228 >>>>>>>>>> num = {}, val = {}", num, val);
			val = num & OptionType.RM_SPACE.getValue();
			log.info("KANG-20210228 >>>>>>>>>> num = {}, val = {}", num, val);
			val = num & OptionType.OPTION16.getValue();
			log.info("KANG-20210228 >>>>>>>>>> num = {}, val = {}", num, val);
		}
		
		if (Flag.flag) {
			int val = 0;
			log.info("KANG-20210228 >>>>> STEP-03");
			log.info("KANG-20210228 >>>>>>>>>> RM_NULL = {} {}", OptionType.RM_NULL, OptionType.RM_NULL.getValue());
			log.info("KANG-20210228 >>>>>>>>>> RM_SPACE = {} {}", OptionType.RM_SPACE.getName(), OptionType.RM_SPACE.getValue());
			log.info("KANG-20210228 >>>>>>>>>> OPTION = {} {}", OptionType.OPTION16.getName(), OptionType.OPTION16.getValue());
			
			val = OptionType.RM_NULL.getValue() | OptionType.RM_SPACE.getValue() | OptionType.OPTION16.getValue();
			log.info("KANG-20210228 >>>>>>>>>> val = {}", val);
			log.info("KANG-20210228 >>>>>>>>>> val = {}", Integer.toBinaryString(val));
		}
		
		if (Flag.flag) {
			log.info("KANG-20210228 >>>>> STEP-04");
			log.info("KANG-20210228 >>>>>>>>>> val = {}", OptionType.valueOf("RM_NULL"));
			log.info("KANG-20210228 >>>>>>>>>> val = {}", OptionType.valueOf("RM_SPACE"));
			log.info("KANG-20210228 >>>>>>>>>> val = {}", OptionType.valueOf("OPTION16"));
			try {
				log.info("KANG-20210228 >>>>>>>>>> val = {}", OptionType.valueOf("_OPTION16"));
			} catch (IllegalArgumentException e) {
				log.info("KANG-20210228 >>>>>>>>>> val = {}", 0);
			}
		}
		
		if (Flag.flag) {
			String stream = "0875020030099999920201016125525125525                                                               "
					+ "ter-001                                           receiverFirstName   receiverLastName    Bangkok             "
					//+ "MG-0012345               falsefalsereceiverEmail@test.com                            345364566      33   MG-0012345                    "
					+ "MG-0012345               falsefalse                                                  345364566      33   MG-0012345                    "
					+ "cash                                    IQLZSO              senderLastNamex     Bangkok             10400               "
					+ "Temp address        GOV                 PURCHASE_GOODS      THA  falsefalsefalsefalse45645645666         66 senderEmail@test.com"
					+ "MYSP83245384           1992-03-23          BUSINESS_PARTNER    senderMiddleName    THAADMIN               IDN"
					+ "43366.3500     IDRmgiThis is MGI test remark                           KOR               USD4324682635157307              "
					+ "true FLATFEE                                           ";

			LnsMstInfo lnsMstInfo = this.mapperReaderJob.get("0200300");
			JsonNode node = new LnsStreamToJson(lnsMstInfo, stream).get();
			
			log.info("KANG-20210228 >>>>> STEP-05");
			log.info("KANG-20210228 >>>>>>>>>> json = {}", node.toPrettyString());
		}
	}
}
