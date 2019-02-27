package com.spring.mapper;

import java.util.List;

import com.spring.model.Board;
import com.spring.model.Criteria;

public interface BoardMapper {

	public List<Board> getBoardList();

	public int insertBoard(Board board);

	public Board getBoard(Long boardIdx);

	public int updateBoard(Board board);

	public int deleteBoard(Long boardIdx);

	public List<Board> getBoardListWithPaging(Criteria cri);
}
