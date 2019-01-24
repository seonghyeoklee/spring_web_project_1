package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.GsonBuilder;
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
	public String boardListGET() {

		List<Board> list = boardService.getBoardList();
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(list));

		return "/board/list";
	}
}
