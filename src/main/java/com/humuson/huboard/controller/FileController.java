package com.humuson.huboard.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.humuson.huboard.model.FileVo;
import com.humuson.huboard.service.BoardService;
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
		System.out.println(storedName);
		return '"'+ storedName +'"';
	}
	
	//파일 DB에 등록
	@PostMapping("/file-db")
	@ResponseBody
	public String fileToDB(@RequestBody FileVo filevo) throws Exception {
		fileService.addFileToDB(filevo);
		return "success";
	}
	
	@DeleteMapping("/file-db/{fileId}")
	@ResponseBody
	public ResponseEntity<Long> delete(@PathVariable Long fileId) throws Exception{
		ResponseEntity<Long> status;
		
		try {
			fileService.deleteFiles(fileId);
			status = new ResponseEntity<>(fileId, HttpStatus.OK);
		}catch (Exception e){
			status = new ResponseEntity<>(fileId, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return status;
	}
}
