<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script type="text/javascript" src="/resources/member/js/memberForm.js" charset="utf-8"></script>
	
	<title>Insert title here</title>
</head>
<body>
	<div class="d-flex justify-content-center">
		<form action="/proceed_join" method=post name=form id=form  onsubmit="return formCheck()">
			<div class="form-group">
				<h1>회원가입</h1>
			</div>
			<div class="form-group">
				<label>아이디</label>
				<input type=text class="form-control" style="width:300px" placeholder="아이디를 입력하세요" name=userId id=id oninput='idCheck()'>				
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
				<input type=text class="form-control" placeholder="이름을 입력하세요" name=userName id=name>
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
					<select class="form-control" style=width:90px name=birthyear>
						<c:forEach var="i" begin="0" end="120"><option>${2020-i}</option></c:forEach>	
					</select>
					<label>년</label>
					<select class="form-control" style=width:80px name=birthmonth>
						<c:forEach var="i" begin="1" end="12"><option>${i}</option></c:forEach>
					</select> 
					<label>월</label>
					<select class="form-control" style=width:80px name=birthday>
						<c:forEach var="i" begin="1" end="31"><option>${i}</option></c:forEach>
					</select>
					<label>일</label>
				</div>
			</div>
			<div class="form-group">
				<label>성별</label>
				<select class="form-control" name=gender>
					<option>남</option>
					<option>여</option>
				</select>
			</div>
			<div class="form-group">
				<label>주소</label>
				<div class="form-inline">
					<input class="form-control" type=text name=roadAddrPart1 id=roadAddrPart1 readonly>
					<input class="btn btn-primary" type=button value=주소검색 onclick="goPopup()">
				</div>
			</div>
			<div class="form-group">
				<label>상세주소</label>
				<input class="form-control" type=text name="addrDetail" id="addrDetail" readonly>
			</div>
			<div class="form-group">
				<label>휴대폰번호</label>
				<input class="form-control" type=text name=phone id=phone placeholder="숫자로만 입력해주세요" oninput='phoneCheck()'>
			</div>
			<div class="form-group">
				<label>이메일</label>
				<div class="form-inline">
					<input class="form-control" type=text name=email id=email placeholder="이메일 입력" oninput="emailCheck()">
					<input class="btn btn-primary" type="button" id="emailbutton" value="인증코드전송" onclick='sendCode();' disabled>
				</div>
				<div class="alert alert-danger collapse" role="alert" style=width:230px id=alertemail>
					<span id="emailcheck"></span>
				</div>
			</div>
			<div class="form-group">
				<label>인증번호</label>
				<div class="form-inline">
					<input class="form-control" type=text placeholder="인증번호입력" id=code>
					<input class="btn btn-primary" type="button" value="확인" onclick='codeCheck()'>
				</div>
				<div class="alert alert-danger collapse" role="alert" style="width:300px" id=alertcode>
					<span id="codetimer"></span>
				</div>	
			</div>
			<div class="form-group">	
				<input class="btn btn-primary" type=submit value=회원가입>
			</div>
			<div class="form-group">	
				<input class="btn btn-primary" style="margin-top:10px" type=button value=로그인화면으로 onclick="location.href='/goLogin'">
			</div>
		 </form>
	</div> 
	
	<!-- 스트립트 -->
	<!--  <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</body>
</html>