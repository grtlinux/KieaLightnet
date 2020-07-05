package org.tain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tain.service.KuserService;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/kuser"})
@Slf4j
public class KuserController {

	@Autowired
	private KuserService kuserService;
	
	@GetMapping(value = {"/list"})
	public String list(Pageable pageable, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("list", this.kuserService.findKuserList(pageable));
		return "kuser/list";
	}
	
	@GetMapping(value = {""})
	public String form(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("kuser", this.kuserService.findKuserById(id));
		return "kuser/form";
	}
	
	@GetMapping(value = {"/username/{username}"})
	public String form(@PathVariable(value = "username") String username, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("kuser", this.kuserService.findKuserByUsername(username));
		return "kuser/form";
	}
	
	@GetMapping(value = {"/list/{role}"})
	public String listByUserId(@PathVariable(value = "role") String role, Pageable pageable, Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("list", this.kuserService.findKuserListByRole(pageable, role));
		return "kuser/list";
	}
}
