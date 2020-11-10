package com.humuson.huboard.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
@Table(name="board")
@Getter @Setter
@ToString
public class BoardVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="boardid")
	private Long boardId;
	
	@Column(name="userid")
	private String userId;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="content")
	private String content;
	
	@Column(name="view")
	private int view;
	
	@Column(name="date")
	private Timestamp date = Timestamp.valueOf(LocalDateTime.now());
	
	public BoardVo() {}
	
	public BoardVo(Long boardId, String userId, String userName, String subject, String content,Timestamp date) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.subject = subject;
		this.content = content;
		this.date = date;
	}
	
}

