package com.humuson.huboard.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.model.CommentVo;
import com.humuson.huboard.model.FileVo;
import com.humuson.huboard.repository.BoardRepository;
import com.humuson.huboard.repository.CommentRepository;
import com.humuson.huboard.repository.FileRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepo;
	
	public Page<BoardVo> getPagingPost(String visible, Pageable pageable){
		return boardRepo.findByVisible(visible, pageable);
	}
	
	public Page<BoardVo> findPostBySearch(String keyword, Pageable pageable, String search_type) {
		Page<BoardVo> searchPage = boardRepo.findAll(pageable);
		if (search_type.equals("subject")) {
			searchPage = boardRepo.findBySubjectContaining(keyword, pageable);
		}
		else if (search_type.equals("userId")) {
			searchPage = boardRepo.findByUserIdContaining(keyword, pageable);
		}
		else if (search_type.equals("content")) {
			searchPage = boardRepo.findByContentContaining(keyword, pageable);
		}
		return searchPage;
	}
	
	public Optional<BoardVo> getPost(Long id){
		return boardRepo.findById(id);
	}
	
	public void deletePost(Long id){
		boardRepo.deleteById(id);
	}
	
	public BoardVo addPost(BoardVo boardVo){
		return boardRepo.save(boardVo);
	}

	public void putCommentCnt() {
		List<BoardVo> allBoard = boardRepo.findAll();
		for (BoardVo b: allBoard) {
			b.setCommentCnt(b.getComment().size());
			boardRepo.save(b);
		}
	}
	
}
