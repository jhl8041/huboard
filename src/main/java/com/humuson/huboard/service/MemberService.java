package com.humuson.huboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.humuson.huboard.model.EmailDto;
import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean findSameId(String userId) {
		return memberRepo.findByUserId(userId).isPresent();
	}
	
	public boolean findSameNickname(String nickname) {
		return memberRepo.findByUserId(nickname).isPresent();
	}
	
	public boolean findSameEmail(String email) {
		return memberRepo.findByEmail(email).isPresent();
	}
	
	public void mailSend(EmailDto mailDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getEmail());
        message.setFrom("jhl8041@naver.com");
        message.setSubject("[휴보드] 안녕하세요 '" + mailDto.getName() + "'님 회원가입 인증번호입니다");
        message.setText("인증번호: " + mailDto.getCode());
        mailSender.send(message);
    }
	
	public void addMember(MemberVo membervo) {
		memberRepo.save(new MemberVo(
				membervo.getUserId(),
				membervo.getPassword(),
				membervo.getUserName(),
				membervo.getNickname(),
				membervo.getPhone(),
				membervo.makeDate(membervo.getBirthyear(), membervo.getBirthmonth(), membervo.getBirthday()),
				membervo.getEmail(),
				membervo.getGender(),
				membervo.makeAddress(membervo.getRoadAddrPart1(), membervo.getAddrDetail())
				));
	}
}
