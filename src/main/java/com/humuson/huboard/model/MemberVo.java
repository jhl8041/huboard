package com.humuson.huboard.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class MemberVo {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userNum;
	
	@OneToMany(mappedBy="userNum", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval=true)
	private List<BoardVo> board = new ArrayList<>();
	
	private String userId;
	private String password;
	private String userName;
	private String nickname;
	private String phone;
	private Date birthDate;
	private String email;
	private String gender;
	private Timestamp joinDate;
	private String roadAddr;
	private String detailAddr;
	private String auth = "ROLE_MEMBER";
	
	@Transient
	private String birthDateStr;
	
	public MemberVo() {}

	public MemberVo(Long userNum, String userId, String password, String userName, String nickname, String phone,
			Date birthDate, String email, String gender, String roadAddr, String detailAddr, Timestamp joinDate) {
		super();
		this.userNum=userNum;
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.nickname = nickname;
		this.phone = phone;
		this.birthDate = birthDate;
		this.email = email;
		this.gender = gender;
		this.roadAddr = roadAddr;
		this.detailAddr = detailAddr;
		this.joinDate = joinDate;
	}
	
}