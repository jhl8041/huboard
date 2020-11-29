package com.humuson.huboard.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="category")
@Getter @Setter
@ToString
@NoArgsConstructor
public class CategoryVo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	private String categoryName;
	
	@OneToMany(mappedBy="categoryId", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval=true)
	private List<BoardVo> boards = new ArrayList<>();
	
}
