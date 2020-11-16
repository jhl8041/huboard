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
	
	@Autowired
	private FileRepository fileRepo;
	
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
	
	
	/*---------------------------------------- 댓글 서비스 ---------------------------------------------*/
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
		//부모댓글 정보 가져오기
		Long parentGroupId = comment.getGroupId();
		Long parentOrderNo = comment.getOrderNo();
		Long parentDepth = comment.getDepth();
		Long parentCommentId = comment.getParentCommentId();
		
		Long currentOrderNo = 0L;
		
		//group_id & depth & parent_id 같은애들 조회 -> 그중 가장 큰 orderNo 조회 -> 그 orderNo +1이 현재 순서
		Optional<CommentVo> cocoPrev  = commentRepo.findTopByGroupIdAndDepthAndParentCommentIdOrderByOrderNoDesc(parentGroupId, parentDepth+1, parentCommentId);
		
		if (cocoPrev.isEmpty()) {
			currentOrderNo = parentOrderNo+1;
		}
		else {
			currentOrderNo = cocoPrev.get().getOrderNo()+1;
		}
		
		//현재 대댓글에 정보 부여
		comment.setParentCommentId(parentCommentId);
		comment.setDepth(parentDepth+1);
		comment.setGroupId(parentGroupId);
		comment.setOrderNo(currentOrderNo);
		
		//현재 대댓글 이후에 올 댓글들 순서 1씩 추가
		List<CommentVo> pushList = commentRepo.findByGroupIdAndOrderNoGreaterThanEqual(parentGroupId, currentOrderNo);
		for(CommentVo push: pushList) {
			push.setOrderNo(push.getOrderNo()+1);
			commentRepo.save(push);
		}
		
		//대댓글 저장
		commentRepo.save(comment);

	}
	
	public List<CommentVo> getComment(Long boardId) {
		return commentRepo.findByBoardIdOrderByGroupIdAscOrderNoAsc(boardId);
	}
	
	
	/*---------------------------------------- 파일 서비스 ---------------------------------------------*/
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
