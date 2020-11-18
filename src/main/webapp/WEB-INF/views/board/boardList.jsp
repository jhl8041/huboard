<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<title>휴머스보드 홈</title>
	
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
	    	<button class="btn btn-outline-success my-2 my-sm-0" onclick="boardSearch()">검색</button>
    	</form>
	</nav>
	
	<table class="table table-hover">
		<thead>
			<tr style="text-align:center">
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">작성시간</th>
				<th scope="col">작성자</th>
				<th scope="col">조회수</th>
			</tr>
		</thead>
		<c:if test="${list.isEmpty() eq false}">
			<tbody>
				<c:forEach var="list" items="${list.getContent()}" >
					<tr style="text-align:center" class='clickable-row' data-href='http://localhost:8080/board/${list.boardId}'>
						<td>${list.boardId}</td>
						<td>${list.subject}</td>
						<td>${list.date}</td>
						<td>${list.userId}</td>
						<td>${list.view}</td>
					</tr>
				</c:forEach>
			</tbody>
		</c:if>
		<c:if test="${list.isEmpty() eq true}">
			<tbody>
				<tr class="table-secondary" style="text-align:center">
					<td colspan="6">게시글이 존재하지 않습니다</td>
				<tr>
			</tbody>
		</c:if>
	</table>
	
	<c:if test="${list.isEmpty() eq false}">
		<!-- 페이징을 위한 변수 설정 -->
		<c:set var="paginav_len" value="5"/>
	   	<c:set var="paginav_section" value="${list.getNumber()/paginav_len - (list.getNumber()/paginav_len)%1}"/>
	   	
	   	<c:set var="start_page" value="${paginav_section * paginav_len}"/>
	   	<c:set var="end_page" value="${start_page + paginav_len - 1}"/>
	   	
	   	<c:if test="${end_page gt list.getTotalPages()}">
	   		<c:set var="end_page" value="${list.getTotalPages()-1}"/>
	   	</c:if>
	   	
	   	<c:choose>
	   		<c:when test="${start_page eq 0 }">
	   			<c:set var="disable_trig_prev" value="disabled"/>
	   		</c:when>
	   		<c:when test="${start_page ne 0}">
	   			<c:set var="disable_trig_prev" value=""/>
	   		</c:when>
	   	</c:choose>
	   	
	   	<c:choose>
	   		<c:when test="${end_page eq list.getTotalPages()-1}"> 
	   			<c:set var="disable_trig_next" value="disabled"/>
	   		</c:when>
	   		<c:when test="${end_page ne list.getTotalPages()-1}"> 
	   			<c:set var="disable_trig_next" value=""/>
	   		</c:when>
	   	</c:choose>
	   	
	   	<fmt:parseNumber var="previous" value="${start_page - 1}" integerOnly="true"/>
	   	<fmt:parseNumber var="next" value="${end_page + 1}" integerOnly="true"/>
	   	
	   	<!-- 페이징 구현 -->
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
		    	<li class="page-item ${disable_trig_prev}">
		      		<a class="page-link" href="?page=${previous}" tabindex="-1" aria-disabled="true">이전</a>
		    	</li>
		    	
		    	<c:forEach var="page" begin="${start_page}" end="${end_page}">
				    <li class="page-item"><a class="page-link" href="?page=${page}">${page+1}</a></li>
				</c:forEach>
		    
		    	<li class="page-item ${disable_trig_next}">
		      		<a class="page-link" href="?page=${next}">다음</a>
		    	</li>
		  	</ul>
		</nav>
	</c:if>
	
	<button type="button" class="btn btn-primary" onclick="location.href='/editor/0'">글쓰기</button>
	<form action="/doLogout" method=post name=logout>
		 	<input class="btn btn-primary" type=submit value=로그아웃>
	</form>
	
	
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    
    <script>
		jQuery(document).ready(function($) {
		    $(".clickable-row").click(function() {
		        window.location = $(this).data("href");
		    });
		});
	</script>
</body>
</html>