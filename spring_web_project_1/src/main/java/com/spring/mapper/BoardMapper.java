package com.spring.mapper;

import java.util.List;

import com.spring.model.Board;

public interface BoardMapper {

	//@Select("select * from board")
	public List<Board> getBoardList();
}
