package com.humuson.huboard.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.model.CategoryVo;
import com.humuson.huboard.model.LikeVo;
import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.model.dto.BoardPageDto;
import com.humuson.huboard.model.dto.CatRowColDto;
import com.humuson.huboard.service.BoardService;
import com.humuson.huboard.service.CategoryService;
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
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	
	//상단고정 메뉴바
	@GetMapping("/navbar")
	public String navBar(Model model, @AuthenticationPrincipal User user) {
		if (user != null)
			model.addAttribute("member",memberService.getMemberByUserId(user.getUsername()));
		return "share/navBar";
	}
	
	//Footer
	@GetMapping("/footer")
	public String footer() {
		return "share/footer";
	}
	
	//게시글 홈페이지 - R
	@GetMapping("/home")
	public String boardHome(Model model, @PageableDefault(size=10, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable, 
			@AuthenticationPrincipal User user) {
		
		
		MemberVo member;
		List<BoardVo> recommList = new ArrayList<>();;
		
		if (user != null) {
			member= memberService.getMemberByUserId(user.getUsername());
			recommList = boardService.getRecommPost(member.getUserNum());
		}
		
		List<List<BoardVo>> catList = new ArrayList<>();
		List<CategoryVo> category = categoryService.getAllCategory();
		
		int catSize = category.size();
		int catCol = 2;
		int catRow = (catSize/catCol) + 1;
		
		for (CategoryVo c:category) {
			catList.add(boardService.getTopTen(c.getCategoryId()));
		}
		
		CatRowColDto catRowCol = new CatRowColDto(catList, catRow, catCol);
		
		if (user != null) model.addAttribute("recommList", recommList);
		model.addAttribute("category", category);
		model.addAttribute("catRowCol",catRowCol);
		return "board/home";
	}
		
	//게시글 목록 조회 - R
	@GetMapping("/cat/{categoryId}")
	public String boardCategory(Model model, @PageableDefault(size=10, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable, 
			@AuthenticationPrincipal User user, @PathVariable Long categoryId) {
		Page<BoardVo> pager = boardService.getPagingPost(categoryId, pageable);
		CategoryVo category = categoryService.getCategory(categoryId);
			
		//페이지 블록 사이즈 설정
		int blockLen = 5;
		int currBlock = pager.getNumber()/blockLen;
		int blockStart = currBlock*blockLen;
		int blockEnd = ((blockStart + blockLen - 1) >= pager.getTotalPages()) ? (pager.getTotalPages()-1) : (blockStart + blockLen - 1);
				
		BoardPageDto boardList = new BoardPageDto(
				pager.getContent(),
				pager.getNumber(),
				pager.getSize(),
				pager.getTotalElements(),
				pager.getTotalPages(),
				blockStart,
				blockEnd
				);
		
		//게시물 목록 및 카테고리 전송
		model.addAttribute("pageInfo",boardList);
		model.addAttribute("category", category);
		
		//로그인했을때만 계정정보 전송
		if (user != null) model.addAttribute("member", memberService.getMemberByUserId(user.getUsername()));
		
		return "board/boardList";
	}
	
	
	//게시글 에디터 - R
	@GetMapping("/editor/{boardId}/{categoryId}")
	public String boardEditor(Model model, @AuthenticationPrincipal User user, @PathVariable Long boardId,
			@PathVariable Long categoryId) {
		boolean data;
		if (boardId!=0) {
			data = true;
			model.addAttribute("post", boardService.getPost(boardId).get());
		}
		else {
			data = false;
			model.addAttribute("post",boardService.addPost(new BoardVo(user.getUsername(), "N")));
		}
		
		List<CategoryVo> allcategory = categoryService.getAllCategory();
		
		model.addAttribute("allcategory", allcategory);
		model.addAttribute("currCategoryId", categoryId);
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
		
		CategoryVo category = categoryService.getCategory(post.getCategoryId());
		MemberVo member = memberService.getMemberByUserId(user.getUsername());
		
		boolean ifLike = boardService.getIfILike(boardId, member.getUserNum());
		System.out.println(ifLike);
		
		model.addAttribute("ifLike", ifLike);
		model.addAttribute("files", fileService.getFiles(boardId));
		model.addAttribute("post", post);
		model.addAttribute("member",member);
		model.addAttribute("category", category);
		model.addAttribute("likeCnt", boardService.getLikeCntByBoardId(boardId));
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
	@GetMapping("/search/{categoryId}/{keyword}/{searchType}")
	public String doSearch(Model model, @PathVariable String keyword, @PathVariable String searchType, @PathVariable Long categoryId,
			@PageableDefault(size=10, sort="boardId", direction=Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal User user){
		
		CategoryVo category = categoryService.getCategory(categoryId);
		Page<BoardVo> pager = boardService.findPostBySearch(keyword, categoryId, pageable, searchType);
			
		//페이지 블록 사이즈 설정
		int blockLen = 5;
		int currBlock = pager.getNumber()/blockLen;
		int blockStart = currBlock*blockLen;
		int blockEnd = ((blockStart + blockLen - 1) >= pager.getTotalPages()) ? (pager.getTotalPages()-1) : (blockStart + blockLen - 1);
				
		BoardPageDto boardList = new BoardPageDto(
				pager.getContent(),
				pager.getNumber(),
				pager.getSize(),
				pager.getTotalElements(),
				pager.getTotalPages(),
				blockStart,
				blockEnd
				);
		
		//게시물 목록 및 카테고리 전송
		model.addAttribute("pageInfo",boardList);
		model.addAttribute("category", category);
		model.addAttribute("keyword",keyword);
		
		//로그인했을때만 계정정보 전송
		if (user != null) model.addAttribute("member", memberService.getMemberByUserId(user.getUsername()));
		
		
		return "board/boardList";
	}
	
	
	//좋아요 활성화
	@PostMapping("/likepost")
	@ResponseBody
	public int likePost(@RequestBody LikeVo likevo) {
		boardService.addLike(likevo);
		int likeCnt = boardService.getLikeCntByBoardId(likevo.getBoardId());
		BoardVo board = boardService.getPost(likevo.getBoardId()).get();
		board.setLikeCnt(likeCnt);
		boardService.addPost(board);
		return likeCnt;
	}
	
	//좋아요 해제
	@Transactional
	@PostMapping("/unlikepost")
	@ResponseBody
	public int unlikePost(@RequestBody LikeVo likevo) {
		boardService.deleteLike(likevo.getBoardId(), likevo.getUserNum());
		int likeCnt = boardService.getLikeCntByBoardId(likevo.getBoardId());
		BoardVo board = boardService.getPost(likevo.getBoardId()).get();
		board.setLikeCnt(likeCnt);
		boardService.addPost(board);
		return likeCnt;
	}
	
}
