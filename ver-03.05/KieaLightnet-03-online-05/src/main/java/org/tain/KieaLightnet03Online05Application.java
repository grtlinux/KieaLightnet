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
import org.tain.object.LnsPacket;
import org.tain.properties.LnsEnvBaseProperties;
import org.tain.properties.LnsEnvJsonProperties;
import org.tain.properties.LnsEnvOnlineProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

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

	@Autowired
	private LnsEnvBaseProperties lnsEnvBaseProperties;
	
	@Autowired
	private LnsEnvJsonProperties lnsEnvJsonProperties;
	
	@Autowired
	private LnsEnvOnlineProperties lnsEnvOnlineProperties;

	private void job01() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			JsonPrint.getInstance().printPrettyJson(this.lnsEnvBaseProperties);
			JsonPrint.getInstance().printPrettyJson(this.lnsEnvOnlineProperties);
			JsonPrint.getInstance().printPrettyJson(this.lnsEnvJsonProperties);
		}
	}

	private void job02() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		String stream = "00450101RESTR123456789012345678Hello world!!!";
		log.info(">>>>> stream.json: {}", new LnsPacket(stream).toPrettyJson());
	}

	private void job03() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		System.out.println(">>>>> " + new Date());
		System.out.println(">>>>> " + new Timestamp(System.currentTimeMillis()));
	}

	private void job04() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
	}

	private void job05() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
	}
}
