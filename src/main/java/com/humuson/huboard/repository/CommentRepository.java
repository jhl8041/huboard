package com.humuson.huboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.CommentVo;

public interface CommentRepository extends JpaRepository<CommentVo, Long> {
	public List<CommentVo> findByBoardId(Long boardId);
}
