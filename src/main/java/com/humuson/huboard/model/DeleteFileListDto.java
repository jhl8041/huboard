package com.humuson.huboard.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteFileListDto {
	private List<String> deletefileList;
}
