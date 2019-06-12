package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.ReplyMapper;
import com.spring.model.Criteria;
import com.spring.model.Reply;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public int register(Reply reply) {

		return replyMapper.insert(reply);
	}

	@Override
	public Reply get(Long rno) {

		return replyMapper.read(rno);
	}

	@Override
	public int remove(Long rno) {

		return replyMapper.delete(rno);
	}

	@Override
	public int modify(Reply reply) {

		return replyMapper.update(reply);
	}

	@Override
	public List<Reply> getList(Criteria cri, Long bno) {

		return replyMapper.getListWithPaging(cri, bno);
	}

}
