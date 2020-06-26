package org.tain;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.domain.Lightnet;
import org.tain.repository.LightnetRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaLightnet06Info01Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaLightnet06Info01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (Flag.flag)
			job02();
	}

	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	
	@Value("${json.table-file.lightnet}")
	private String jsonTableFileLightnet;
	
	@Autowired
	private LightnetRepository lightnetRepository;
	
	private void job02() throws Exception {
		log.info("KANG-20200618 >>>>> {}", CurrentInfo.get());
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Lightnet> lstLightnet = objectMapper.readValue(new File(this.jsonTableFileLightnet), new TypeReference<List<Lightnet>>() {});

		lstLightnet.forEach(entry -> {
			this.lightnetRepository.save(entry);
		});
	}
}
