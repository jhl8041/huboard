package com.humuson.huboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.MemberVo;

public interface MemberRepository extends JpaRepository<MemberVo, Long>{
	public Optional<MemberVo> findByUserId(String userId);
}
