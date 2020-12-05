package com.humuson.huboard.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humuson.huboard.model.MemberVo;

public interface MemberRepository extends JpaRepository<MemberVo, Long>{
	//아이디 별 회원 조회
	public Optional<MemberVo> findByUserId(String userId);
	
	//닉네임 별 회원 조회
	public Optional<MemberVo> findByNickname(String nickname);
	
	//이메일 별 회원 조회
	public Optional<MemberVo> findByEmail(String email);
	
	//잠김회원 조회
	public List<MemberVo> findByIsLocked(String isLocked);
	
	//회원삭제
	public void deleteByUserId(String userId);
	
	
	//가장 오래된 회원 조회
	public Optional<MemberVo> findTopByOrderByJoinDate();
	
	//기간별 회원수 조회
	public Long countByJoinDateBetween(Date start, Date end);
	
	//성별 별 회원수 조회
	public Long countByGender(String gender);
	
	
}
