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
	
	@RequestMapping("/")
	public String main(Model model) {
		model.addAttribute("list",boardService.getAllPost());
		return "boardList"; 
	}
	
	@RequestMapping("/write")
	public String write_page() {
		return "writePage";
	}
	
	@GetMapping("/list")
	public String page_board(Model model,@PageableDefault(size=2, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("list",boardService.getPagingPost(pageable));
		return "boardList";
	}
	
	@GetMapping("/view")
	public String view_board(Model model, @RequestParam Long id) {
		model.addAttribute("post", boardService.getPost(id).get());
		return "boardView";
	}
	
	@GetMapping("/edit")
	public String edit_board(Model model, @RequestParam Long id) {
		model.addAttribute("post", boardService.getPost(id).get());
		return "boardEdit";
	}
	
	@GetMapping("/delete")
	public String delete_board(@RequestParam Long id) {
		boardService.deletePost(id);
		return "redirect:/";
	}
	
	@PostMapping("/proceed_edit")
	public String proceed_edit(BoardVo boardVo) {
		boardService.editPost(boardVo);
		return "redirect:/view?id="+ boardVo.getBoardId().toString();
	}
	
	@PostMapping("/add")
	public String submitForm(BoardVo boardVo){
		boardService.addPost(boardVo);
		return "redirect:/";
	}
}
