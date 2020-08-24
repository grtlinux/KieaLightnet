package org.tain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.object.lns.LnsStream;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.working.PropertiesWorking;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaLightnet03Online05Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet03Online05Application.class, args);
	}
	
	/*
	@PostConstruct
	void started() {
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		System.out.println(">>>>> " + new Date());
		System.out.println(">>>>> " + new Timestamp(System.currentTimeMillis()));
	}
	*/
	
	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) job01();
		if (Flag.flag) job02();
		if (Flag.flag) job03();
		if (Flag.flag) job04();
		if (Flag.flag) job05();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private PropertiesWorking propertiesWorking;
	
	private void job01() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.propertiesWorking.printProperties();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void job02() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		String reqStream = "0047" + "0200200" + "101RESTR123456789012345678Hello world!!!";
		LnsStream reqLnsStream = new LnsStream(reqStream);
		log.info(">>>>> test reqLnsStream.json: {}", JsonPrint.getInstance().toPrettyJson(reqLnsStream));
		
		LnsStream resLnsStream = (LnsStream) reqLnsStream.clone();
		resLnsStream.setTrTypeCode("0210200");
		resLnsStream.combind();
		log.info(">>>>> test resLnsStream.json: {}", JsonPrint.getInstance().toPrettyJson(resLnsStream));
		
		if (!Flag.flag) System.exit(0);
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void job03() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		System.out.println(">>>>> " + new Date());
		System.out.println(">>>>> " + new Timestamp(System.currentTimeMillis()));
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	////////////b//////////////////////////////////////////////////////////////
	
	private void job04() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
	}

	private void job05() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
	}
}
