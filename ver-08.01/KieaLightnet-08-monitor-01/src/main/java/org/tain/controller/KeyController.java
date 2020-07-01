package org.tain.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Layout;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/key"})
@Slf4j
public class KeyController {

	@Value("${job.default-path}")
	private String jobDefaultPath;
	
	//@Autowired
	//private BoardService boardService;
	
	@GetMapping(value = {"/info"})
	public String keyInfo(Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		//model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, "info"));
		model.addAttribute("layout", Layout.get(2));
		return jobDefaultPath + "key/info";
	}
	
	@GetMapping(value = {"/refresh"})
	public String keyRefresh(Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		//model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, "refresh"));
		model.addAttribute("layout", Layout.get(2));
		return jobDefaultPath + "key/refresh";
	}
}
