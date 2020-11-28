package com.humuson.huboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.BoardVo;

public interface BoardRepository extends JpaRepository<BoardVo, Long> {
	public Page<BoardVo> findBySubjectContainingAndVisibleAndCategoryId(String subject, String visible, Long categoryId, Pageable pageable);
	public Page<BoardVo> findByContentContainingAndVisibleAndCategoryId(String content, String visible, Long categoryId, Pageable pageable);
	public Page<BoardVo> findByUserIdContainingAndVisibleAndCategoryId(String userId,  String visible, Long categoryId, Pageable pageable);
	
	public Page<BoardVo> findByVisibleAndCategoryId(String visible, Long categoryId, Pageable pageable);
	
	public List<BoardVo> findTop10ByCategoryId(Long categoryId);
	
	public Optional<BoardVo> findTopByOrderByBoardIdDesc();
}
