package org.tain.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/"})
@Slf4j
public class HomeController {

	private Random random = new Random(new Date().getTime());
	
	@GetMapping(value = {"", "home"})
	public String testKang(Model model) {
		log.info("KANG-20200630 >>>>> {} {}", CurrentInfo.get());
		
		Map<String,Object> map = new HashMap<>();
		switch (random.nextInt(2)) {
		case 0: map.put("_header_", "header1"); break;
		case 1: map.put("_header_", "header2"); break;
		default: map.put("_header_", "header1"); break;
		}
		map.put("_css_", "css1");
		map.put("_script_", "script1");
		map.put("_footer_", "footer1");
		model.addAttribute("prop", map);
		log.info("KANG-20200630 >>>>> {}", map);
		
		return "home";
	}
}
