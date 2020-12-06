package com.humuson.huboard.model.dto;

import java.util.List;

import com.humuson.huboard.model.BoardVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
public class CatRowColDto {
	private List<List<BoardVo>> catList;
	private int row;
	private int col;
}
