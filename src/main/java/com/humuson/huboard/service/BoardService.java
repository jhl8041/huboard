package com.humuson.huboard.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.humuson.huboard.model.BoardVo;
import com.humuson.huboard.model.CommentVo;
import com.humuson.huboard.model.LikeVo;
import com.humuson.huboard.repository.BoardRepository;
import com.humuson.huboard.repository.CommentRepository;
import com.humuson.huboard.repository.LabRepository;
import com.humuson.huboard.repository.LikeRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private LabRepository labRepo;
	
	@Autowired
	private LikeRepository likeRepo;
	
	
	public List<BoardVo> getRecommPost(Long userNum){
		List<String> bestTags = new ArrayList<>();
		String tags = "";
		if (labRepo.findByUserNum(userNum).isPresent()) {
			tags = labRepo.findByUserNum(userNum).get().getBestTag();
			tags = tags.substring(1,tags.length()-1);
			bestTags = Arrays.asList(tags.split(", "));
		}
		
		if (bestTags.size() == 0) {
			return new ArrayList<>();
		}
		
		Random r = new Random();
		int low=0;
		int high=bestTags.size();
		int result = r.nextInt(high-low) + low;

		List<BoardVo> recommList = boardRepo.findTop10BySubjectContainingOrSubjectContainingOrSubjectContainingAndVisibleOrderByLikeCntDescViewDescUpdateDateDesc(bestTags.get(result), bestTags.get(result), bestTags.get(result), "Y");
				
		return recommList;
	}
	
	
	//게시글 페이지 객체로 변환 및 반환
	public Page<BoardVo> getPagingPost(Long categoryId, Pageable pageable){	
		return boardRepo.findByVisibleAndCategoryId("Y", categoryId,  pageable);
	}
	
	//게시글 상위 10개 조회
	public List<BoardVo> getTopTen(Long categoryId){
		return boardRepo.findTop10ByCategoryIdOrderByBoardIdDesc(categoryId);
	}
	
	//게시글 검색
	public Page<BoardVo> findPostBySearch(String keyword, Long categoryId,Pageable pageable, String search_type) {
		Page<BoardVo> searchPage = boardRepo.findByVisibleAndCategoryId("Y", categoryId, pageable);
		if (search_type.equals("subject")) {
			searchPage = boardRepo.findBySubjectContainingAndVisibleAndCategoryId(keyword, "Y", categoryId, pageable);
		}
		else if (search_type.equals("userId")) {
			searchPage = boardRepo.findByNicknameContainingAndVisibleAndCategoryId(keyword, "Y", categoryId, pageable);
		}
		else if (search_type.equals("content")) {
			searchPage = boardRepo.findByContentContainingAndVisibleAndCategoryId(keyword, "Y", categoryId, pageable);
		}
		return searchPage;
	}
	
	//게시글 가져오기
	public Optional<BoardVo> getPost(Long id){
		return boardRepo.findById(id);
	}
	
	//게시글 삭제
	public void deletePost(Long id){
		boardRepo.deleteById(id);
	}
	
	//게시글 추가
	public BoardVo addPost(BoardVo boardVo){
		return boardRepo.save(boardVo);
	}
	
	//댓글수 업데이트
	public void updateCommentCnt(Long boardId) {
		BoardVo board = boardRepo.findById(boardId).get();
		List<CommentVo> comments = commentRepo.findByBoardId(boardId);
		board.setCommentCnt(comments.size());
		boardRepo.save(board);
	}
	
	//좋아요 여부
	public boolean getIfILike(Long boardId, Long UserNum) {
		if (likeRepo.findByBoardIdAndUserNum(boardId, UserNum).isEmpty()) {
			return false;
		}
		return true;
	}
	
	//좋아요 행 추가
	public void addLike(LikeVo likevo) {
		likeRepo.save(likevo);
	}
	
	//좋아요 행 삭제
	public void deleteLike(Long boardId, Long userNum) {
		likeRepo.deleteByBoardIdAndUserNum(boardId, userNum);
	}
	
	//게시글별 좋아요 조회
	public int getLikeCntByBoardId(Long boardId) {
		return likeRepo.findByBoardId(boardId).size();
	}
	
}
