package com.humuson.huboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.humuson.huboard.model.CommentVo;
import com.humuson.huboard.service.BoardService;
import com.humuson.huboard.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private BoardService boardService;
	
	//댓글 등록
	@PostMapping("/comment")
	@ResponseBody
	public List<CommentVo> doComment(@RequestBody CommentVo commentvo) {
		commentService.addComment(commentvo);
		boardService.updateCommentCnt(commentvo.getBoardId());
		return commentService.getComment(commentvo.getBoardId());
	}
	
	//대댓글 등록
	@PostMapping("/cocomment")
	@ResponseBody
	public List<CommentVo> doCoComment(@RequestBody CommentVo commentvo) {
		commentService.addCoComment(commentvo);
		boardService.updateCommentCnt(commentvo.getBoardId());
		return commentService.getComment(commentvo.getBoardId());
	}
	
	//댓글리스트 불러오기
	@GetMapping("/comment/{boardId}")
	@ResponseBody
	public List<CommentVo> getComment(@PathVariable("boardId") Long boardId) {
		List<CommentVo> comments = commentService.getComment(boardId);
		boardService.updateCommentCnt(boardId);
		return comments;
	}
	
	//댓글 수정 및 삭제처리
	@PatchMapping("/comment")
	@ResponseBody
	public List<CommentVo> editComment(@RequestBody CommentVo commentvo) {
		commentService.editComment(commentvo);
		boardService.updateCommentCnt(commentvo.getBoardId());
		return commentService.getComment(commentvo.getBoardId());
	}

}
