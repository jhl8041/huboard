package com.humuson.huboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.BoardVo;

public interface BoardRepository extends JpaRepository<BoardVo, Long> {
	//게시글 검색
	public Page<BoardVo> findBySubjectContainingAndVisibleAndCategoryId(String subject, String visible, Long categoryId, Pageable pageable);
	public Page<BoardVo> findByContentContainingAndVisibleAndCategoryId(String content, String visible, Long categoryId, Pageable pageable);
	public Page<BoardVo> findByNicknameContainingAndVisibleAndCategoryId(String nickname,  String visible, Long categoryId, Pageable pageable);
	
	//카테고리별 게시글 목록 조회
	public Page<BoardVo> findByVisibleAndCategoryId(String visible, Long categoryId, Pageable pageable);
	
	//카테고리별 게시글 상위 10개 조회
	public List<BoardVo> findTop10ByCategoryIdOrderByBoardIdDesc(Long categoryId);
	
	public List<BoardVo> findByVisibleAndCategoryId(String visible, Long categoryId);
	public Long countByVisibleAndCategoryId(String visible, Long categoryId);
	
	//추천게시글 검색
	public List<BoardVo> findTop10BySubjectContainingOrSubjectContainingOrSubjectContainingAndVisibleOrderByLikeCntDescViewDescUpdateDateDesc(String keyword1, String keyword2, String keyword3, String visible);

}
