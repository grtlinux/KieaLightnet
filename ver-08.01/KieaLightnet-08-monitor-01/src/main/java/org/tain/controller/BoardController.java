package org.tain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tain.service.BoardService;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Layout;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/board"})
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = {"/list"})
	public String list(Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		model.addAttribute("boardList", this.boardService.findBoardList(pageable));
		model.addAttribute("layout", Layout.get(1));
		return "board/list";
	}
	
	@GetMapping(value = {""})
	public String board(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		model.addAttribute("board", this.boardService.findBoardById(id));
		model.addAttribute("layout", Layout.get(1));
		return "board/form";
	}
	
	@GetMapping(value = {"/list/{userId}"})
	public String listByUserId(@PathVariable(value = "userId") String userId, Pageable pageable, Model model) {
		log.info("KANG-20200629 >>>>> {}", CurrentInfo.get());
		model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, userId));
		model.addAttribute("layout", Layout.get(1));
		return "board/list";
	}
}
