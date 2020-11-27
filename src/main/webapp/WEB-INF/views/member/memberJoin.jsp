<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<!-- JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/member/css/memberJoin.css">
    
    <!-- DatePicker CSS-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker3.min.css"></script>
	
	<!-- DatePicker Script -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.0-RC3/js/bootstrap-datepicker.min.js"></script>
	
    <!-- Script -->
	<script type="text/javascript" src="/resources/member/js/memberJoin.js" charset="utf-8"></script>
	
	<title>회원가입</title>
</head>
<body>
	<!-- 네비게이션 바 -->
	<div id="nav-placeholder"></div>
	
	<div class="container">
		<div class="row justify-content-center">
			<div id="joinTitle">
				<h3>회원가입</h3>
				<hr>
			</div>
		</div>
		<div class="row justify-content-center">
			<form action="/join" method=post name=form id=form  onsubmit="return formCheck()">
				<div class="form-group">
					<label>아이디</label>
					<input type=text class="form-control" placeholder="아이디를 입력하세요" name=userId id=id oninput='idCheck()'>				
					<div class="alert alert-danger collapse" role="alert" style=width:300px id=alertid>
						<span id="idcheck"></span>
					</div>
			  	</div>
			  	<div class="form-group">
					<label>비밀번호</label>
					<input type=password class="form-control" placeholder="비밀번호를 입력하세요" maxlength=20 name=password id=pwd oninput="pwdCheck()">
					<div class="alert alert-danger collapse" role="alert" style="width:300px" id=alertpwd>
						<span id="pwdcheck"></span>
					</div>
			  	</div>
				<div class="form-group">
					<label>비밀번호 확인</label>
					<input type=password class="form-control" placeholder="비밀번호를 재입력하세요" maxlength=20 id=pwd2 oninput='pwdMatchCheck()'>
					<div class="alert alert-danger collapse" role="alert" style="width:300px" id=alertpwd2>
						<span id="pwdmatchcheck"></span>
					</div>	
				</div>
				<div class="form-group">
					<label>이름</label>
					<input type=text class="form-control" placeholder="이름을 입력하세요" name=userFullName id=name>
				</div>
				<div class="form-group">
					<label>닉네임</label>
					<input type=text class="form-control" placeholder="닉네임을 입력하세요" name=nickname id=nickname oninput="nicknameCheck()">
					<div class="alert alert-danger collapse" role="alert" style=width:300px id=alertnickname>
						<span id="nicknamecheck"></span>
					</div>
				</div>
				<div class="form-group">
					<label>생년월일</label>
					<div class="form-inline">
						<input type=text id="cal" class="form-control" name=birthDateStr style="width:300px" placeholder="생년월일" />
						<div class="input-group-append" data-target="calstart" data-toggle="datepicker">
	                        <div class="input-group-text"><i class="fa fa-calendar" style="height:23px;text-align:center"></i></div>
	                    </div>
                    </div>
				</div>
				<div class="form-group">
					<label>성별</label>
					<select class="form-control" name=gender style="width: 300px">
						<option>남</option>
						<option>여</option>
					</select>
				</div>
				<div class="form-group">
					<label>주소</label>
					<div class="form-inline">
						<input class="form-control" type=text name=roadAddr id=roadAddrPart1 readonly>
						<input class="btn btn-primary" type=button style="width:100px" value=주소검색 onclick="goPopup()">
					</div>
				</div>
				<div class="form-group">
					<label>상세주소</label>
					<input class="form-control" type=text name="detailAddr" id="addrDetail" readonly>
				</div>
				<div class="form-group">
					<label>휴대폰번호</label>
					<input class="form-control" type=text name=phone id=phone placeholder="숫자로만 입력해주세요" oninput='phoneCheck()'>
				</div>
				<div class="form-group">
					<label>이메일</label>
					<div class="form-inline">
						<input class="form-control" type="email" name=email id=email placeholder="이메일 입력" oninput="emailCheck()">
						<input class="btn btn-primary" style="width:120px" type="button" id="emailbutton" value="인증코드전송" onclick='sendCode();' disabled>
					</div>
					<div class="alert alert-danger collapse" role="alert" style="width:300px" id=alertemail>
						<span id="emailcheck"></span>
					</div>
				</div>
				<div class="form-group">
					<label>인증번호</label>
					<div class="form-inline">
						<input class="form-control" style="width:300px" type=text placeholder="인증번호입력" id=code>
						<input class="btn btn-primary" style="width:100px" type="button" value="확인" onclick='codeCheck()'>
					</div>
					<div class="alert alert-danger collapse" role="alert" style="width:300px" id=alertcode>
						<span id="codetimer"></span>
					</div>
				</div>
				<div class="form-group">	
					<input class="btn btn-primary" style="margin-top:30px" type=submit value=회원가입>
				</div>
				<div class="form-group">	
					<input class="btn btn-primary" style="margin-top:10px" type=button value=로그인화면으로 onclick="location.href='/login'">
				</div>
			 </form>
		</div>
	</div>
	
	<!-- Footer -->
	<div id="footer-placeholder"></div>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    
    <script>				    
	$(function () {
	    $('#cal').datepicker({
	    	format: "yyyy-mm-dd"
	    });
	});
	</script>
</body>
</html>