package org.tain;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.data.LnsData;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.working.async.AsyncWorking;
import org.tain.working.authJob.AuthJobWorking;
import org.tain.working.lnsJsonNode.LnsJsonNodeWorking;
import org.tain.working.properties.PropertiesWorking;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaLightnet01Auth11Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet01Auth11Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) job01();  // properties
		if (!Flag.flag) job02();  // async on testing
		if (Flag.flag) job03();  // authJob 
		if (!Flag.flag) job04();  // LnsJsonNode on testing
		if (Flag.flag) job05();
		if (Flag.flag) job06();
		if (Flag.flag) job07();
		if (Flag.flag) job08();
		if (Flag.flag) job09();
		if (Flag.flag) job10();
		
		//if (Flag.flag) System.exit(0);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private PropertiesWorking propertiesWorking;
	
	private void job01() {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.propertiesWorking.print();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private AsyncWorking asyncWorking;
	
	private void job02() {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.asyncWorking.runningTaskThread();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	@Autowired
	private AuthJobWorking authJobWorking;
	
	private void job03() {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsData.getInstance().setAccessToken(this.projEnvParamProperties.getAccessToken());
		}
		
		if (Flag.flag) {
			this.authJobWorking.running();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private LnsJsonNodeWorking lnsJsonNodeWorking;
	
	private void job04() throws Exception {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.lnsJsonNodeWorking.test01();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void job05() {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void job06() {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void job07() {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void job08() {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void job09() {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void job10() {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
		}
	}
}
