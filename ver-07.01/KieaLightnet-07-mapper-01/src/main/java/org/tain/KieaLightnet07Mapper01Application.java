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
public class KieaLightnet07Mapper01Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet07Mapper01Application.class, args);
	}

	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		if (Flag.flag) job01();
	}

	private void job01() {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
	}

}

/*
package com.example.demo.controller;

import com.example.demo.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseEntityExController {

    @GetMapping("/success")
    public ResponseEntity successMessage(){
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/serverError", produces = "application/json")
    public ResponseEntity serverErrorMessage(){
        Message message = Message.builder()
                .message1("첫번째 메시지입니다")
                .message2("메시지 두개 전달해보고 싶어서")
                .build();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/onlystatus")
    public ResponseEntity onlyStatus(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/header")
    public ResponseEntity header(){
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("AUTHCODE","xxxxxxx");
        header.add("TOKEN", "xxxxxx");
        return new ResponseEntity(header, HttpStatus.OK);
    }
}

package com.example.demo.message;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {
    private String message1;
    private String message2;

    @Builder
    public Message(String message1, String message2) {
        this.message1 = message1;
        this.message2 = message2;
    }
}

*/
