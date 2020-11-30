package com.humuson.huboard.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.MemberVo;

public interface MemberRepository extends JpaRepository<MemberVo, Long>{
	public Optional<MemberVo> findByUserId(String userId);
	public Optional<MemberVo> findByNickname(String nickname);
	public Optional<MemberVo> findByEmail(String email);
	public List<MemberVo> findByIsLocked(String isLocked);
	
	public Optional<MemberVo> findTopByOrderByJoinDate();
	public List<MemberVo> findByJoinDateBetween(Date start, Date end);
	
	public void deleteByUserId(String userId);
}
