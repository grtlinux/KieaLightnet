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
@RequestMapping(value = {"/recv"})
@Slf4j
public class RecvController {

	@Value("${job.default-path}")
	private String jobDefaultPath;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = {"/callback"})
	public String recvCallback(Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, "callback"));
		return jobDefaultPath + "recv/callback";
	}
}
