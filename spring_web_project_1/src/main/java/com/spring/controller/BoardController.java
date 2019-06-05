package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.model.Board;
import com.spring.model.Criteria;
import com.spring.model.PageDTO;
import com.spring.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {

	@Autowired
	BoardService boardService;

	@GetMapping("/register")
	public void register() {

	}

	@GetMapping("/list")
	public String list(Criteria cri, Model model) {

		List<Board> list = boardService.getList(cri);

		if(list == null) {
			throw new NullPointerException();
		}

		int total = boardService.getTotal(cri);
		log.info("total : " + total);

		model.addAttribute("list", list);
		model.addAttribute("pageMaker", new PageDTO(cri, total));

		return "/board/list";
	}

	@PostMapping("/register")
	public String create(RedirectAttributes rttr, Board board) {

		boardService.insertBoard(board);

		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}

	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		Board board = boardService.getBoard(bno);

		if(board == null) {
			throw new NullPointerException();
		}

		model.addAttribute("board", board);
	}

	@PostMapping("/modify")
	public String update(RedirectAttributes rttr, Board board, @ModelAttribute("cri") Criteria cri) {

		int updateCount = boardService.updateBoard(board);

		if(updateCount == 1) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/board/list" + cri.getListLink();
	}

	@PostMapping("/remove")
	public String delete(RedirectAttributes rttr, Board board, @ModelAttribute("cri") Criteria cri) {

		int deleteCount = boardService.deleteBoard(board.getBno());

		if(deleteCount == 1) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/board/list" + cri.getListLink();
	}
}
