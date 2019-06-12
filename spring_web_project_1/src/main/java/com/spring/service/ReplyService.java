package com.spring.service;

import java.util.List;

import com.spring.model.Criteria;
import com.spring.model.Reply;

public interface ReplyService {

	public int register(Reply reply);

	public Reply get(Long rno);

	public int remove(Long rno);

	public int modify(Reply reply);

	public List<Reply> getList(Criteria cri, Long bno);
}
