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
import org.tain.working.board.BoardWorking;
import org.tain.working.mid.MidWorking;
import org.tain.working.properties.PropertiesWorking;
import org.tain.working.word.WordWorking;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaLightnet12Sit04Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet12Sit04Application.class, args);
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
		if (!Flag.flag) job02();  // Board
		if (!Flag.flag) job03();  // Word
		if (!Flag.flag) job04();  // Mid
		if (Flag.flag) job05();  // apis
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
	
	@Autowired
	private BoardWorking boardWorking;
	
	private void job02() {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.boardWorking.loading();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private WordWorking wordWorking;
	
	private void job03() {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.wordWorking.loading();
		if (Flag.flag) this.wordWorking.saveJsonFile();
		if (!Flag.flag) this.wordWorking.loadingFromJsonFile();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private MidWorking midWorking;
	
	private void job04() {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.midWorking.loading();
		if (!Flag.flag) this.midWorking.getAll();
		if (Flag.flag) this.midWorking.saveJsonFile();
		if (Flag.flag) this.midWorking.getById();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ApisWorking apisWorking;
	
	private void job05() throws Exception {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.apisWorking.loading();
		if (!Flag.flag) this.apisWorking.transaction();
	}

}
