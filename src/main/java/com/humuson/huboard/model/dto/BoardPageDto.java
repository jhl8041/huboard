package com.humuson.huboard.model.dto;

import java.util.List;

import com.humuson.huboard.model.BoardVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardPageDto {
	private List<BoardVo> pageBoardList;
	private int currPage;
	private int currSize;
	private Long totalBoards;
	private int totalPages;
	private int blockStart;
	private int blockEnd;
	
}
