<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
</head>
<body>
	<!-- 네비게이션 바 -->
	<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
		<!-- 홈버튼 -->
		<a class="navbar-brand" href="/">
			<img class="navbar-brand" alt="not found" src="/resources/images/humus_logo_home.png">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	    </button>
	    
	    <!-- 메뉴 리스트 -->
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
			    <a class="nav-link active" href="#">쇼핑몰</a>
			    <a class="nav-link" href="#">커뮤니티</a>
			    <a class="nav-link" href="/lab">실험실</a>
		    </div>
		    <c:if test="${empty member.getUserName()}">
		    	<input type="button" value="로그인"/>
		    </c:if>
		    <c:if test="${not empty member.getUserName()}">
		    	<span class="navbar-text">
		    		${member.getUserName()}님 환영합니다!
		    	</span>
		    </c:if>
		    
		</div>
	</nav>
</body>
</html>