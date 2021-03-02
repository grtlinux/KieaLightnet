package org.tain.working.infoTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.mapper.LnsJsonNode;
import org.tain.mapper.LnsMstInfo;
import org.tain.task.MapperReaderJob;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.enums.OptionType;

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
			log.info("KANG-20210228 >>>>>>>>>> RM_NULL = {}", OptionType.RM_NULL.getValue());
			log.info("KANG-20210228 >>>>>>>>>> RM_SPACE = {}", OptionType.RM_SPACE.getValue());
			log.info("KANG-20210228 >>>>>>>>>> OPTION = {}", OptionType.OPTION16.getValue());
			
			val = OptionType.RM_NULL.getValue() | OptionType.RM_SPACE.getValue() | OptionType.OPTION16.getValue();
			log.info("KANG-20210228 >>>>>>>>>> val = {}", val);
			log.info("KANG-20210228 >>>>>>>>>> val = {}", Integer.toBinaryString(val));
		}
		
	}
}
