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
	//public Optional<CommentVo> findTopByGroupIdAndOrderNo(Long groupId, Long orderNo);
	public Optional<CommentVo> findTopByGroupIdAndOrderNoAndDepth(Long groupId, Long orderNo, Long depth);
	public Optional<CommentVo> findTopByGroupIdOrderByOrderNoDesc(Long groupId);
	
	@Query(value="SELECT * FROM CommentVo c WHERE group_id LIKE ?1 ORDER BY order_no LIMIT ?2,?3", nativeQuery=true)
	public List<CommentVo> findByGroupIdOrderAndMore(Long groupId, Long limitStart, Long limitEnd);
}
