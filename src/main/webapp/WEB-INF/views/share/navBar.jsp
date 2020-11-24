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
			<div class="navbar-nav col">
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			      <li class="nav-item active">
			        <a class="nav-link active" href="#">쇼핑몰</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="/">커뮤니티</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="/lab">실험실</a>
			      </li>
			    </ul>
		    </div>
		    <div class="navbar-nav col justify-content-end" style="padding-right:50px">
		    	<ul class="navbar-nav ml-0 mr-0 mt-2 mt-lg-0">
		    		<li class="nav-item dropdown">
				        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				          마이메뉴
				        </a>
				        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
				          	<c:if test="${empty member.getNickname()}">
				          		<a class="dropdown-item" href="/login">로그인</a>
				          		<a class="dropdown-item" href="/join">회원가입</a>
				          	</c:if>
				          	<c:if test="${not empty member.getNickname()}">
				          		<a class="dropdown-item" href="/mypage">내정보수정</a>
					          	<a class="dropdown-item" href="javascript:void(0)" onclick="quitMember(${member.getUserId()})">회원탈퇴</a>
					          	<div class="dropdown-divider"></div>
				          		<a class="dropdown-item" href="/logout">로그아웃</a>
				          	</c:if>
				    	</div>
					</li>
		    	</ul>
		    </div>
		</div>
		<script>
		function quitMember(userId){
			if (confirm("정말 탈퇴하시겠습니까?")){
				var form = document.createElement("form");
			    form.setAttribute("charset", "UTF-8");
			    form.setAttribute("method", "Post");  //Post 방식
			    form.setAttribute("action", "/quit"); //요청 보낼 주소
			        
			    var hiddenField = document.createElement("input");
			    hiddenField.setAttribute("type", "hidden");
			    hiddenField.setAttribute("name", "userId");
			    hiddenField.setAttribute("value", userId);
			    form.appendChild(hiddenField);
			    
			    document.body.appendChild(form);
			    form.submit();
			}
		}
		</script>
	</nav>
</body>
</html>