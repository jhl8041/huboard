<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>${post.subject}</title>
	
	<!-- JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/board/css/boardView.css" charset="utf-8"></link>
	
	<!-- Script -->
	<script type="text/javascript" src="/resources/board/js/boardView.js" charset="utf-8"></script>
		
</head>
<body>
	<!-- 네비게이션 바 -->
	<div id="nav-placeholder"></div>
	
	<div class="container">
		<div id="postTitle" class="row justify-content-left">
			<h3>
				${post.subject}
			</h3>
		</div>
		<div class="row justify-content-left">
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
		<div id="postContent" class="row justify-content-left">
			${post.content}
		</div>
	
		<div>
			<c:forEach var="files" items="${files}" >
				<a href="/resources/uploads/${files.storedFileName}" download="${files.originFileName}">${files.originFileName}</a>
				<br>
			</c:forEach>
		</div>
		<hr>
		<!-- 버튼 기능 -->
		<div id="mainButtonRow" class="row justify-content-left">
			<input type="button" value="목록" onclick="location.href='http://localhost:8080/'"/>
			<!-- 작성자 본인만 사용하는 기능 -->
			<c:if test="${member.userId eq post.userId}">
				<input type="button" value="수정" onclick="location.href='/editor/${post.boardId}'"/>
				<input type="button" value="삭제" onclick="deletePost()"/>
			</c:if>
		</div>
		<hr>
		<!-- 댓글 작성 -->
		<div id="mainCommentRow" class="row justify-content-left">
			<div class="form-group">
				<label for="commentTextArea">댓글작성</label>
				<div class="form-inline">
					<textarea class="form-control" name="commentContent" id="commentContent" rows="2"></textarea>
					<input type="hidden" name="boardId" id="boardId" value="${post.boardId}"/>
					<input type="hidden" name="userId" id="userId" value="${member.userId}"/>
					<input class="btn btn-primary" style="margin-top:10px" type=button value=댓글작성 onClick="addComment()">
				</div>
			</div>
		</div>

		
		<!-- 댓글 및 대댓글 표시 -->
		<div id="coCommentRow" class="row justify-content-left">
			<div class="input-group" role="group" aria-label="..." style="margin-top: 10px; width: 100%;">
			    <div id="showComment" style="text-align: center;"></div>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="editCommentModal" tabindex="-1" aria-labelledby="editCommentModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="editCommentModal">댓글 수정</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<input type="hidden" class="form-control" id="editId"/>
					<input type="text" class="form-control" id="editedContent"/>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" onclick="editComment()">수정 및 저장</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	
</body>
</html>