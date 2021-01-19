package org.tain.task;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.data.LnsError;
import org.tain.properties.ProjEnvJsonProperties;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.Sleep;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ErrorReaderJob {

	private static final int LOOP_SLEEP_SEC = 10;
	
	private String param;
	
	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	@Autowired
	private ProjEnvJsonProperties projEnvJsonProperties;
	
	private Map<String, LnsError> mapError = new HashMap<>();
	
	public LnsError get(String key) {
		return this.mapError.get(key);
	}
	
	public LnsError search(String message) {
		// regex
		
		for (Map.Entry<String, LnsError> entry : this.mapError.entrySet()) {
			LnsError lnsError = entry.getValue();
			if (Flag.flag) log.info(">>>>> " + lnsError.getError_regex());
		}
		return null;
	}
	
	@Async(value = "async_errorReaderJob")
	public void errorReaderJob(String param) throws Exception {
		this.param = param;
		log.info("KANG-20200721 >>>>> {} {} {}", this.param, CurrentInfo.get());
		
		String errorFile = null;
		if (Flag.flag) {
			String home = this.projEnvParamProperties.getHome();
			String base = this.projEnvParamProperties.getBase();
			String infoPath = this.projEnvParamProperties.getInfoPath();
			String errorInfoFile = this.projEnvJsonProperties.getErrorInfoFile();
			errorFile = home + base + infoPath + File.separator + errorInfoFile;
			
			log.info(">>>>> errorFile: " + errorFile);
		}
		
		if (Flag.flag) {
			//
			this.mapError.clear();
			long oldLastModified = 0L;
			File fileError = new File(errorFile);
			
			while (true) {
				long lastModified = fileError.lastModified();
				//if (Flag.flag) log.info("============ sleep 5 sec ================" + fileError.lastModified());
				
				if (oldLastModified != lastModified) {
					if (Flag.flag) log.info(">>>>> update file error.json and doing some job... ");
					oldLastModified = lastModified;
					
					List<LnsError> lstLnsError = JsonPrint.getInstance().getObjectMapper().readValue(StringTools.stringFromFile(errorFile), new TypeReference<List<LnsError>>() {});
					if (Flag.flag) lstLnsError.forEach(lnsError -> {
						if (Flag.flag) log.info(">>>>> {} = {}", lnsError.getKey(), lnsError);
						this.mapError.put(lnsError.getKey(), lnsError);
					});
				}
				
				Sleep.run(LOOP_SLEEP_SEC * 1000);
			}
		}
		
		if (!Flag.flag) {
			/*
			// initialize
			File fileBasePath = new File(basePath);
			File[] files = fileBasePath.listFiles();
			Arrays.sort(files);
			for (final File fileEntry : files) {
				if (fileEntry.isFile()) {
					log.info(">>>>> [{}] [{}]", fileEntry.getParent(), fileEntry.getName());
					
					if (!StringTools.isExtension(fileEntry.getName(), "json") || "apis.json".equals(fileEntry.getName()))
						continue;
					
					LnsMstInfo lnsMstInfo = new LnsMstInfo(fileEntry.getParent(), fileEntry.getName());
					String strLength = new LnsStreamLength(lnsMstInfo).getStrLength();
					lnsMstInfo.setLength(strLength);
					lnsMstInfo.setExtHttpUrl(this.projEnvUrlProperties.getLightnet11());
					
					String reqResType = lnsMstInfo.getReqResType();
					this.mapInfo.put(reqResType, lnsMstInfo);
					String fileName = lnsMstInfo.getFileName();
					this.mapInfo.put(fileName, lnsMstInfo);
					
					if (Flag.flag) {
						log.info("======================= START: {} ==========================", lnsMstInfo.getFileName());
						log.info(">>>>> basePath = {}", lnsMstInfo.getBasePath());
						log.info(">>>>> fileName = {}", lnsMstInfo.getFileName());
						log.info(">>>>> filePath = {}", lnsMstInfo.getFilePath());
						log.info(">>>>> lastModified = {}", lnsMstInfo.getLastModfied());
						log.info(">>>>> strJsonInfo = {}", lnsMstInfo.getStrJsonInfo());
						log.info(">>>>> reqResType = {}", lnsMstInfo.getReqResType());
						log.info(">>>>> infoNode = {}", lnsMstInfo.getInfoNode().toPrettyString());
						log.info(">>>>> streamHead = [{}]", lnsMstInfo.getStreamHead());
						log.info(">>>>> jsonHead = '{}'", lnsMstInfo.getJsonHead());
						log.info(">>>>> cstruct = '{}'", lnsMstInfo.getCStruct());
						//log.info(">>>>> headBaseInfoNode = {}", lnsMstInfo.getHeadBaseInfoNode().toPrettyString());
						//log.info(">>>>> headDataInfoNode = {}", lnsMstInfo.getHeadDataInfoNode().toPrettyString());
						//log.info(">>>>> bodyBaseInfoNode = {}", lnsMstInfo.getBodyBaseInfoNode().toPrettyString());
						//log.info(">>>>> bodyDataInfoNode = {}", lnsMstInfo.getBodyDataInfoNode().toPrettyString());
						log.info("----------------------- END: {} --------------------------", lnsMstInfo.getFileName());
					}
				}
			}
			*/
		}
		
		if (!Flag.flag) {
			/*
			// check and update every 10 seconds
			File fileBasePath = new File(basePath);
			while (true) {
				File[] files = fileBasePath.listFiles();
				Arrays.sort(files);
				for (final File fileEntry : files) {
					if (fileEntry.isDirectory()) {
						// subdirectory
					} else if (fileEntry.isFile()) {
						// file
						if (!StringTools.isExtension(fileEntry.getName(), "json") || "apis.json".equals(fileEntry.getName()))
							continue;
						
						LnsMstInfo lnsMstInfo = this.mapInfo.get(fileEntry.getName());
						if (lnsMstInfo == null) {
							// new
							lnsMstInfo = new LnsMstInfo(fileEntry.getParent(), fileEntry.getName());
							String strLength = new LnsStreamLength(lnsMstInfo).getStrLength();
							lnsMstInfo.setLength(strLength);
							lnsMstInfo.setExtHttpUrl(this.projEnvUrlProperties.getLightnet11());
							
							String reqResType = lnsMstInfo.getReqResType();
							this.mapInfo.put(reqResType, lnsMstInfo);
							String fileName = lnsMstInfo.getFileName();
							this.mapInfo.put(fileName, lnsMstInfo);
							
							if (Flag.flag) {
								log.info("======================= START: {} ==========================", lnsMstInfo.getFileName());
								log.info(">>>>> basePath = {}", lnsMstInfo.getBasePath());
								log.info(">>>>> fileName = {}", lnsMstInfo.getFileName());
								log.info(">>>>> filePath = {}", lnsMstInfo.getFilePath());
								log.info(">>>>> lastModified = {}", lnsMstInfo.getLastModfied());
								log.info(">>>>> strJsonInfo = {}", lnsMstInfo.getStrJsonInfo());
								log.info(">>>>> reqResType = {}", lnsMstInfo.getReqResType());
								log.info(">>>>> infoNode = {}", lnsMstInfo.getInfoNode().toPrettyString());
								log.info(">>>>> streamHead = [{}]", lnsMstInfo.getStreamHead());
								log.info(">>>>> jsonHead = '{}'", lnsMstInfo.getJsonHead());
								//log.info(">>>>> headBaseInfoNode = {}", lnsMstInfo.getHeadBaseInfoNode().toPrettyString());
								//log.info(">>>>> headDataInfoNode = {}", lnsMstInfo.getHeadDataInfoNode().toPrettyString());
								//log.info(">>>>> bodyBaseInfoNode = {}", lnsMstInfo.getBodyBaseInfoNode().toPrettyString());
								//log.info(">>>>> bodyDataInfoNode = {}", lnsMstInfo.getBodyDataInfoNode().toPrettyString());
								log.info("======================= END: {} ==========================", lnsMstInfo.getFileName());
							}
						} else if (lnsMstInfo.checkAndUpdate(fileEntry)) {
							// update
							lnsMstInfo.setExtHttpUrl(this.projEnvUrlProperties.getLightnet11());
							if (Flag.flag) log.info(">>>>> {} file is updated.", fileEntry.getName());
						}
					}
				}
				
				Sleep.run(10 * 1000);
			}
			*/
		}
	}
}