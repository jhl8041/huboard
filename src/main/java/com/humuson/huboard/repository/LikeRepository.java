package com.humuson.huboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.LikeVo;

public interface LikeRepository extends JpaRepository<LikeVo, Long>{
	List<LikeVo> findByBoardIdAndUserNum(Long boardId, Long userNum);
	List<LikeVo> findByBoardId(Long boardId);
	void deleteByBoardIdAndUserNum(Long boardId, Long userNum);
}
