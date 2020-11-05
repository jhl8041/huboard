package com.humuson.huboard.model;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name="member")
@Getter @Setter
@ToString
public class MemberVo {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userNum;
	private String userId;
	private String password;
	private String userName;
	private String nickname;
	private String phone;
	private Date birthDate;
	private String email;
	private String gender;
	private Date joinDate;
	private String address;
	
	public MemberVo() {}

	public MemberVo(Long userNum, String userId, String password, String userName, String nickname, String phone,
			Date birthDate, String email, String gender, Date joinDate, String address) {
		super();
		this.userNum = userNum;
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.nickname = nickname;
		this.phone = phone;
		this.birthDate = birthDate;
		this.email = email;
		this.gender = gender;
		this.joinDate = joinDate;
		this.address = address;
	}
}