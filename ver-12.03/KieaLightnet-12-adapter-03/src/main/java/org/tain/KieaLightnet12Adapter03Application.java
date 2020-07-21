package org.tain;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.properties.LnsEnvBaseProperties;
import org.tain.properties.LnsEnvJsonProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaLightnet12Adapter03Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet12Adapter03Application.class, args);
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
	
	private void job01() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get(), this.lnsEnvBaseProperties);
			log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get(), this.lnsEnvJsonProperties);
		}
		
		if (Flag.flag) {
			try {
				String jsonString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this.lnsEnvBaseProperties);
				System.out.println(">>>>> base.jsonString: " + jsonString);
			} catch (Exception e) {
			}
		}
		
		if (Flag.flag) {
			try {
				String jsonString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this.lnsEnvJsonProperties);
				System.out.println(">>>>> json.jsonString: " + jsonString);
			} catch (Exception e) {
			}
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
