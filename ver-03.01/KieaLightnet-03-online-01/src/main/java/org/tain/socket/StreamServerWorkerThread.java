package org.tain.socket;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tain.config.SkipSSLConfig;
import org.tain.object.Packet;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamServerWorkerThread extends Thread {

	private Socket socket = null;
	private StreamPacket streamPacket = null;
	private Packet packet = null;
	
	public StreamServerWorkerThread(Socket socket) throws Exception {
		this.socket = socket;
		this.streamPacket = new StreamPacket(this.socket);
	}
	
	@Override
	public void run() {
		Random random = new Random(new Date().getTime());
		
		try {
			do {
				this.packet = this.streamPacket.recvPacket();
				if (Flag.flag) System.out.println("SERVER >>>>> " + this.packet);
		
				// 0. detail
				// 1. validate
				// 2. commit
				// 3. batch
				switch (random.nextInt() % 3) {
				case 0:
					this.httpPostDetail();
					break;
				case 1:
					this.httpPostDetail();
					break;
				case 2:
					this.httpPostDetail();
					break;
				default:
					break;
				}
				
				Sleep.run(2000);
			} while(this.packet != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private String DETAIL_HTTP_URL = "http://localhost:8082/link/detail";
	
	private void httpPostDetail() throws Exception {
		log.info("KANG-20200623 >>>>> {}", CurrentInfo.get());
		
		ResponseEntity<String> response = null;
		
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			String parameters = ""
				+ "{\n"
				+ "    \"sourceCountry\": \"KOR\",\n"
				+ "    \"destinationCountry\": \"KHM\",\n"
				+ "    \"destinationOperatorCode\": \"lyhour\",\n"
				+ "    \"withdrawableAmount\": \"1.500\",\n"
				+ "    \"transactionCurrency\": \"USD\",\n"
				+ "    \"deliveryMethod\": \"cash\"\n"
				+ "}\n";
			
			HttpEntity<String> request = new HttpEntity<>(parameters, headers);

			RestTemplate restTemplate = new RestTemplate();
			for (int i=0; i < 5; i++) {
				response = restTemplate.exchange(DETAIL_HTTP_URL, HttpMethod.POST, request, String.class);
				
				log.info("=====================================================");
				log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
				log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
				log.info("=====================================================");
				
				if (response.getStatusCodeValue() == 200) {
					break;
				}
				
				log.info("KANG-20200618 >>>>> {} after 5sec, retry to connect.....", CurrentInfo.get());
				try { Thread.sleep(5000); } catch (InterruptedException e) {}
			}
		}
		
		log.info("KANG-20200623 >>>>> response.getBody() = {}", response.getBody());
		
		return;
	}
	
	private void httpPostDetail0() throws Exception {
		if (Flag.flag) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("sourceCountry", "KOR");
			paramMap.put("destinationCountry", "KHM");
			paramMap.put("destinationOperatorCode", "lyhour");
			paramMap.put("withdrawableAmount", "1.500");
			paramMap.put("transactionCurrency", "USD");
			paramMap.put("deliveryMethod", "cash");
			
			HttpEntity<Map<String,Object>> request = new HttpEntity<>(paramMap, headers);

			SkipSSLConfig.skip();
			RestTemplate restTemplate = new RestTemplate();
			for (int i=0; i < 5; i++) {
				ResponseEntity<String> response = restTemplate.exchange(DETAIL_HTTP_URL, HttpMethod.POST, request, String.class);
				
				log.info("=====================================================");
				log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
				log.info("KANG-20200623 >>>>> response.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info("KANG-20200623 >>>>> response.getStatusCode()      = {}", response.getStatusCode());
				log.info("=====================================================");
				
				if (response.getStatusCodeValue() == 200) {
					break;
				}
				
				log.info("KANG-20200618 >>>>> {} after 5sec, retry to connect.....", CurrentInfo.get());
				try { Thread.sleep(5000); } catch (InterruptedException e) {}
			}
		}
		
		return;
	}
	
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
}
