<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>Insert title here</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script type="text/javascript" src="/resources/board/js/boardView.js" charset="utf-8"></script>
		
</head>
<body>
	<div class="form-group" style="margin-top:30px">
		<div class="form-inline">
			<label><h3>${post.subject}</h3></label> 
		</div>
	</div>
	<div class="form-group">
		<div class="form-inline">
			<label><b>작성자</b></label>
			<label style="margin-left:10px;">${post.userId}</label>
			
			<label style="margin-left:20px;"><b>조회수</b></label>
			<label style="margin-left:10px;">${post.view}</label> 
			
			<label style="margin-left:20px;"><b>작성일</b></label>
			<label style="margin-left:10px;">${post.date}</label>
		</div>
	</div>
	<hr>
	<div class="form-group">
		${post.content}
	</div>
	
	<div>
		<c:forEach var="files" items="${files}" >
			<a href="/resources/uploads/${files.storedFileName}">${files.originFileName}</a>
			<br>
		</c:forEach>
	</div>
	
	<form action="doComment" method="post">
		<div class="form-group">
			<label for="commentTextArea">댓글작성</label>
			<div class="form-inline">
				<textarea style="resize: none; width: 500px;" class="form-control" name="commentContent" id="commentContent" rows="2"></textarea>
				<input type="hidden" name="boardId" id="boardId" value="${post.boardId}"/>
				<input type="hidden" name="userId" id="userId" value="${member.userId}"/>
				<input class="btn btn-primary" style="margin-top:10px" type=button value=댓글작성 onClick="addComment()">
			</div>
		</div>
	</form>
	
	<!-- 댓글 AJAX -->
	<div class="input-group" role="group" aria-label="..." style="margin-top: 10px; width: 100%;">
	    <div id="showComment" style="text-align: center;"></div>
	</div>
	<input type="button" value="목록" onclick="location.href='http://localhost:8080/'"/>
	
	<c:if test="${member.userId eq post.userId}">
		<input type="button" value="수정" onclick="location.href='http://localhost:8080/goEdit?id=${post.boardId}'"/>
		<input type="button" value="삭제" onclick="location.href='http://localhost:8080/doDelete?id=${post.boardId}'"/>
	</c:if>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	
</body>
</html>