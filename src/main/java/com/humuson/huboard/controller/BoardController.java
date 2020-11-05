package com.humuson.huboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.repository.BoardRepository;
import com.humuson.huboard.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	BoardRepository boardRepo;
	
	@RequestMapping("/write")
	public String write_page() {
		return "board/boardWrite";
	}
	
	//게시글 목록 조회
	@GetMapping("/")
	public String page_board(Model model,@PageableDefault(size=5, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("list",boardService.getPagingPost(pageable));
		return "board/boardList";
	}
	
	//게시글 추가
	@PostMapping("/add")
	public String submitForm(BoardVo boardVo){
		boardService.addPost(boardVo);
		return "redirect:/";
	}
	
	//게시글 조회
	@GetMapping("/view")
	public String view_board(Model model, @RequestParam Long id) {
		model.addAttribute("post", boardService.getPost(id).get());
		return "board/boardView";
	}
	
	//게시글 수정페이지로 이동
	@GetMapping("/edit")
	public String edit_board(Model model, @RequestParam Long id) {
		model.addAttribute("post", boardService.getPost(id).get());
		return "board/boardEdit";
	}
	
	//게시글 수정
	@PostMapping("/proceed_edit")
	public String proceed_edit(BoardVo boardVo) {
		boardService.editPost(boardVo);
		return "redirect:/view?id="+ boardVo.getBoardId().toString();
	}
	
	//게시글 삭제
	@GetMapping("/delete")
	public String delete_board(@RequestParam Long id) {
		boardService.deletePost(id);
		return "redirect:/";
	}
	
	//게시글 검색
	@GetMapping("/search")
	public String search(Model model, @RequestParam String keyword, @RequestParam String search_type,
			@PageableDefault(size=5, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable){
		model.addAttribute("list",boardService.findPostBySearch(keyword, pageable, search_type));
		return "boardList";
	}
	
	
}
