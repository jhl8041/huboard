package com.humuson.huboard.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//https://lightcode.tistory.com/22

@Entity
@Table(name="commentvo")
@Getter @Setter
@ToString
public class CommentVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	private Long boardId;
	
	private Long parentCommentId;
	private Long groupId;
	private Long orderNo;
	//@ColumnDefault("0")
	private Long depth;
	
	private String userId;
	private String commentContent;
	
}
