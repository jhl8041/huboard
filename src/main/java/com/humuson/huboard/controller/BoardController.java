package com.humuson.huboard.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.annotation.MultipartConfig;
import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.model.CommentVo;
import com.humuson.huboard.model.FileVo;
import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.repository.BoardRepository;
import com.humuson.huboard.service.BoardService;
import com.humuson.huboard.service.FileService;
import com.humuson.huboard.service.MemberService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private FileService fileService;
	
	//게시글 목록 조회 - R
	@GetMapping("/")
	public String boardHome(Model model, @PageableDefault(size=10, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable, 
			@AuthenticationPrincipal User user) {
	    
		Page<BoardVo> pager = boardService.getPagingPost("Y",pageable);
		model.addAttribute("list",pager);
		if (user != null)
			model.addAttribute("member",memberService.getMemberByUserId(user.getUsername()));
		return "board/boardList";
	}
	
	@GetMapping("/navbar")
	public String navBar(Model model, @AuthenticationPrincipal User user) {
		if (user != null)
			model.addAttribute("member",memberService.getMemberByUserId(user.getUsername()));
		return "share/navBar";
	}
	
	//게시글 에디터 - R
	@GetMapping("/editor/{boardId}")
	public String boardEditor(Model model, @AuthenticationPrincipal User user, @PathVariable Long boardId) {
		boolean data;
		if (boardId!=0) {
			System.out.println(boardService.getPost(boardId).get());
			System.out.println(boardService.getPost(boardId).get().getVisible());
			
			data = true;
			model.addAttribute("post", boardService.getPost(boardId).get());
		}
		else {
			data = false;
			model.addAttribute("post",boardService.addPost(new BoardVo(user.getUsername(), "N")));
		}
		model.addAttribute("files", fileService.getFiles(boardId));
		model.addAttribute("member", memberService.getMemberByUserId(user.getUsername()));
		model.addAttribute("data",data);
		return "board/boardEditor";
	}
		
	//게시글 등록 - C
	@PostMapping("/board")
	@ResponseBody
	public ResponseEntity<BoardVo> boardCreate(@RequestBody BoardVo boardVo) {
		ResponseEntity<BoardVo> status;
		try {
			boardVo.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
			boardVo.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
			boardVo.setVisible("Y");
			boardService.addPost(boardVo);
			System.out.println("add post end");
			status = new ResponseEntity<>(boardVo, HttpStatus.OK);
		}catch (Exception e){
			status = new ResponseEntity<>(boardVo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return status;
	}
	
	//게시글 보기 - R
	@GetMapping("/board/{boardId}")
	public String boardView(Model model, @PathVariable Long boardId, @AuthenticationPrincipal User user) {
		BoardVo post = boardService.getPost(boardId).get();
		post.setView(post.getView()+1); 
		boardService.addPost(post); //조회수 증가
		
		model.addAttribute("files", fileService.getFiles(boardId));
		model.addAttribute("post", post);
		model.addAttribute("member",memberService.getMemberByUserId(user.getUsername()));
		return "board/boardView";
	}
	
	//게시글 수정 - U
	@PatchMapping("/board/{boardId}")
	@ResponseBody
	public ResponseEntity<BoardVo> boardEdit(Model model, @PathVariable Long boardId, @RequestBody BoardVo boardVo) {
		ResponseEntity<BoardVo> status;
		try {
			BoardVo boardPrev = boardService.getPost(boardId).get();
			boardVo.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
			boardVo.setCreateDate(boardPrev.getCreateDate());
			boardVo.setVisible(boardPrev.getVisible());
			boardService.addPost(boardVo);
			status = new ResponseEntity<>(boardVo, HttpStatus.OK);
		}catch (Exception e){
			status = new ResponseEntity<>(boardVo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return status;
	}
	
	//게시글 삭제 - D
	@DeleteMapping("/board/{boardId}")
	@ResponseBody
	public ResponseEntity<Long> boardDelete(@PathVariable Long boardId) {
		ResponseEntity<Long> status;
		try {
			boardService.deletePost(boardId);
			status = new ResponseEntity<>(boardId, HttpStatus.OK);
		}catch (Exception e){
			status = new ResponseEntity<>(boardId, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return status;
	}
	
	//게시글 검색 - R
	@GetMapping("/search/{keyword}/{searchType}")
	public String doSearch(Model model, @PathVariable String keyword, @PathVariable String searchType,
			@PageableDefault(size=5, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable){
		model.addAttribute("list",boardService.findPostBySearch(keyword, pageable, searchType));
		return "board/boardList";
	}
	
	//파일아이디 가져오기
	@PostMapping("/filename/{fileName}")
	@ResponseBody
	public Long getFileId(@PathVariable String fileName) {
		return fileService.getFileId(fileName);
	}
	
}
