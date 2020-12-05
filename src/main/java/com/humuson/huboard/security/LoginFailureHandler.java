package com.humuson.huboard.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.service.MemberService;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
	private MemberService memberService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String userId = request.getParameter("userId");
		String error="";
		
		if (exception instanceof InternalAuthenticationServiceException) {
			//존재하지 않는 사용자입니다
			exception = new InternalAuthenticationServiceException("존재하지 않는 사용자입니다");
		}
		else if (exception instanceof AuthenticationServiceException) {
			//존재하지 않는 사용자입니다
			exception = new AuthenticationServiceException("존재하지 않는 사용자입니다");
		}
		else if(exception instanceof BadCredentialsException) {
			//비밀번호가 틀립니다
			MemberVo membervo = memberService.getMemberByUserId(userId);
			membervo.setFailCnt(membervo.getFailCnt() + 1);
			membervo.setPassword("");
			try {
				memberService.editMember(membervo);
				System.out.println("failcnt: " + membervo.getFailCnt());
			} catch (Exception e) {
				e.printStackTrace();
			}
			exception = new BadCredentialsException("비밀번호가 틀립니다 남은시도횟수: "+ (6 - membervo.getFailCnt()));
		}
		else if(exception instanceof LockedException) {
			exception = new LockedException("계정이 잠겼습니다. 관리자에게 문의해주세요");
		}
		super.setDefaultFailureUrl("/login?error="+error);
		super.onAuthenticationFailure(request, response, exception);
	}
	
}
