package com.humuson.huboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humuson.huboard.model.CommentVo;
import com.humuson.huboard.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepo;
	
	//메인댓글 추가
	public void addComment(CommentVo commentvo) {
		commentvo.setParentCommentId(0L); //부모 아이디 초기화
		commentvo.setDepth(0L); //깊이 초기화
		commentvo.setOrderNo(1L); //순서 초기화
		
		//그룹아이디 부여
		if (commentRepo.findAll().isEmpty()) {
			commentvo.setGroupId(1L);
		}
		else {
			Long newGroupId = commentRepo.findTopByOrderByGroupIdDesc().get().getGroupId();
			commentvo.setGroupId(newGroupId+1);
		}
		
		commentRepo.save(commentvo);
	}
	//가장 마지막 순서를 구하기 위한 재귀함수
	public Optional<CommentVo> recurr(Optional<CommentVo> cocoPrev){
		Optional<CommentVo> cocoTemp = commentRepo.findTopByGroupIdAndDepthAndParentCommentIdOrderByOrderNoDesc(cocoPrev.get().getGroupId(),cocoPrev.get().getDepth()+1,cocoPrev.get().getCommentId());
		if (cocoTemp.isEmpty()) {
			return cocoPrev; 
		}
		return recurr(cocoTemp);
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
			System.out.println("empty");
		}
		else {
			Optional<CommentVo> prev = recurr(cocoPrev);
			currentOrderNo = prev.get().getOrderNo()+1;
			System.out.println("not empty");
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
	//순서에 맞게 모든 댓글 조회
	public List<CommentVo> getComment(Long boardId) {
		return commentRepo.findByBoardIdOrderByGroupIdAscOrderNoAsc(boardId);
	}
	//댓글 수정
	public void editComment(CommentVo commentvo) {
		CommentVo newComment = commentRepo.findById(commentvo.getCommentId()).get();
		newComment.setCommentContent(commentvo.getCommentContent());
		newComment.setVisible(commentvo.getVisible());
		commentRepo.save(newComment);
	}
}
