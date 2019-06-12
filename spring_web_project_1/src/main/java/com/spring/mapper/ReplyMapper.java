package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.model.Criteria;
import com.spring.model.Reply;

public interface ReplyMapper {

	public int insert(Reply reply);

	public Reply read(Long rno);

	public int delete(Long rno);

	public int update(Reply reply);

	public List<Reply> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
}
