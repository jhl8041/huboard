package com.humuson.huboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.repository.BoardRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepo;
	
	public List<BoardVo> getAllPost(){
		return boardRepo.findAll();
	}
	
	public List<BoardVo> getPagingPost(Model model,@PageableDefault(size=5, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable){
		Page<BoardVo> pager = boardRepo.findAll(pageable);
		List<BoardVo> paged_list = pager.getContent();
		return paged_list;
	}
	
	public Optional<BoardVo> getPost(Long id){
		return boardRepo.findById(id);
	}
	
	public void deletePost(Long id){
		boardRepo.deleteById(id);
	}
	
	public void editPost(BoardVo boardVo){
		boardRepo.save(boardVo);
	}
	
	public void addPost(BoardVo boardVo){
		boardRepo.save(boardVo);
	}
}
