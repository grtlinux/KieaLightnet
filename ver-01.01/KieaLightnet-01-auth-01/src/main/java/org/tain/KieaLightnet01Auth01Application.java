package org.tain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KieaLightnet01Auth01Application {

	public static void main(String[] args) {
		SpringApplication.run(KieaLightnet01Auth01Application.class, args);
	}
}
