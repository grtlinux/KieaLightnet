package org.tain.socket;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StreamServer {

	@Bean
	public void jobStreamServer() throws Exception {
		log.info("Stream Server KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
	}
}
