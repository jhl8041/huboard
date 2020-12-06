package com.humuson.huboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tag")
@Getter @Setter
@ToString
public class TagVo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tagNum;
	private Long userNum;
	private String searchedTag;
	private String bestTag;
	
}
