package com.humuson.huboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.repository.MemberRepository;
import com.humuson.huboard.service.MemberService;


@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	MemberRepository memberRepo;
	
	@PostMapping("/idcheck")
	@ResponseBody
	public String idCheck(@RequestBody String userId) {
		String id = userId.substring(7);
		
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
}
