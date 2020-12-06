package com.humuson.huboard.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.humuson.huboard.model.FileVo;
import com.humuson.huboard.model.dto.DeleteFileListDto;
import com.humuson.huboard.service.FileService;

@Controller
public class FileController {
	@Autowired
	private FileService fileService;
	
	//파일 서버에 업로드
	@PostMapping("/file-server")
	@ResponseBody
	public String fileToServer(@RequestBody MultipartFile file) throws Exception {
		String basePath = "C:\\Users\\humuson\\Desktop\\humusOn Workspace\\huboard\\src\\main\\resources\\static\\uploads";		
		UUID uuid = UUID.randomUUID();
		String storedName = uuid.toString()+"_"+file.getOriginalFilename();
		String filePath = basePath + "/" + storedName;
		file.transferTo(new File(filePath));
		return '"'+ storedName +'"';
	}
	
	//파일 DB에 등록
	@PostMapping("/file-db")
	@ResponseBody
	public String fileToDB(@RequestBody FileVo filevo) throws Exception {
		fileService.addFileToDB(filevo);
		return "success";
	}
	
	//파일 DB에서 제거
	@DeleteMapping("/file-db")
	@ResponseBody
	public ResponseEntity<Long> delete(@RequestBody DeleteFileListDto deleteFileListDto) throws Exception{	
		List<String> delList = deleteFileListDto.getDeletefileList();
		for (String i: delList) {
			System.out.println(i);
			Long fileId = Long.parseLong(i);
			fileService.deleteFiles(fileId);
		}
		
		return new ResponseEntity<>(1L, HttpStatus.OK);
	}
}
