package org.tain;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.domain.Board;
import org.tain.domain.Kuser;
import org.tain.domain.Stmt;
import org.tain.repository.BoardRepository;
import org.tain.repository.KuserRepository;
import org.tain.repository.StmtRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		if (Flag.flag) job04();
		if (Flag.flag) job05();
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
	
	@Value("${json.file.stmt.path}")
	private String jsonFileStmtPath;
	
	@Autowired
	private StmtRepository stmtRepository;
	
	private void job02() {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Stmt> list = objectMapper.readValue(new File(this.jsonFileStmtPath), new TypeReference<List<Stmt>>() {});
				list.forEach(entity -> {
					this.stmtRepository.save(entity);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Value("${json.file.kuser.path}")
	private String jsonFileKuserPath;
	
	@Autowired
	private KuserRepository kuserRepository;
	
	private void job03() {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Kuser> list = objectMapper.readValue(new File(this.jsonFileKuserPath), new TypeReference<List<Kuser>>() {});
				list.forEach(entity -> {
					this.kuserRepository.save(entity);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	private void job04() {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	private void job05() {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		
	}

}
