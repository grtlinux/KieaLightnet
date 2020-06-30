package org.tain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tain.service.BoardService;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/fep"})
@Slf4j
public class FepController {

	@Value("${job.default-path}")
	private String jobDefaultPath;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = {"/lns01"})
	public String fepLns01(Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, "lns01"));
		return jobDefaultPath + "fep/lns01";
	}

	@GetMapping(value = {"/lns02"})
	public String fepLns02(Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, "lns02"));
		return jobDefaultPath + "fep/lns02";
	}

	@GetMapping(value = {"/lns51"})
	public String fepLns51(Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, "lns51"));
		return jobDefaultPath + "fep/lns51";
	}
}
