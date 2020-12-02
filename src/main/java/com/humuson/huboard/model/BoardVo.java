package com.humuson.huboard.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="board")
@Getter @Setter
@ToString
@NoArgsConstructor
public class BoardVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardId;
	
	@OneToMany(mappedBy="boardId", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval=true)
	private List<CommentVo> comment = new ArrayList<>();
	
	@OneToMany(mappedBy="boardId", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval=true)
	private List<FileVo> file = new ArrayList<>();
	
	@OneToMany(mappedBy="boardId", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval=true)
	private List<LikeVo> like = new ArrayList<>();
	
	private int commentCnt;
	private String userId;
	private String nickname;
	private Long userNum;
	private String subject;
	
	private Long categoryId;
	
	@Column(length = 10000)
	private String content;
	private int view;
	private Timestamp createDate;
	private Timestamp updateDate;
	private String visible;
	private int likeCnt;
	
	public BoardVo(String userId, String visible) {
		this.userId = userId;
		this.visible = visible;
	}
	
	public BoardVo(Long boardId, String userId, String subject, String content,Timestamp createDate,
			Timestamp updateDate, String visible) {
		super();
		this.userId = userId;
		this.subject = subject;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.visible = visible;
	}
}

