package com.spring.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/upload")
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {

	}

	@PostMapping("/uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile) {

		String uploadFolder = "C:"+File.separator+"upload";

		for(MultipartFile multipartFile : uploadFile) {
			log.info("================================");
			log.info(multipartFile.getOriginalFilename());				//원본파일명(IE 파일경로 포함)
			log.info(multipartFile.getContentType());
			log.info(multipartFile.getSize());

			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {

	}

	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxAction(MultipartFile[] uploadFile) {

		String uploadFolder = "C:" + File.separator + "upload";

		File uploadPath = new File(uploadFolder, getFolder());

		log.info(uploadPath);

		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for(MultipartFile multipartFile : uploadFile) {
			log.info("================================");
			log.info(multipartFile.getOriginalFilename());
			log.info(multipartFile.getContentType());
			log.info(multipartFile.getSize());

			String uploadFileName = multipartFile.getOriginalFilename();

			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			File saveFile = new File(uploadPath, uploadFileName);

			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}
}
