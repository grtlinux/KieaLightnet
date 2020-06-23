package org.tain;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaLightnet02Link01Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaLightnet02Link01Application.class, args);
	}

	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
	}
}
