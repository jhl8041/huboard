package com.humuson.huboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.model.CommentVo;
import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.repository.BoardRepository;
import com.humuson.huboard.service.BoardService;
import com.humuson.huboard.service.MemberService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	BoardRepository boardRepo;
	
	
	//게시글 목록 조회
	@GetMapping("/")
	public String page_board(Model model,@PageableDefault(size=5, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal User user) {
		model.addAttribute("list",boardService.getPagingPost(pageable));
		model.addAttribute("member",memberService.getMemberByUserId(user.getUsername()));
		return "board/boardList";
	}
	
	//게시글 쓰기페이지 이동
	@GetMapping("/goCreate")
	public String goCreate(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("member", memberService.getMemberByUserId(user.getUsername()));
		return "board/boardWrite";
	}
	
	//게시글 쓰기
	@PostMapping("/doCreate")
	public String doCreate(BoardVo boardVo){
		boardService.addPost(boardVo);
		return "redirect:/";
	}
	
	//게시글 보기페이지 이동
	@GetMapping("/goView")
	public String goView(Model model, @RequestParam Long id, @AuthenticationPrincipal User user) {
		model.addAttribute("post", boardService.getPost(id).get());
		model.addAttribute("member",memberService.getMemberByUserId(user.getUsername()));
		return "board/boardView";
	}
	
	//게시글 수정페이지 이동
	@GetMapping("/goEdit")
	public String goEdit(Model model, @RequestParam Long id) {
		model.addAttribute("post", boardService.getPost(id).get());
		return "board/boardEdit";
	}
	
	//게시글 수정
	@PostMapping("/doEdit")
	public String doEdit(BoardVo boardVo) {
		boardService.editPost(boardVo);
		return "redirect:/goView?id="+ boardVo.getBoardId().toString();
	}
	
	//게시글 삭제
	@GetMapping("/doDelete")
	public String doDelete(@RequestParam Long id) {
		boardService.deletePost(id);
		return "redirect:/";
	}
	
	//게시글 검색
	@GetMapping("/doSearch")
	public String doSearch(Model model, @RequestParam String keyword, @RequestParam String search_type,
			@PageableDefault(size=5, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable){
		model.addAttribute("list",boardService.findPostBySearch(keyword, pageable, search_type));
		return "board/boardList";
	}
	
	//댓글 등록
	@PostMapping("/doComment")
	@ResponseBody
	public List<CommentVo> doComment(@RequestBody CommentVo commentvo) {
		boardService.addComment(commentvo);
		return boardService.getComment(commentvo.getBoardId());
	}
	
	@PostMapping("/getComment")
	@ResponseBody
	public List<CommentVo> getComment(@RequestBody CommentVo commentvo) {
		return boardService.getComment(commentvo.getBoardId());
	}
	
}
