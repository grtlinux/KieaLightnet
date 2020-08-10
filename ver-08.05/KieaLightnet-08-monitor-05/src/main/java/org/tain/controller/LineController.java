package org.tain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tain.service.LineService;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/line"})
@Slf4j
public class LineController {

	@Autowired
	private LineService lineService;
	
	@GetMapping(value = {"/list"})
	public String list(Pageable pageable, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("list", this.lineService.findLineList(pageable));
		return "lightnet/line/list";
	}
	
	@GetMapping(value = {""})
	public String form(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("line", this.lineService.findLineById(id));
		return "lightnet/line/form";
	}
}
