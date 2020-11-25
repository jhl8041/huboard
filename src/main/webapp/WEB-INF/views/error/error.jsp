<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
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
	<div>
		<!-- 네비게이션 바 -->
		<div id="nav-placeholder"></div>
		
		<c:if test="${code eq 404}">
			<c:set var="errorTitle" value="잘못된 페이지 접근입니다" />
		</c:if>
		<c:if test="${code eq 500}">
			<c:set var="errorTitle" value="내부서버 에러입니다" />
		</c:if>
		
		<div class="container">
			<div class="row justify-content-center">
				<div id="loginTitle">
					<h3> ${errorTitle} </h3>
					<hr>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="form-group">	
					<input class="btn btn-primary" style="width:330px; margin-top:100px" type=button value=홈으로 onclick="location.href='/'">
				</div>
			</div>
		</div>
	</div>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>