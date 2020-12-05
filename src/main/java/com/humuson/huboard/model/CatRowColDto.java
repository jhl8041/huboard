package com.humuson.huboard.model;

import java.util.List;

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
