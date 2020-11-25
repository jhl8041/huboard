package com.humuson.huboard.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.service.MemberService;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Autowired
	private MemberService memberService;
	
	
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		String userId = request.getParameter("userId");
		MemberVo membervo = memberService.getMemberByUserId(userId);
		membervo.setFailCnt(0);
		membervo.setPassword("");
		try {
			memberService.editMember(membervo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setDefaultTargetUrl("/");
		super.onAuthenticationSuccess(request, response, chain, authentication);
	}

	
}
