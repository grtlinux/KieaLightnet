package org.tain;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.properties.LnsEnvBaseProperties;
import org.tain.properties.LnsEnvJsonProperties;
import org.tain.properties.LnsEnvLns01Properties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaLightnet09Lns0103Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet09Lns0103Application.class, args);
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
	private LnsEnvLns01Properties lnsEnvLns01Properties;

	private void job01() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info(">>>>> base.jsonString: " + this.lnsEnvBaseProperties.toPrettyJson());
			log.info(">>>>> json.jsonString: " + this.lnsEnvJsonProperties.toPrettyJson());
			log.info(">>>>> lns01.jsonString: " + this.lnsEnvLns01Properties.toPrettyJson());
		}
		
		if (Flag.flag) {
			String key = null;
			key = "virtual"; log.info("KANG-20200721 >>>>> {} = {}", key, this.lnsEnvJsonProperties.getFile().get(key));
			key = "auth"   ; log.info("KANG-20200721 >>>>> {} = {}", key, this.lnsEnvJsonProperties.getFile().get(key));
			key = "link"   ; log.info("KANG-20200721 >>>>> {} = {}", key, this.lnsEnvJsonProperties.getFile().get(key));
			key = "lns01"  ; log.info("KANG-20200721 >>>>> {} = {}", key, this.lnsEnvJsonProperties.getFile().get(key));
			key = "adapter"; log.info("KANG-20200721 >>>>> {} = {}", key, this.lnsEnvJsonProperties.getFile().get(key));
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
