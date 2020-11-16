package com.humuson.huboard.model;

import java.sql.Timestamp;

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
	private Long boardId;

	private String userId;
	private String originFileName;
	private String storedFileName;
	private Long fileSize;
	private Timestamp regDate;
	
}
