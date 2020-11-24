package com.humuson.huboard.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.humuson.huboard.model.CommentVo;
import com.humuson.huboard.model.EmailDto;
import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.repository.CommentRepository;
import com.humuson.huboard.repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
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
        message.setSubject("[휴보드] 안녕하세요 회원가입 인증번호입니다");
        message.setText("인증번호: " + mailDto.getCode());
        mailSender.send(message);
    }
	
	public void addMember(MemberVo membervo) throws Exception {
		
		String encryptPw = BCrypt.hashpw(membervo.getPassword(), BCrypt.gensalt());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date birthDateStr = sdf.parse(membervo.getBirthDateStr());
		java.sql.Date birthDate = new java.sql.Date(birthDateStr.getTime());
		
		memberRepo.save(new MemberVo(
				null,
				membervo.getUserId(),
				encryptPw,
				membervo.getUserName(),
				membervo.getNickname(),
				membervo.getPhone(),
				birthDate,
				membervo.getEmail(),
				membervo.getGender(),
				membervo.getRoadAddr(), 
				membervo.getDetailAddr(),
				Timestamp.valueOf(LocalDateTime.now())
				));
	}
	
	public void editMember(MemberVo membervo) throws Exception {
		MemberVo newMember = memberRepo.findByUserId(membervo.getUserId()).get();
		
		if (!membervo.getPassword().isEmpty()) {
			newMember.setPassword(BCrypt.hashpw(membervo.getPassword(), BCrypt.gensalt()));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date birthDateStr = sdf.parse(membervo.getBirthDateStr());
		java.sql.Date birthDate = new java.sql.Date(birthDateStr.getTime());
		
		newMember.setBirthDate(birthDate);
		
		newMember.setUserName(membervo.getUserName());
		newMember.setGender(membervo.getGender());
		newMember.setRoadAddr(membervo.getRoadAddr());
		newMember.setDetailAddr(membervo.getDetailAddr());
		newMember.setPhone(membervo.getPhone());
		
		memberRepo.save(newMember);
	}
	
	public void deleteMember(String userId) {
		List<CommentVo> commentList = commentRepo.findByUserId(userId);
		
		for (CommentVo c:commentList) {
			c.setVisible("N");
			c.setUserId(userId + "(탈퇴한 회원)");
			commentRepo.save(c);
		}
		
		memberRepo.deleteByUserId(userId);
	}
	
	public MemberVo getMemberByUserId(String userId) {
		return memberRepo.findByUserId(userId).get();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		MemberVo member = memberRepo.findByUserId(username).get();
		String role = member.getAuth();
		
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(role));

		return new User(member.getUserId(), member.getPassword(), auth);
	}
	
}
