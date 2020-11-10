package com.humuson.huboard.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//https://lightcode.tistory.com/22

@Entity
@Table(name="comment")
@Getter @Setter
@ToString
public class CommentVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentID;
	private String boardId;
	private int depth;
	private int bundleId;
	private int bundleOrder;
	private String userId;
	private String nickname;
	private String ip;
	private String comment;
	private boolean isDeleted;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Timestamp deleteDate;
}
