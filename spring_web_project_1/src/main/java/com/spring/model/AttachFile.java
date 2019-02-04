package com.spring.model;

import lombok.Data;

@Data
public class AttachFile {

	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;

}

