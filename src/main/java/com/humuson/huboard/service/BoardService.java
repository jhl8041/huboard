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
	
	@Autowired
	private CommentRepository commentRepo;
	
	public Page<BoardVo> getPagingPost(Long categoryId, Pageable pageable){	
		return boardRepo.findByVisibleAndCategoryId("Y", categoryId,  pageable);
	}
	
	public List<BoardVo> getTopTen(Long categoryId){
		return boardRepo.findTop10ByCategoryId(categoryId);
	}
	
	
	public Page<BoardVo> findPostBySearch(String keyword, Long categoryId,Pageable pageable, String search_type) {
		Page<BoardVo> searchPage = boardRepo.findByVisibleAndCategoryId("Y", categoryId, pageable);
		if (search_type.equals("subject")) {
			searchPage = boardRepo.findBySubjectContainingAndVisibleAndCategoryId(keyword, "Y", categoryId, pageable);
		}
		else if (search_type.equals("userId")) {
			searchPage = boardRepo.findByUserIdContainingAndVisibleAndCategoryId(keyword, "Y", categoryId, pageable);
		}
		else if (search_type.equals("content")) {
			searchPage = boardRepo.findByContentContainingAndVisibleAndCategoryId(keyword, "Y", categoryId, pageable);
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

	public void updateCommentCnt(Long boardId) {
		BoardVo board = boardRepo.findById(boardId).get();
		List<CommentVo> comments = commentRepo.findByBoardId(boardId);
		board.setCommentCnt(comments.size());
		boardRepo.save(board);
	}
	
}
