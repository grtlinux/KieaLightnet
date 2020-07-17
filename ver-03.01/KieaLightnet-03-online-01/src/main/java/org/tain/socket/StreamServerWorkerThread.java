package org.tain.socket;

import java.net.Socket;

import org.tain.object.Packet;
import org.tain.scheduler.CommitScheduler;
import org.tain.scheduler.DetailScheduler;
import org.tain.scheduler.ListScheduler;
import org.tain.scheduler.TransactionIdScheduler;
import org.tain.scheduler.ValidateScheduler;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamServerWorkerThread extends Thread {

	private Socket socket = null;
	private StreamPacket streamPacket = null;
	private Packet packet = null;
	
	public StreamServerWorkerThread(Socket socket) throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		this.socket = socket;
		this.streamPacket = new StreamPacket(this.socket);
	}
	
	@Override
	public void run() {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get());
		//Random random = new Random(new Date().getTime());
		
		try {
			do {
				this.packet = this.streamPacket.recvPacket();
				if (Flag.flag) System.out.println("SERVER >>>>> " + this.packet);
				
				String request = this.packet.getData();
				String response = null;
				
				String strLength   = request.substring(0, 4);
				String strDivision = request.substring(4, 8);
				String strType     = request.substring(8, 11);
				System.out.printf(">>>>> [%s] [%s] [%s]", strLength, strDivision, strType);
				
				switch (strDivision) {
				case "0101":
					response = ValidateScheduler.process(request);
					break;
				case "0201":
					response = CommitScheduler.process(request);
					break;
				case "0301":
					response = DetailScheduler.process(request);
					break;
				case "0401":
					response = ListScheduler.process(request);
					break;
				case "0701":
					response = TransactionIdScheduler.process(request);
					break;
				default:
					break;
				}
				this.packet = this.streamPacket.sendPacket(response);
			} while(this.packet != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	/*
	private String POST_DETAIL_HTTP_URL = "http://localhost:18082/link/detail";
	
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

			RestTemplate restTemplate = SkipSSLConfig.getRestTemplate(0);
			for (int i=0; i < 5; i++) {
				response = restTemplate.exchange(POST_DETAIL_HTTP_URL, HttpMethod.POST, request, String.class);
				
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
	*/
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/*
	private String POST_LIST_HTTP_URL = "http://localhost:18082/link/list";
	
	private void httpPostList() throws Exception {
		log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		
		String reqJson = ""
			+ "{"
			+ "\"operatorCode\": \"\","
			+ "\"offset\": \"0\","
			+ "\"limit\": \"20\""
			+ "}";
		
		ResponseEntity<String> response = null;
		
		if (Flag.flag) {
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJson, reqHeaders);

			RestTemplate restTemplate = new RestTemplate();
			for (int i=0; i < 5; i++) {
				response = restTemplate.exchange(POST_LIST_HTTP_URL, HttpMethod.POST, reqHttpEntity, String.class);
				
				log.info("=====================================================");
				log.info("KANG-20200623 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
				log.info("KANG-20200623 >>>>> POST {}", POST_LIST_HTTP_URL);
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
		
		if (Flag.flag) {
			// Pretty Print
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			JsonNode jsonNode = objectMapper.readTree(response.getBody());
			//String json = jsonNode.at("/").toPrettyString();
			String json = jsonNode.toPrettyString();
			System.out.println(">>>>> json: " + json);
		}
	}
	*/
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
}
