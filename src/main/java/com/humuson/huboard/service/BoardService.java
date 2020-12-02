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
import com.humuson.huboard.model.LikeVo;
import com.humuson.huboard.repository.BoardRepository;
import com.humuson.huboard.repository.CommentRepository;
import com.humuson.huboard.repository.FileRepository;
import com.humuson.huboard.repository.LikeRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private LikeRepository likeRepo;
	
	public Page<BoardVo> getPagingPost(Long categoryId, Pageable pageable){	
		return boardRepo.findByVisibleAndCategoryId("Y", categoryId,  pageable);
	}
	
	public List<BoardVo> getTopTen(Long categoryId){
		return boardRepo.findTop10ByCategoryIdOrderByBoardIdDesc(categoryId);
	}
	
	public Page<BoardVo> findPostBySearch(String keyword, Long categoryId,Pageable pageable, String search_type) {
		Page<BoardVo> searchPage = boardRepo.findByVisibleAndCategoryId("Y", categoryId, pageable);
		if (search_type.equals("subject")) {
			searchPage = boardRepo.findBySubjectContainingAndVisibleAndCategoryId(keyword, "Y", categoryId, pageable);
		}
		else if (search_type.equals("userId")) {
			searchPage = boardRepo.findByNicknameContainingAndVisibleAndCategoryId(keyword, "Y", categoryId, pageable);
		}
		else if (search_type.equals("content")) {
			searchPage = boardRepo.findByContentContainingAndVisibleAndCategoryId(keyword, "Y", categoryId, pageable);
		}
		return searchPage;
	}
	
	//게시글 가져오기
	public Optional<BoardVo> getPost(Long id){
		return boardRepo.findById(id);
	}
	
	//게시글 삭제
	public void deletePost(Long id){
		boardRepo.deleteById(id);
	}
	
	//게시글 추가
	public BoardVo addPost(BoardVo boardVo){
		return boardRepo.save(boardVo);
	}

	public void updateCommentCnt(Long boardId) {
		BoardVo board = boardRepo.findById(boardId).get();
		List<CommentVo> comments = commentRepo.findByBoardId(boardId);
		board.setCommentCnt(comments.size());
		boardRepo.save(board);
	}
	
	public int getViewCntByCategoryId(Long categoryId) {
		int totalCnt = 0;
		List<BoardVo> boards = boardRepo.findByVisibleAndCategoryId("Y", categoryId);
		for (BoardVo b: boards) {
			totalCnt += b.getView();
		}
		return totalCnt;
	}
	
	public boolean getIfILike(Long boardId, Long UserNum) {
		if (likeRepo.findByBoardIdAndUserNum(boardId, UserNum).isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void addLike(LikeVo likevo) {
		likeRepo.save(likevo);
	}
	
	public void deleteLike(Long boardId, Long userNum) {
		likeRepo.deleteByBoardIdAndUserNum(boardId, userNum);
	}
	
	public int getLikeCntByBoardId(Long boardId) {
		return likeRepo.findByBoardId(boardId).size();
	}
	
}
