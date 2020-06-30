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
@RequestMapping(value = {"/send"})
@Slf4j
public class SendController {

	@Value("${job.default-path}")
	private String jobDefaultPath;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = {"/validate"})
	public String sendValidate(Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, "validate"));
		return jobDefaultPath + "send/validate";
	}
	@GetMapping(value = {"/commit"})
	public String sendCommit(Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, "commit"));
		return jobDefaultPath + "send/commit";
	}
	@GetMapping(value = {"/list"})
	public String sendList(Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, "list"));
		return jobDefaultPath + "send/list";
	}
	@GetMapping(value = {"/detail"})
	public String sendDetail(Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, "detail"));
		return jobDefaultPath + "send/detail";
	}
}
