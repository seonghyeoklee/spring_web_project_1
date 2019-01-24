package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.model.Board;
import com.spring.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {

	@Autowired
	BoardService boardService;

	@GetMapping("/list")
	public String listGET(Model model) {

		List<Board> list = boardService.getboardList();
		model.addAttribute("list", list);

		return "/board/list";
	}

	@PostMapping("/register")
	public String createPOST(RedirectAttributes rttr, Board board) {

		boardService.insertBoard(board);

		rttr.addAttribute("boardIdx", board.getBoardIdx());
		return "redirect:/board/list";
	}

	@GetMapping("/{idx}")
	public String readGET() {

		return "";
	}

	@PutMapping("/{idx}")
	public String updatePUT() {

		return "";
	}

	@DeleteMapping("/{idx}")
	public String deleteDELETE() {

		return "";
	}
}
