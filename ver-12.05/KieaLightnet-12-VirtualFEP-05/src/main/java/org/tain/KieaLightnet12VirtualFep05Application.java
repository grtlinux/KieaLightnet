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
import org.tain.working.apis.ApisWorking;
import org.tain.working.properties.PropertiesWorking;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaLightnet12VirtualFep05Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet12VirtualFep05Application.class, args);
	}

	@PostConstruct
	public void start() {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
		// TO application.yml: spring.jpa.properties.hibernate.jdbc.time_zone: UTC+9
		if (Flag.flag) LnsTimeZone.setTimeZone("UTC+9");
	}
	
	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) job01();  // properties
		if (Flag.flag) job02();  // 
		if (Flag.flag) job03();  // 
		if (Flag.flag) job04();  // 
		if (Flag.flag) job05();  // apis
		
		//if (Flag.flag) System.exit(0);
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private PropertiesWorking propertiesWorking;
	
	private void job01() {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.propertiesWorking.print();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void job02() {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void job03() {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void job04() {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ApisWorking apisWorking;
	
	private void job05() throws Exception {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.apisWorking.loading();
		if (!Flag.flag) this.apisWorking.initialize();
		if (!Flag.flag) this.apisWorking.transaction();
	}
}
