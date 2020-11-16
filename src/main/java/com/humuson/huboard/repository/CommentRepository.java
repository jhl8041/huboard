package com.humuson.huboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.humuson.huboard.model.CommentVo;

public interface CommentRepository extends JpaRepository<CommentVo, Long> {
	public List<CommentVo> findByBoardId(Long boardId);
	public List<CommentVo> findByBoardIdOrderByGroupIdAscOrderNoAsc(Long boardId);
	
	//댓글,대댓글용
	public Optional<CommentVo> findTopByOrderByGroupIdDesc();
	public Optional<CommentVo> findTopByGroupIdOrderByOrderNoDesc(Long groupId);
	public Optional<CommentVo> findTopByGroupIdAndDepthAndParentCommentIdOrderByOrderNoDesc(Long groupId, Long depth, Long parentCommentId);	
	
	public Optional<CommentVo> findTopByGroupIdAndDepthGreaterThanEqualOrderByOrderNoDesc(Long groupId, Long depth);
	public List<CommentVo> findByGroupIdAndOrderNoGreaterThanEqual(Long groupId, Long length);
	
}
