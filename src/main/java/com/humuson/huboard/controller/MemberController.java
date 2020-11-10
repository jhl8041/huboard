package com.humuson.huboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.model.EmailDto;
import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.repository.MemberRepository;
import com.humuson.huboard.service.MemberService;


@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	MemberRepository memberRepo;
	
	//아이디 유효성 검사 및 중복검사 ("/idcheck")
	@PostMapping("/idcheck")
	@ResponseBody
	public String idCheck(@RequestBody MemberVo membervo) {
		String id = membervo.getUserId();
		
		String idPattern = "^[a-z0-9]{6,}$";
        Matcher matcher = Pattern.compile(idPattern).matcher(id);       
        
        String idResult=null;
        if (id.equals(""))
        	idResult = "empty";
        else {
        	if (id.length()<6)
        		idResult = "short";
        	
        	else if (!matcher.matches())
        		idResult = "wrongChar";
        	
        	else if (memberService.findSameId(id))
        		idResult = "notunique";
    	
            else 
            	idResult = "unique";
        }
        
		return idResult;
	}
	
	//비밀번호 유효성 검사
	@PostMapping("/pwdcheck")
	@ResponseBody
	public String pwdCheck(@RequestBody MemberVo membervo) {
		String id = membervo.getUserId();
		String pwd = membervo.getPassword();
		
	    String pwPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,}$";
	    Matcher matcher = Pattern.compile(pwPattern).matcher(pwd);
	    
	    pwPattern = "(.)\\1\\1\\1";
	    Matcher matcher2 = Pattern.compile(pwPattern).matcher(pwd);
	    
	    pwPattern = "^(?=.*[a-z]).{8,}$";
	    Matcher strength1_1 = Pattern.compile(pwPattern).matcher(pwd);
	    
	    pwPattern = "^(?=.*[0-9]).{8,}$";
	    Matcher strength1_2 = Pattern.compile(pwPattern).matcher(pwd);
	    
	    pwPattern = "^(?=.*[a-z])(?=.*[0-9]).{8,}$";
	    Matcher strength2 = Pattern.compile(pwPattern).matcher(pwd);
	    
	    pwPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$";
	    Matcher strength3_1 = Pattern.compile(pwPattern).matcher(pwd);
	    
	    pwPattern = "^(?=.*[a-z])(?=.*[$@$!%*#?&])(?=.*[0-9]).{8,}$";
	    Matcher strength3_2 = Pattern.compile(pwPattern).matcher(pwd);
	   
	    String result = null;
	    if (pwd.length()>7) {
	    	if(matcher2.find())
	        	result="fourChar";
	        
	        // 비밀번호에 아이디가 포함되어있을때 
	        else if(pwd.contains(id) && !id.equals(""))
	        	result="includeId";
	        
	        // 비밀번호에 공백이 포함되어있을때 
	        else if(pwd.contains(" "))
	        	result="includeSpace";
	    	
	        else if(matcher.matches()) 
	    		result="strength4"; 
	    	
	        else if(strength3_1.matches() || strength3_2.matches()) 
	    		result="strength3";
	    	
	        else if(strength2.matches())
	    		result="strength2";
	    	
	        else if(strength1_1.matches() || strength1_2.matches())
	    		result="strength1";
	           	
	    }
	    else {
	    	if(pwd.equals(""))
	    		result = "empty";
	    	else
	    		result = "digit";
	    }
	 		
		return result;
	}
	
	//닉네임 중복 검사
	@PostMapping("/nicknamecheck")
	@ResponseBody
	public String nicknameCheck(@RequestBody MemberVo membervo) {
		String nickname = membervo.getNickname();
      
        String nicknamecheck=null;
        if (nickname.equals(""))
        	nicknamecheck = "empty";
        else {
        	if (memberService.findSameNickname(nickname))
        		nicknamecheck = "notunique";
    	
            else 
            	nicknamecheck = "unique";
        }
        
        return nicknamecheck;
	}
	
	//도로명주소 API
	@RequestMapping("/addressPop")
	public String addressPop() {
		return "member/addressPop";
	}
	
	@PostMapping("/addressDo")
	public String addressDo() {
		return "redirect: http://www.juso.go.kr/addrlink/addrLinkUrl.do";
	}
	
	//이메일 중복 검사
	@PostMapping("/emailcheck")
	@ResponseBody
	public String emailCheck(@RequestBody MemberVo membervo) {
		String email = membervo.getEmail();
      
        String emailcheck=null;
        if (email.equals(""))
        	emailcheck = "empty";
        else {
        	if (memberService.findSameEmail(email))
        		emailcheck = "notunique";
    	
            else 
            	emailcheck = "unique";
        }
        
        return emailcheck;
	}
	
	//이메일 전송
	@PostMapping("/doSendEmail")
	@ResponseBody
	public String sendEmail(@RequestBody EmailDto dto) {
		memberService.mailSend(dto);
		return "success";
	}
	
	//회원가입 페이지 이동
	@GetMapping("/goJoin")
	public String goJoin() {
		return "member/memberJoin";
	}
	
	//회원가입 등록
	@PostMapping("/doJoin")
	public String submitForm(MemberVo membervo){
		memberService.addMember(membervo);
		return "member/memberLogin";
	}
	
	//로그인 페이지 이동
	@GetMapping("/goLogin")
	public String goLogin() {
		return "member/memberLogin";
	}
	
	//로그인 진행
	@GetMapping("/doLogin")
	public String doLogin() {
		return "redirect:/";
	}
	
	
	//로그아웃
	@PostMapping("/doLogout")
	public String doLogout() {
		return "member/memberLogin";
	}
	
}
