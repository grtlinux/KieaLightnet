package org.tain;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.working.PropertiesWorking;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaLightnet07Mapper05Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet07Mapper05Application.class, args);
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
		
		if (Flag.flag) job01();  // properties
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
	}
	
	private void job03() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
	}

	private void job04() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
	}

	private void job05() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
	}
}
