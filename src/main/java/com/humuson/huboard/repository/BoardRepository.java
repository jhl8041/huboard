package com.humuson.huboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.BoardVo;

public interface BoardRepository extends JpaRepository<BoardVo, Long> {

}
