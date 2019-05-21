package com.spring.service;

import java.util.List;

import com.spring.model.Board;
import com.spring.model.Criteria;

public interface BoardService {

	public List<Board> getList(Criteria cri);

	//public List<Board> getBoardList();

	public int insertBoard(Board board);

	public Board getBoard(Long boardIdx);

	public int updateBoard(Board board);

	public int deleteBoard(Long boardIdx);

	public int getTotal(Criteria cri);
}
