package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.model.Board;
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
	public String listGET(Model model) {

		List<Board> list = boardService.getboardList();

		if(list == null) {
			throw new NullPointerException();
		}

		model.addAttribute("list", list);

		return "/board/list";
	}

	@PostMapping("/register")
	public String createPOST(RedirectAttributes rttr, Board board) {

		boardService.insertBoard(board);

		rttr.addFlashAttribute("result", board.getBoardIdx());
		return "redirect:/board/list";
	}

	@GetMapping({"/get", "/modify"})
	public void readGET(@RequestParam("boardIdx") Long boardIdx, Model model) {
		Board board = boardService.getBoard(boardIdx);

		if(board == null) {
			throw new NullPointerException();
		}

		model.addAttribute("board", board);
	}

	@PostMapping("/modify")
	public String updatePUT(RedirectAttributes rttr, Board board) {

		int updateCount = boardService.updateBoard(board);

		if(updateCount == 1) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/board/list";
	}

	@DeleteMapping("/remove")
	public String deleteDELETE(@RequestParam("boardIdx") Long boardIdx, RedirectAttributes rttr) {

		int deleteCount = boardService.deleteBoard(boardIdx);

		if(deleteCount == 1) {
			rttr.addAttribute("result", "success");
		}

		return "redirect:/board/list";
	}
}
