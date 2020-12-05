package com.humuson.huboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.repository.BoardRepository;
import com.humuson.huboard.repository.MemberRepository;

@Service
public class AdminService {
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	//카테고리별 게시글 수
	public int getBoardCntByCategoryId(Long categoryId) {
		return boardRepo.countByVisibleAndCategoryId("Y", categoryId).intValue();
	}
	
	//카테고리별 게시글 조회수
	public int getViewCntByCategoryId(Long categoryId) {
		int totalView = 0;
		List<BoardVo> boards = boardRepo.findByVisibleAndCategoryId("Y", categoryId);
		for (BoardVo b:boards) {
			totalView += b.getView();
		}
		return totalView;
	}
	
	//기간별 회원수
	public int getMemberCntBetween(java.util.Date yearMonth) {
		java.sql.Date startDate = new java.sql.Date(memberRepo.findTopByOrderByJoinDate().get().getJoinDate().getTime());
		java.sql.Date endDate = new java.sql.Date(yearMonth.getTime());
		return memberRepo.countByJoinDateBetween(startDate, endDate).intValue();
	}
	
	//성별별 회원수 조회
	public int getMemberCntGender(String gender) {
		return memberRepo.countByGender(gender).intValue();
	}
	
	
}
