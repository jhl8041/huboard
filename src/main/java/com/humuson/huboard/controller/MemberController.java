package com.humuson.huboard.controller;

import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	//회원가입 페이지 이동
	@GetMapping("/join")
	public String goMemberJoin() {
		return "member/memberJoin";
	}
	
	//회원가입 진행
	@PostMapping("/join")
	public String addMember(MemberVo membervo) throws Exception{
		memberService.addMember(membervo);
		return "member/memberLogin";
	}
	
	//회원탈퇴
	@PostMapping("/quit")
	@Transactional
	public String deleteMember(MemberVo membervo){
		memberService.deleteMember(membervo.getUserId());
		return "redirect:/logout";
	}
	
	//회원정보 수정 페이지 이동
	@GetMapping("/mypage")
	public String goEditMember(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("member",memberService.getMemberByUserId(user.getUsername()));
		return "member/memberEdit";
	}
	
	//회원정보 수정 진행
	@PostMapping("/mypage")
	public String doEditMember(MemberVo membervo) throws Exception {
		memberService.editMember(membervo);
		return "redirect:/home";
	}
	
	//로그인 페이지
	@GetMapping("/login")
	public String goLogin() {
		return "member/memberLogin";
	}
	
	//로그인 진행
	@PostMapping("/login")
	public String doLogin() {
		return "redirect:/loginer";
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String doLogout() {
		return "redirect:/login";
	}
	
	
}
