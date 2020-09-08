package org.tain.working.accessToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.data.AccessToken;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccessTokenWorking {

	@Autowired
	private AccessToken accessToken;
	
	public void print() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info(">>>>> accessToken = {}", this.accessToken.get());
		}
	}
}
