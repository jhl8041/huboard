package com.humuson.huboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.FileVo;

public interface FileRepository extends JpaRepository<FileVo, Long>{
	List<FileVo> findByOriginFileName(String originFileName);
	
}
