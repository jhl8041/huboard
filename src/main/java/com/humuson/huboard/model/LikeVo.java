package com.humuson.huboard.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="liketable")
@Getter @Setter
@NoArgsConstructor
@ToString
public class LikeVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeId;
	private Long boardId;
	private Long userNum;
}
