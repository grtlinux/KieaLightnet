package org.tain.working.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.domain.apis.Apis;
import org.tain.properties.ProjEnvJsonProperties;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.repository.apis.ApisRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApisWorking {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	@Autowired
	private ProjEnvJsonProperties projEnvJsonProperties;
	
	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ApisRepository apisRepository;
	
	public void loading() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			//String rootPath = this.projEnvParamProperties.getHome() 
			//		+ this.projEnvParamProperties.getBase() 
			//		// + this.projEnvParamProperties.getInfoPath() + "/";
			//		+ this.projEnvParamProperties.getDataPath() + "/";
			String infoPath = this.projEnvParamProperties.getHome() 
					+ this.projEnvParamProperties.getBase() 
					+ this.projEnvParamProperties.getInfoPath() + "/";
			String dataPath = this.projEnvParamProperties.getHome() 
					+ this.projEnvParamProperties.getBase() 
					+ this.projEnvParamProperties.getDataPath() + "/";
			String apiFilePath = infoPath + this.projEnvJsonProperties.getApisInfoFile();
			
			try {
				List<Apis> lstApis = JsonPrint.getInstance().getObjectMapper().readValue(StringTools.stringFromFile(apiFilePath), new TypeReference<List<Apis>>() {});
				
				this.apisRepository.deleteAll();
				
				if (Flag.flag) lstApis.forEach(apis -> {
					apis.setHttpUrl(this.projEnvUrlProperties.getLightnet() + apis.getHttpUrl());
					apis.setReqJson(StringTools.stringFromFile(dataPath + apis.getReqJson()));
					apis.setResJson(StringTools.stringFromFile(dataPath + apis.getResJson()));
					apis.setReqType("0200" + apis.getType());
					apis.setResType("0210" + apis.getType());
					//apis.setResJson("");
					this.apisRepository.save(apis);                     // personal save
				});
				if (!Flag.flag) this.apisRepository.saveAll(lstApis);    // array save
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
