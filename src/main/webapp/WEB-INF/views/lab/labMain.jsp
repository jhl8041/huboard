<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
	<meta charset="EUC-KR">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<title>휴머스 실험실</title>
</head>
<body>
	<nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="/">
			<img class="navbar-brand" alt="not found" src="/resources/images/humus_logo_home.png">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	    </button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
			    <a class="nav-link active" href="#">쇼핑몰</a>
			    <a class="nav-link" href="#">커뮤니티</a>
			    <a class="nav-link" href="#">실험실</a>
		    </div>
		    <span class="navbar-text">
		    	${member.getUserName()}님 환영합니다!
		    </span>
		</div>
		
    	<form class="form-inline" method="get" action="doSearch">
	    	<select class="custom-select" name="search_type">
				<option value="subject" selected>제목</option>
				<option value="userId">작성자</option>
				<option value="content">내용</option>
			</select>
	    	<input class="form-control mr-sm-2" name="keyword" type="search" placeholder="검색" aria-label="Search">
	    	<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
    	</form>
	</nav>
	
	
	
</body>
</html>