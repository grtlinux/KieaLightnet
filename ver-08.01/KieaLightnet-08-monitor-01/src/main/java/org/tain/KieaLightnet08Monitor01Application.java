package org.tain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
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
public class KieaLightnet08Monitor01Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200629 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet08Monitor01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		if (Flag.flag) job01();
	}

	@Autowired
	private BoardRepository boardRepository;
	
	private void job01() {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		
		Random random = new Random(new Date().getTime());
		
		IntStream.rangeClosed(1, 200).forEach(index -> {
			String userId = null;
			int switchNumber = random.nextInt(10);
			switch(switchNumber) {
			case 0: userId = "info"; break;
			case 1: userId = "refresh"; break;
			case 2: userId = "validate"; break;
			case 3: userId = "commit"; break;
			case 4: userId = "list"; break;
			case 5: userId = "detail"; break;
			case 6: userId = "callback"; break;
			case 7: userId = "lns01"; break;
			case 8: userId = "lns02"; break;
			case 9: userId = "lns51"; break;
			default: return;
			}
			this.boardRepository.save(Board.builder()
					.title("제목-" + index)
					.subTitle("부제목-" + index)
					.content("안녕하세요. 내용입니다. 감사합니다.")
					.userId(userId)
					.build());
		});
	}
}
