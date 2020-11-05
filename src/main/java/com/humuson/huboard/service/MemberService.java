package com.humuson.huboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humuson.huboard.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	public boolean findSameId(String userId) {
		return memberRepo.findByUserId(userId).isPresent();
	}
}
