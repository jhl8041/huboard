package com.humuson.huboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.CategoryVo;

public interface CategoryRepository extends JpaRepository<CategoryVo, Long> {
	
}
