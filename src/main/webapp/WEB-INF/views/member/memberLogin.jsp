<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<input type=text class="form-control" style="width:330px" placeholder="아이디를 입력하세요" name=userId required>
			  	</div>
			  	<div class="form-group">
					<label>비밀번호</label>
					<input type=password class="form-control" style="width:330px" placeholder="비밀번호를 입력하세요" maxlength=20 name=password required>
					<div class="alert alert-danger collapse" role="alert" style="width:300px" id=alertpwd>
						<span id="pwdcheck"></span>
					</div>
			  	</div>
			  	<div>
			  		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
	                    <font color="red">
	                    	<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
	                    </font>
                  	</c:if>
			  	</div>
			  	<div class="form-group">	
					<input class="btn btn-primary" style="width:330px" id="loginButton" type=submit value=로그인>
				</div>
				<div class="form-group">	
					<input class="btn btn-primary" style="width:330px" id="joinButton" type=button value=회원가입 onclick="location.href='/join'">
				</div>	
			</form>
		</div>
	</div>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>