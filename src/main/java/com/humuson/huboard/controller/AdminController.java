package com.humuson.huboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.model.CatRowColDto;
import com.humuson.huboard.model.CategoryVo;
import com.humuson.huboard.service.CategoryService;
import com.humuson.huboard.service.MemberService;

@Controller
public class AdminController {
	@Autowired
	MemberService memberService;
	
	@Autowired
	CategoryService categoryService;
	
	//상단고정 메뉴바
	@GetMapping("/navbaradmin")
	public String navBar(Model model, @AuthenticationPrincipal User user) {
		if (user != null)
			model.addAttribute("member",memberService.getMemberByUserId(user.getUsername()));
		return "share/navBarAdmin";
	}
	
	//관리자 컨틀롤 - R
	@GetMapping("/admin/admincontrol")
	public String adminControl(Model model, @AuthenticationPrincipal User user) {
		List<CategoryVo> category = categoryService.getAllCategory();
		
		//카테고리 목록 전송
		model.addAttribute("category", category);
		
		//로그인했을때만 계정정보 전송
		if (user != null) model.addAttribute("member", memberService.getMemberByUserId(user.getUsername()));
			
		return "admin/adminControl";
	}
	
	//카테고리 추가 - C
	@PostMapping("/category")
	public String addCategory(CategoryVo categoryvo) {
		categoryService.addCategory(categoryvo);	
		return "redirect:/admincontrol";
	}
	
	//카테고리 수정 - U
	@PostMapping("/category/{categoryId}")
	@ResponseBody
	public String editCategory(@RequestBody CategoryVo categoryvo, @PathVariable Long categoryId) {
		categoryvo.setCategoryId(categoryId);
		categoryService.addCategory(categoryvo);	
		return "success";
	}
	
	//카테고리 제거 - D
	@GetMapping("/category/{categoryId}")
	public String delCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategory(categoryId);	
		return "redirect:/admincontrol";
	}
	
	
}
