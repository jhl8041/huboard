package com.humuson.huboard.service;

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
import com.humuson.huboard.repository.BoardRepository;
import com.humuson.huboard.repository.CommentRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	public List<BoardVo> getAllPost(){
		return boardRepo.findAll();
	}
	
	public Page<BoardVo> getPagingPost(Pageable pageable){
		Page<BoardVo> pager = boardRepo.findAll(pageable);
//		List<BoardVo> paged_list = pager.getContent();
//		System.out.println("총 페이지 수: " + pager.getTotalPages());
//		System.out.println("총 게시글 수: " + pager.getTotalElements());
//		System.out.println("페이지당 게시글 수: " + pager.getNumberOfElements());
//		System.out.println("현재페이지: " + pager.getNumber());
//		System.out.println("사이즈: " + pager.getSize());
		return pager;
	}
	
	public Page<BoardVo> findPostBySearch(String keyword, Pageable pageable, String search_type) {
		Page<BoardVo> searchPage = boardRepo.findAll(pageable);
		System.out.println(search_type);
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
	
	public void editPost(BoardVo boardVo){
		boardRepo.save(boardVo);
	}
	
	public void addPost(BoardVo boardVo){
		boardRepo.save(boardVo);
	}
	
	//메인댓글 추가
	public void addComment(CommentVo commentvo) {
		commentvo.setParentCommentId(0L);
		commentvo.setDepth(0L);
		
		if (commentRepo.findAll().isEmpty()) {
			commentvo.setGroupId(1L);
		}
		else {
			//새로운 그룹아이디 부여
			Long newGroupId = commentRepo.findTopByOrderByGroupIdDesc().get().getGroupId();
			commentvo.setGroupId(newGroupId+1);
		}
		
		commentvo.setOrderNo(1L);
		commentRepo.save(commentvo);
	}
	
	//대댓글 추가
	public void addCoComment(CommentVo comment) {
		Long groupId = comment.getGroupId();
		
		Long parentOrderNo = comment.getOrderNo();
		Long currentOrderNo = parentOrderNo+1;
		
		Long parentDepth = comment.getDepth();
		Long currentDepth = parentDepth+1;
		
		comment.setParentCommentId(comment.getParentCommentId());
		comment.setDepth(currentDepth);
		comment.setGroupId(groupId);
		
		Long endOrderNo = commentRepo.findTopByGroupIdOrderByOrderNoDesc(groupId).get().getOrderNo();
		
		if(commentRepo.findTopByGroupIdAndOrderNoAndDepth(groupId,currentOrderNo,currentDepth).isEmpty()) {			
			comment.setOrderNo(endOrderNo+1);
			System.out.println("if");
		}
		else {
			Long limit_start = parentOrderNo;
			Long limit_end = endOrderNo - limit_start;
			
			List<CommentVo> pushList = commentRepo.findByGroupIdOrderAndMore(groupId, limit_start, limit_end);
			
			for(CommentVo push: pushList) {
				CommentVo tempcommentvo = push;
				Long prevOrderNo = tempcommentvo.getOrderNo(); 
				tempcommentvo.setOrderNo(prevOrderNo+1);
				commentRepo.save(tempcommentvo);
			}
			
			comment.setOrderNo(currentOrderNo);
			System.out.println("else");
		}
		
		commentRepo.save(comment);
	}
	
	public List<CommentVo> getComment(Long boardId) {
		//return commentRepo.findByBoardId(boardId);
		return commentRepo.findByBoardIdOrderByGroupIdAscOrderNoAsc(boardId);
	}
	
	
}
