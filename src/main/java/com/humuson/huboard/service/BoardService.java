package com.humuson.huboard.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	
	public Page<BoardVo> getPagingPost(Pageable pageable){
		Page<BoardVo> pager = boardRepo.findAll(pageable);
		List<BoardVo> paged_list = pager.getContent();
		System.out.println("총 페이지 수: " + pager.getTotalPages());
		System.out.println("총 게시글 수: " + pager.getTotalElements());
		System.out.println("페이지당 게시글 수: " + pager.getNumberOfElements());
		System.out.println("현재페이지: " + pager.getNumber());
		System.out.println("사이즈: " + pager.getSize());
		return pager;
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
