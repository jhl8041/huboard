package com.humuson.huboard.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="file")
@Getter @Setter
@ToString
public class FileVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileId;
	//@Column(name="board_id")
	private Long boardId;

	private String userId;
	private Long userNum;
	private String originFileName;
	private String storedFileName;
	private double fileSize;
	private Timestamp regDate;
	
}
