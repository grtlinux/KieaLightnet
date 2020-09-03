package org.tain;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.LnsTimeZone;
import org.tain.working.properties.PropertiesWorking;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaLightnet01Auth10Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet01Auth10Application.class, args);
	}

	@PostConstruct
	public void start() {
		if (!Flag.flag) LnsTimeZone.setTimeZone("UTC+9");
	}

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
		
		if (Flag.flag) this.propertiesWorking.print();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void job02() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void job03() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void job04() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
	}

	private void job05() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
	}
}
