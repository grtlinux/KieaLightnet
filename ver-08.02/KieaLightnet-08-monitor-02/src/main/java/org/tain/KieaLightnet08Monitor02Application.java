package org.tain;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.domain.Board;
import org.tain.repository.BoardRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaLightnet08Monitor02Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet08Monitor02Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		if (Flag.flag) job01();
		if (Flag.flag) job02();
		if (Flag.flag) job03();
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private BoardRepository boardRepository;
	
	private void job01() {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		
		IntStream.rangeClosed(1, 200).forEach(index -> {
			this.boardRepository.save(Board.builder()
					.title("제목-" + index)
					.subTitle("부제목-" + index)
					.content("안녕하세요.\n내용입니다.")
					.userId("kiea")
					.build());
		});
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	private void job02() {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	private void job03() {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		
	}

}
