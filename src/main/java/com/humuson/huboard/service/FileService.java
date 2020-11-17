package com.humuson.huboard.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humuson.huboard.model.FileVo;
import com.humuson.huboard.repository.BoardRepository;
import com.humuson.huboard.repository.FileRepository;

@Service
public class FileService {
	@Autowired
	private FileRepository fileRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	public void addFileToDB(FileVo filevo) {
		Long nextBoardId = boardRepo.findTopByOrderByBoardIdDesc().get().getBoardId()+1;
		filevo.setBoardId(nextBoardId);
		filevo.setRegDate(Timestamp.valueOf(LocalDateTime.now()));
		fileRepo.save(filevo);
	}
	
	public List<FileVo> getFiles(Long boardId){
		return fileRepo.findByBoardId(boardId);
	}
	
}
