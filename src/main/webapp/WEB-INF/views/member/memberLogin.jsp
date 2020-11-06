<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
	</head>
<body>
	<div class="d-flex justify-content-center">
		<form action="/proceed_login" method=post name=form id=form onsubmit="accountCheck()">
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
				<input class="btn btn-primary" type=submit value=로그인>
			</div>
			<div class="form-group">	
				<input class="btn btn-primary" style="margin-top:10px" type=button value=회원가입 onclick="location.href='/gojoin'">
			</div>
		</form>
	</div>
</body>
</html>