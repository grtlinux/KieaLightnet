package org.tain;

import java.time.LocalDateTime;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.properties.LnsEnvBaseProperties;
import org.tain.properties.LnsEnvJsonProperties;
import org.tain.properties.LnsEnvVirtualProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaLightnet00Virtual05Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet00Virtual05Application.class, args);
	}

	@PostConstruct
	public void start() {
		if (!Flag.flag) {
			TimeZone.setDefault(TimeZone.getTimeZone("UTC+9"));
			//TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
			//TimeZone.setDefault(TimeZone.getTimeZone("GMT+09:00"));
		}
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

	@Autowired
	private LnsEnvBaseProperties lnsEnvBaseProperties;
	
	@Autowired
	private LnsEnvJsonProperties lnsEnvJsonProperties;
	
	@Autowired
	private LnsEnvVirtualProperties lnsEnvVirtualProperties;

	private void job01() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			JsonPrint.getInstance().printPrettyJson(this.lnsEnvBaseProperties);
			JsonPrint.getInstance().printPrettyJson(this.lnsEnvVirtualProperties);
			JsonPrint.getInstance().printPrettyJson(this.lnsEnvJsonProperties);
		}
	}

	private void job02() {
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