package org.tain.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Layout;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/test"})
@Slf4j
public class TestController {

	@GetMapping(value = {"", "/kang"})
	public String testKang(Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		
		Map<String,Object> map = new HashMap<>();
		map.put("name", "Kiea 강석");
		map.put("date", LocalDateTime.now());
		model.addAttribute("data", map);
		model.addAttribute("layout-1", Layout.get(1));
		model.addAttribute("layout-2", Layout.get(2));
		log.info("KANG-20200701 >>>>> {}", model);
		
		return "test/kang";
	}
}
