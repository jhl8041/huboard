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
@Table(name="liketable")
@Getter @Setter
@ToString
public class LikeVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeId;
	private Long boardId;
	private Long userNum;
}
