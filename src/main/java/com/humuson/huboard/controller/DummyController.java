package com.humuson.huboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.repository.BoardRepository;
import com.humuson.huboard.service.BoardService;

@RestController
public class DummyController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	BoardRepository boardRepo;
	
	@GetMapping("/dummy/user")
	public List<BoardVo> pageList(Model model,@PageableDefault(size=5, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable){
		Page<BoardVo> pager = boardRepo.findAll(pageable);
		List<BoardVo> users = pager.getContent();
		return users;
	}
}
