package org.tain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.properties.ProjEnvBaseProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;
import org.tain.working.errorTest.ErrorTest01Working;
import org.tain.working.properties.PropertiesWorking;
import org.tain.working.tasks.ErrorReaderTask;
import org.tain.working.test.TestWorking;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaLightnet02Link11Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaLightnet02Link11Application.class, args);
	}

	@Autowired
	private ProjEnvBaseProperties projEnvBaseProperties;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		try {
			if (Flag.flag) job01();  // properties
			if (!Flag.flag) job02();  // testing
			if (Flag.flag) job03();  // tasks > ErrorReaderJob¸
			if (Flag.flag) job04();  // errrorTest
			if (Flag.flag) job05();
			if (Flag.flag) job06();
			if (Flag.flag) job07();
			if (Flag.flag) job08();
			if (Flag.flag) job09();
			if (Flag.flag) job10();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.projEnvBaseProperties.isTestFlag()) {
				System.out.println("###################  SYSTEM EXIT by TestFlag ##################3");
				System.exit(0);
			}
		}
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
	private TestWorking testWorking;
	
	private void job02() {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.testWorking.test01();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ErrorReaderTask errorReaderTask;
	
	private void job03() throws Exception {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			if (Flag.flag) this.errorReaderTask.runErrorReaderJob();
		}
		
		if (Flag.flag) Sleep.run(1 * 1000);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ErrorTest01Working errorTest01Working;
	
	private void job04() throws Exception {
		log.info("KANG-20200923 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			if (Flag.flag) this.errorTest01Working.test00();  // regex
			if (!Flag.flag) this.errorTest01Working.test01();
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
