package org.tain;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.domain.Adapter;
import org.tain.domain.Board;
import org.tain.domain.Kuser;
import org.tain.domain.Line;
import org.tain.domain.Link;
import org.tain.domain.Lns01;
import org.tain.domain.Stmt;
import org.tain.object.LnsJson;
import org.tain.object.LnsStream;
import org.tain.properties.LnsEnvBaseProperties;
import org.tain.properties.LnsEnvJsonProperties;
import org.tain.properties.LnsEnvMonitorProperties;
import org.tain.repository.AdapterRepository;
import org.tain.repository.BoardRepository;
import org.tain.repository.KuserRepository;
import org.tain.repository.LineRepository;
import org.tain.repository.LinkRepository;
import org.tain.repository.Lns01Repository;
import org.tain.repository.StmtRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaLightnet08Monitor03Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet08Monitor03Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		if (Flag.flag) job01();
		if (!Flag.flag) job02();
		if (!Flag.flag) job03();
		if (!Flag.flag) job04();
		if (!Flag.flag) job05();
		if (!Flag.flag) job06();
		if (Flag.flag) job07();
		if (Flag.flag) job08();
		if (Flag.flag) job09();
		if (Flag.flag) job10();
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private LnsEnvBaseProperties lnsEnvBaseProperties;
	
	@Autowired
	private LnsEnvJsonProperties lnsEnvJsonProperties;
	
	@Autowired
	private LnsEnvMonitorProperties lnsEnvMonitorProperties;

	private void job01() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info(">>>>> base.jsonString: " + this.lnsEnvBaseProperties.toPrettyJson());
			log.info(">>>>> json.jsonString: " + this.lnsEnvJsonProperties.toPrettyJson());
			log.info(">>>>> monitor.jsonString: " + this.lnsEnvMonitorProperties.toPrettyJson());
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

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private BoardRepository boardRepository;
	
	private void job02() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) IntStream.rangeClosed(1, 200).forEach(index -> {
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
	
	@Autowired
	private StmtRepository stmtRepository;
	
	private void job03() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Stmt> list = objectMapper.readValue(new File(System.getenv("HOME") + this.lnsEnvMonitorProperties.getStmtPath()), new TypeReference<List<Stmt>>() {});
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

	@Autowired
	private KuserRepository kuserRepository;
	
	private void job04() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Kuser> list = objectMapper.readValue(new File(System.getenv("HOME") + this.lnsEnvMonitorProperties.getKuserPath()), new TypeReference<List<Kuser>>() {});
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

	@Autowired
	private LineRepository lineRepository;
	
	private void job05() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Line> list = objectMapper.readValue(new File(System.getenv("HOME") + this.lnsEnvMonitorProperties.getLinePath()), new TypeReference<List<Line>>() {});
				list.forEach(entity -> {
					this.lineRepository.save(entity);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	@Autowired
	private AdapterRepository adapterRepository;
	
	@Autowired
	private Lns01Repository lns01Repository;
	
	@Autowired
	private LinkRepository linkRepository;
	
	private void job06() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Adapter> list = objectMapper.readValue(new File(System.getenv("HOME") + this.lnsEnvMonitorProperties.getAdapterPath()), new TypeReference<List<Adapter>>() {});
				list.forEach(entity -> {
					this.adapterRepository.save(entity);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.flag) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Lns01> list = objectMapper.readValue(new File(System.getenv("HOME") + this.lnsEnvMonitorProperties.getLns01Path()), new TypeReference<List<Lns01>>() {});
				list.forEach(entity -> {
					this.lns01Repository.save(entity);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.flag) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Link> list = objectMapper.readValue(new File(System.getenv("HOME") + this.lnsEnvMonitorProperties.getLinkPath()), new TypeReference<List<Link>>() {});
				list.forEach(entity -> {
					this.linkRepository.save(entity);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	private void job07() {
		log.info("MONITOR >>>>> {} {}", CurrentInfo.get());
		
		LnsJson reqJson = null;
		if (Flag.flag) {
			reqJson = LnsJson.builder()
					.name("TEST")
					.title("for testing...")
					.workUrl("http://localhost:18091/v0.3/lns01/trid")
					.division("trid")
					.divisionType("REQ")
					.dataType("STREAM")
					.reqData("00530701REQ................................          ")
					.resData("")
					.code("")
					.message("")
					.build();
			log.info("MONITOR >>>>> lnsJson.REQ = {}", reqJson.toPrettyJson());
			
			LnsStream lnsStream = new LnsStream(reqJson.getReqData());
			log.info("MONITOR >>>>> lnsStream.REQ = {}", lnsStream.toPrettyJson());
		}
		
		LnsJson resJson = null;
		if (Flag.flag) {
			try {
				HttpHeaders reqHeaders = new HttpHeaders();
				reqHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson.toJson(), reqHeaders);
				
				ResponseEntity<String> response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						reqJson.getWorkUrl()
						, HttpMethod.POST
						, reqHttpEntity
						, String.class);
				
				log.info("MONITOR >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info("MONITOR >>>>> response.getStatusCode()      = {}", response.getStatusCode());
				
				resJson = new ObjectMapper().readValue(response.getBody(), LnsJson.class);
			} catch (Exception e) {
				log.error("MONITOR >>>>> Exception.message = {}", e.getMessage());
				System.exit(-1);
			}
		}
		
		if (Flag.flag) {
			log.info("MONITOR >>>>> lnsJson.RES = {}", resJson.toPrettyJson());
			
			LnsStream resStream = new LnsStream(resJson.getResData());
			log.info("MONITOR >>>>> lnsStream.RES = {}", resStream.toPrettyJson());
		}
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	private void job08() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
	}

	private void job09() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
	}

	private void job10() {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
	}
}
