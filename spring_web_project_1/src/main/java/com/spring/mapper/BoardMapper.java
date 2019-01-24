package com.spring.mapper;

import java.util.List;

import com.spring.model.Board;

public interface BoardMapper {

	public List<Board> getboardList();

	public int insertBoard(Board board);

	public Board getBoard(Long boardIdx);

	public int updateBoard(Board board);

	public int deleteBoard(Long boardIdx);
}
