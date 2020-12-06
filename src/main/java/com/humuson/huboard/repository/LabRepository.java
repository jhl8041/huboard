package com.humuson.huboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.TagVo;

public interface LabRepository extends JpaRepository<TagVo, Long>{
	Optional<TagVo> findByUserNum(Long userNum);
}
