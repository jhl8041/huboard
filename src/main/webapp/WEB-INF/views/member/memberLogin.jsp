<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
	
	<!-- AJAX -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/member/css/memberLogin.css">
	
	<!-- Script -->
	<script type="text/javascript" src="/resources/member/js/memberJoin.js" charset="utf-8"></script>
	<script type="text/javascript" src="/resources/member/js/memberLogin.js" charset="utf-8"></script>
	
</head>
<body>
	<!-- 네비게이션 바 -->
	<div id="nav-placeholder"></div>
	
	<div class="container">
		<div class="row justify-content-center">
			<div id="loginTitle">
				<h3>로그인</h3>
				<hr>
			</div>
		</div>
		<div class="row justify-content-center">
			<form action="/login" method=post name=form id=form>
				<div class="form-group">
					<label>아이디</label>
					<input type=text class="form-control" style="width:300px" placeholder="아이디를 입력하세요" name=userId>
			  	</div>
			  	<div class="form-group">
					<label>비밀번호</label>
					<input type=password class="form-control" placeholder="비밀번호를 입력하세요" maxlength=20 name=password>
					<div class="alert alert-danger collapse" role="alert" style="width:300px" id=alertpwd>
						<span id="pwdcheck"></span>
					</div>
			  	</div>
			  	<div class="form-group">	
					<input class="btn btn-primary" id="loginButton" type=submit value=로그인>
				</div>
				<div class="form-group">	
					<input class="btn btn-primary" id="joinButton" type=button value=회원가입 onclick="location.href='/goJoin'">
				</div>	
			</form>
		</div>
	</div>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</body>
</html>