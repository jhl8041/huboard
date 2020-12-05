package com.humuson.huboard.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.service.MemberService;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	@Autowired
	private MemberService memberService;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("login Success!");
		String userId = request.getParameter("userId");
		MemberVo membervo = memberService.getMemberByUserId(userId);
		membervo.setFailCnt(0);
		membervo.setPassword("");
		try {
			memberService.editMember(membervo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultRedirectStrategy(request, response, authentication);

	}
	
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        
        String userId = request.getParameter("userId");
        MemberVo membervo = memberService.getMemberByUserId(userId);
        String role = membervo.getAuth();
        
        if(savedRequest!=null && role.equals("ROLE_MEMBER")) {
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStratgy.sendRedirect(request, response, targetUrl);
        } 
        else if (role.equals("ROLE_ADMIN")) {
            redirectStratgy.sendRedirect(request, response, "/admin/dashboard");
        }
        else{
            redirectStratgy.sendRedirect(request, response, "/home");
        }
        
        
        
    }

}
