package com.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.common.Constant;
import com.spring.model.AttachFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

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

	//@PostMapping("/uploadAjaxAction")
	public void uploadAjaxAction(MultipartFile[] uploadFile) {

		//String uploadFolder = "C:" + File.separator + "upload";
		String uploadFolder = Constant.UPLOAD_FOLDER_PATH;

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

				if(multipartFile.getContentType().startsWith("image")) {

					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);

					thumbnail.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFile>> uploadAjaxPost(MultipartFile[] uploadFile) {

		List<AttachFile> list = new ArrayList<>();
		String uploadFolder = Constant.UPLOAD_FOLDER_PATH;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		String uploadFilePath = sdf.format(date).replace("-", File.separator);

		File uploadPath = new File(uploadFolder, uploadFilePath);

		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for(MultipartFile multipartFile : uploadFile) {

			UUID uuid = UUID.randomUUID();
			AttachFile attachFile = new AttachFile();
			String uploadFileName = multipartFile.getOriginalFilename();

			//IE 경우 파일경로 포함
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

			//원본파일명
			attachFile.setFileName(uploadFileName);

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			File saveFile = new File(uploadPath, uploadFileName);

			try {
				multipartFile.transferTo(saveFile);

				attachFile.setUuid(uuid.toString());
				attachFile.setUploadPath(uploadFilePath);


				if(multipartFile.getContentType().startsWith("image")) {
					attachFile.setImage(true);

					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));

					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
				}

				list.add(attachFile);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return new ResponseEntity<List<AttachFile>>(list, HttpStatus.OK);
	}

	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}

}
