package org.tain.utils;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Layout {

	public static Map<String,Object> get(int jobNumber) {
		log.trace("KANG-20200701 >>>>> {}", CurrentInfo.get());
		switch (jobNumber) {
		case 1: return job1();
		case 2: return job2();
		default:
			return null;
		}
	}

	// base
	private static Map<String, Object> job1() {
		Map<String,Object> map = new HashMap<>();
		map.put("_css_", "css1");
		map.put("_script_", "script1");
		map.put("_home_", "home1");
		map.put("_page_", "page1");
		map.put("_header_", "header1");
		map.put("_footer_", "footer1");
		return map;
	}

	// navbar menu
	private static Map<String, Object> job2() {
		Map<String,Object> map = new HashMap<>();
		map.put("_css_", "css1");
		map.put("_script_", "script1");
		map.put("_home_", "home2");
		map.put("_page_", "page1");
		map.put("_header_", "header2");
		map.put("_footer_", "footer2");
		return map;
	}
}
