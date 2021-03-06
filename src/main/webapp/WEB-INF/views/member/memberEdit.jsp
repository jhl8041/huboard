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
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker3.min.css">
	
	<!-- DatePicker Script -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.0-RC3/js/bootstrap-datepicker.min.js"></script>
	
    <!-- Script -->
	<script type="text/javascript" src="/resources/member/js/memberEdit.js" charset="utf-8"></script>
	
	<title>회원가입</title>
</head>
<body>
	<!-- 네비게이션 바 -->
	<div id="nav-placeholder"></div>
	
	<div class="container">
		<div class="row justify-content-center">
			<div id="joinTitle">
				<h3>회원정보 수정</h3>
				<hr>
			</div>
		</div>
		<div class="row justify-content-center">
			<form action="/mypage" method=post name=form id=form  onsubmit="return formCheck()">
				<div class="form-group">
					<label>아이디</label>
					<input type=text class="form-control" name="userId" id="id" value="${member.userId}" readonly>	
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
					<input type=text class="form-control" value="${member.userFullName}" name=userFullName id=name>
				</div>
				<div class="form-group">
					<label>닉네임</label>
					<input type=text class="form-control" placeholder="닉네임을 입력하세요" name=nickname id=nickname value="${member.nickname}" readonly>
				</div>
				<div class="form-group">
					<label>생년월일</label>
					<div class="form-inline">
						<input type=text id="cal" class="form-control" name=birthDateStr value="${member.birthDate}" style="width:300px" placeholder="생년월일" />
						<div class="input-group-append" data-target="calstart" data-toggle="datepicker">
	                        <div class="input-group-text"><i class="fa fa-calendar" style="height:23px;text-align:center"></i></div>
	                    </div>
                    </div>
				</div>
				<div class="form-group">
					<label>성별</label>
					<select class="form-control" name=gender style="width: 300px">
						<c:if test="${member.gender eq '남'}">
							<option selected>남</option>
							<option>여</option>
						</c:if>
						<c:if test="${member.gender eq '여'}">
							<option>남</option>
							<option selected>여</option>
						</c:if>
					</select>
				</div>
				<div class="form-group">
					<label>주소</label>
					<div class="form-inline">
						<input class="form-control" type=text name=roadAddr id=roadAddrPart1 value="${member.roadAddr}" readonly>
						<input class="btn btn-primary" type=button style="width:100px" value=주소검색 onclick="goPopup()">
					</div>
				</div>
				<div class="form-group">
					<label>상세주소</label>
					<input class="form-control" type=text name="detailAddr" id="addrDetail" value="${member.detailAddr}" readonly>
				</div>
				<div class="form-group">
					<label>휴대폰번호</label>
					<input class="form-control" type=text name=phone id=phone value="${member.phone}" oninput='phoneCheck()'>
				</div>
				<div class="form-group">
					<label>이메일</label>
					<div class="form-inline">
						<input class="form-control" type="email" name=email id=email value="${member.email}" readonly>
					</div>
				</div>
				<div class="form-group">	
					<input class="btn btn-primary" style="margin-top:30px" type=submit value="회원정보수정">
				</div>
				<div class="form-group">	
					<input class="btn btn-primary" style="margin-top:10px" type=button value=취소 onclick="location.href='/home'">
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