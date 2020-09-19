package org.tain.working.apis;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.object.ApisObject;
import org.tain.properties.ProjEnvJsonProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApisWorking {

	@Autowired
	private ProjEnvJsonProperties projEnvJsonProperties;
	
	public void jobFirst() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (!Flag.flag) {
			String rootPath = this.projEnvJsonProperties.getHome() + this.projEnvJsonProperties.getBase() + "/";
			JsonNode arrJsonNode = new ObjectMapper().readTree(new File(rootPath + this.projEnvJsonProperties.getApisInfoFile()));
			if (!Flag.flag) System.out.println(">>>>> " + arrJsonNode.toPrettyString());
			if (arrJsonNode.isArray()) {
				int i = 0;
				for (final JsonNode jsonNode : arrJsonNode) {
					if (Flag.flag) System.out.printf(">>>>> (%d) %s%n", ++i, jsonNode.toPrettyString());
				}
			}
		}
		
		if (!Flag.flag) {
			String rootPath = this.projEnvJsonProperties.getHome() + this.projEnvJsonProperties.getBase() + "/";
			List<ApisObject> lstApis = new ObjectMapper().readValue(new File(rootPath + this.projEnvJsonProperties.getApisInfoFile()), new TypeReference<List<ApisObject>>() {});
			lstApis.forEach(apis -> {
				if (Flag.flag) System.out.printf(">>>>> %s%n", apis);
			});
		}
		
		if (!Flag.flag) {
			String rootPath = this.projEnvJsonProperties.getHome() + this.projEnvJsonProperties.getBase() + "/";
			List<ApisObject> lstApis = new ObjectMapper().readValue(new File(rootPath + this.projEnvJsonProperties.getApisInfoFile()), new TypeReference<List<ApisObject>>() {});
			lstApis.forEach(apis -> {
				if (Flag.flag) System.out.printf(">>>>> %s%n", apis);
				try {
					String reqJson = StringTools.stringFromFile(rootPath + apis.getReqJson());
					if (Flag.flag) System.out.println(">>>>> " + reqJson);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}
	
	public void jobReqApis() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			String rootPath = this.projEnvJsonProperties.getHome() + this.projEnvJsonProperties.getBase() + "/";
			List<ApisObject> lstApis = new ObjectMapper().readValue(new File(rootPath + this.projEnvJsonProperties.getApisInfoFile()), new TypeReference<List<ApisObject>>() {});
			lstApis.forEach(apisObject -> {
				if (Flag.flag) System.out.printf(">>>>> %s%n", apisObject);
				switch(apisObject.getMapping()) {
				case "apis/auth": new _AuthAnalyze(rootPath, apisObject).analyze(); break;
				case "apis/detail": new _DetailAnalyze(rootPath, apisObject).analyze(); break;
				case "apis/validate": new _ValidateAnalyze(rootPath, apisObject).analyze(); break;
				case "apis/commit": new _CommitAnalyze(rootPath, apisObject).analyze(); break;
				case "apis/amend": new _AmendAnalyze(rootPath, apisObject).analyze(); break;
				case "apis/refund": new _RefundAnalyze(rootPath, apisObject).analyze(); break;
				case "apis/customer": new _CustomerAnalyze(rootPath, apisObject).analyze(); break;
				case "apis/histories": new _HistoriesAnalyze(rootPath, apisObject).analyze(); break;
				default: break;
				}
			});
		}
	}
}
