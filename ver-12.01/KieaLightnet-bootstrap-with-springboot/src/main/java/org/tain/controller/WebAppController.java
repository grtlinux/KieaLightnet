package org.tain.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebAppController {

	private String appMode;

	@Autowired
	public WebAppController(Environment environment){
		appMode = environment.getProperty("app-mode");
	}

	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("datetime", new Date());
		model.addAttribute("username", "@omeryazir");
		model.addAttribute("projectname", "WebApp");

		model.addAttribute("mode", appMode);

		return "index";
	}
}