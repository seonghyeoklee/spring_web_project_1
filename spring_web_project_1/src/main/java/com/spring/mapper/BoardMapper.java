package com.spring.mapper;

import java.util.List;

import com.spring.model.Board;
import com.spring.model.Criteria;

public interface BoardMapper {

	public List<Board> getBoardList();

	public int insertBoard(Board board);

	public Board getBoard(Long bno);

	public int updateBoard(Board board);

	public int deleteBoard(Long bno);

	public List<Board> getBoardListWithPaging(Criteria cri);

	public int getTotalCount(Criteria cri);
}
