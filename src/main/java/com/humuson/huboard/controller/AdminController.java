package com.humuson.huboard.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.humuson.huboard.model.MemberLineDto;
import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.model.ViewBarDto;
import com.humuson.huboard.service.BoardService;
import com.humuson.huboard.service.CategoryService;
import com.humuson.huboard.service.MemberService;

@Controller
public class AdminController {
	@Autowired
	MemberService memberService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BoardService boardService;
	
	//상단고정 메뉴바
	@GetMapping("/navbaradmin")
	public String navBar(Model model, @AuthenticationPrincipal User user) {
		if (user != null)
			model.addAttribute("member",memberService.getMemberByUserId(user.getUsername()));
		return "share/navBarAdmin";
	}
	
	@GetMapping("/admin/dashboard")
	public String adminDashboard(Model model) {
		
		
		return "admin/adminDashboard";
	}
	
	public static Date addMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }
	
	//월별 회원가입수 라인 그래프
	@PostMapping("/admin/memberline")
	@ResponseBody
	public List<MemberLineDto> adminMemberLine() throws ParseException {
		List<MemberLineDto> memberLineDtoList = new ArrayList<>();
		
		System.out.println("testing");
		
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");

		String startDateStr = formatDate.format(memberService.getFirstDate());
		Date startDate = formatDate.parse(startDateStr);
		
		String endDateStr = formatDate.format(new Date());
		Date endDate = formatDate.parse(endDateStr);
				
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.add(Calendar.MONTH,1);
		
		for (Date date = start.getTime(); start.before(end); start.add(Calendar.MONTH,1), date=start.getTime()) {	
			Calendar dater = Calendar.getInstance();
			dater.setTime(date);
			dater.add(Calendar.MONTH,1);
			
			int memberCnt = memberService.getMemberCntBetween(dater.getTime());
			memberLineDtoList.add(new MemberLineDto(formatDate.format(date), memberCnt));	
		}
				
		return memberLineDtoList;
	}
		
	
	//카테고리별 조회수 바 그래프
	@PostMapping("/admin/viewbar")
	@ResponseBody
	public List<ViewBarDto> adminViewBar() {
		List<ViewBarDto> viewbardtolist = new ArrayList<>();
		List<CategoryVo> category = categoryService.getAllCategory();
		
		for (CategoryVo c: category) {
			String catName = c.getCategoryName();
			int viewCnt = boardService.getViewCntByCategoryId(c.getCategoryId());
			viewbardtolist.add(new ViewBarDto(catName, viewCnt));
		}
		
		return viewbardtolist;
	}
	
	//관리자 관리 페이지 - R
	@GetMapping("/admin/admincontrol")
	public String adminControl(Model model, @AuthenticationPrincipal User user) {
		List<CategoryVo> category = categoryService.getAllCategory();
		List<MemberVo> member = memberService.getLockedMember();
		
		//카테고리 목록 전송
		model.addAttribute("category", category);
		model.addAttribute("lockedMember", member);
		
		//로그인했을때만 계정정보 전송
		if (user != null) model.addAttribute("member", memberService.getMemberByUserId(user.getUsername()));
			
		return "admin/adminControl";
	}
	
	//카테고리 추가 - C
	@PostMapping("admin/category")
	public String addCategory(CategoryVo categoryvo) {
		categoryService.addCategory(categoryvo);	
		return "redirect:/admin/admincontrol";
	}
	
	//카테고리 수정 - U
	@PostMapping("admin/category/{categoryId}")
	@ResponseBody
	public String editCategory(@RequestBody CategoryVo categoryvo, @PathVariable Long categoryId) {
		categoryvo.setCategoryId(categoryId);
		categoryService.addCategory(categoryvo);	
		return "success";
	}
	
	//카테고리 제거 - D
	@GetMapping("admin/category/{categoryId}")
	public String delCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategory(categoryId);	
		return "redirect:/admin/admincontrol";
	}
	
	//잠긴 사용자 해제
	@GetMapping("admin/unlock/{userNum}")
	public String findLockedMember(MemberVo membervo, @PathVariable Long userNum) throws Exception {
		MemberVo member = memberService.getMemberByUserNum(userNum);
		member.setFailCnt(0);
		member.setIsLocked("N");
		memberService.unlockMember(member);	
		return "redirect:/admin/admincontrol";
	}
	
	
}
