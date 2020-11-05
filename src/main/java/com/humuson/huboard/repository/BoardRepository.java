package com.humuson.huboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.BoardVo;

public interface BoardRepository extends JpaRepository<BoardVo, Long> {
	public Page<BoardVo> findBySubject(String subject, Pageable pageable);
}
