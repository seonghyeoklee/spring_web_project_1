package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.BoardMapper;
import com.spring.model.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;

	@Override
	public List<Board> getboardList() {

		return boardMapper.getboardList();
	}

	@Override
	public int insertBoard(Board board) {

		return boardMapper.insertBoard(board);
	}

	@Override
	public Board getBoard(Long boardIdx) {

		return boardMapper.getBoard(boardIdx);
	}

	@Override
	public int updateBoard(Board board) {

		return boardMapper.updateBoard(board);
	}

	@Override
	public int deleteBoard(Long boardIdx) {

		return boardMapper.deleteBoard(boardIdx);
	}

}
