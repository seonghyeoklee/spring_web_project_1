package com.spring.model;

import java.util.Date;

import lombok.Data;

@Data
public class Board {

	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
}
