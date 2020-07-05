package org.tain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tain.service.StmtService;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/stmt"})
@Slf4j
public class StmtController {

	@Autowired
	private StmtService stmtService;
	
	@GetMapping(value = {"/list"})
	public String list(Pageable pageable, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("list", this.stmtService.findStmtList(pageable));
		return "stmt/list";
	}
	
	@GetMapping(value = {""})
	public String form(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("stmt", this.stmtService.findStmtById(id));
		return "stmt/form";
	}
	
	@GetMapping(value = {"/seqno/{seqNo}"})
	public String form(@PathVariable(value = "seqNo") Integer seqNo, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("stmt", this.stmtService.findStmtBySeqNo(seqNo));
		return "stmt/form";
	}
	
	@GetMapping(value = {"/list/{groupNo}"})
	public String listByUserId(@PathVariable(value = "groupNo") Integer groupNo, Pageable pageable, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("list", this.stmtService.findStmtListByGroupNo(pageable, groupNo));
		return "stmt/list";
	}
}
