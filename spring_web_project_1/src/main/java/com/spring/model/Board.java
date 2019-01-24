package com.spring.model;

import java.util.Date;

import lombok.Data;

@Data
public class Board {

	private Long boardIdx;
	private String title;
	private String content;
	private String writer;
	private Date createdAt;
	private Date updatedAt;
}
