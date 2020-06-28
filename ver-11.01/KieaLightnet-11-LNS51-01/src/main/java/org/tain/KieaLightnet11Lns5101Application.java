package org.tain;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaLightnet11Lns5101Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200628 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet11Lns5101Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200628 >>>>> {}", CurrentInfo.get());
		if (Flag.flag) job01();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private void job01() {
		log.info("KANG-20200628 >>>>> {}", CurrentInfo.get());
		
	}
}
