package com.humuson.huboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.BoardVo;

public interface BoardRepository extends JpaRepository<BoardVo, Long> {
	public Page<BoardVo> findBySubjectContainingAndCategoryAndVisible(String subject, String category, String visible, Pageable pageable);
	public Page<BoardVo> findByContentContainingAndCategoryAndVisible(String content, String category, String visible, Pageable pageable);
	public Page<BoardVo> findByUserIdContainingAndCategoryAndVisible(String userId, String category, String visible, Pageable pageable);
	
	public Page<BoardVo> findByCategoryAndVisible(String category, String visible, Pageable pageable);
	
	public Optional<BoardVo> findTopByOrderByBoardIdDesc();
	//public Page<BoardVo> findByVisible(String visible, Pageable pageable);
}
