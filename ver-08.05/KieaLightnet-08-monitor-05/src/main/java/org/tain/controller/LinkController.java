package org.tain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tain.service.LinkService;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/link"})
@Slf4j
public class LinkController {

	@Autowired
	private LinkService linkService;
	
	@GetMapping(value = {"/list"})
	public String list(Pageable pageable, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("list", this.linkService.findLineList(pageable));
		return "lightnet/link/list";
	}
	
	@GetMapping(value = {""})
	public String form(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("link", this.linkService.findLineById(id));
		return "lightnet/link/form";
	}
}
