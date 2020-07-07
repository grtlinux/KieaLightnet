package org.tain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/tools"})
@Slf4j
public class ToolsController {

	@GetMapping(value = {"/tranJsonNormalPretty"})
	public String tranJsonNormalPretty(Model model) {
		log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get());
		return "tools/tranJsonNormalPretty";
	}
}
