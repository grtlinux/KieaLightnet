package org.tain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Layout;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/"})
@Slf4j
public class HomeController {

	@GetMapping(value = {"", "home"})
	public String testKang(Model model) {
		log.info("KANG-20200630 >>>>> {} {}", CurrentInfo.get());
		
		model.addAttribute("layout", Layout.get(2));
		log.info("KANG-20200630 >>>>> {}", model);
		
		return "home";
	}
}
