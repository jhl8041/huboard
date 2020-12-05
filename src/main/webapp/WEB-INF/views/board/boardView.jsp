<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${post.subject}</title>
	
	<!-- JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/board/css/boardView.css"></link>
	
	<!-- Script -->
	<script type="text/javascript" src="/resources/board/js/boardView.js" charset="utf-8"></script>
	<script async charset="utf-8" src="//cdn.embedly.com/widgets/platform.js"></script>
</head>
<body>
	<!-- 네비게이션 바 -->
	<div id="nav-placeholder"></div>
	
	<div class="container">
		<div>
			${category.categoryName}
		</div>
		<div id="postTitle" class="row justify-content-left">
			<h3>
				${post.subject}
			</h3>
		</div>
		<div class="row justify-content-left">
			<div class="form-inline">
				<label><b>작성자</b></label>
				<label style="margin-left:10px;">${post.nickname}</label>
				
				<label style="margin-left:20px;"><b>조회수</b></label>
				<label style="margin-left:10px;">${post.view}</label> 
				
				<fmt:formatDate var="createDate" pattern="yyyy-MM-dd HH:mm:ss" value="${post.createDate}"/>
				<fmt:formatDate var="updateDate" pattern="yyyy-MM-dd HH:mm:ss" value="${post.updateDate}"/>
				
				<label style="margin-left:20px;"><b>작성</b></label>
				<label style="margin-left:10px;">${createDate}</label>
				
				<label style="margin-left:20px;"><b>최종수정</b></label>
				<label style="margin-left:10px;">${updateDate}</label>
			</div>
		</div>
		<hr>
		<div id="postContent" class="row justify-content-left">
			<pre style="margin:0">${post.content}</pre>
		</div>
		<div id="likeRow" class="row justify-content-center">
			<input type="hidden" id="ifLike" value="${ifLike}">
			<a href="javascript:void(0)" onclick="likePost()">
				<img id="likeImg" src="" style="height: 60px;"/>
			</a>
			<br>
		</div>
		<div id="likeCntRow" class="row justify-content-center">
			<input type="text" id="likeCnt" value="${likeCnt}" style="background-color:rgba(0,0,0,0);border:none;text-align:center">
		</div>
		<hr>
		<label>첨부파일</label>
		<div>
			<c:forEach var="files" items="${files}" >
				<a href="/resources/uploads/${files.storedFileName}" download="${files.originFileName}">
				${files.originFileName} / 
				<c:choose>
					<c:when test="${files.fileSize*1024 < 1}">
						<fmt:formatNumber value="${files.fileSize*1024*1024}" pattern=".00"/>Byte
					</c:when>
					<c:when test="${files.fileSize < 1}">
						<fmt:formatNumber value="${files.fileSize*1024}" pattern=".00"/>KB
					</c:when>
					<c:otherwise>
						<fmt:formatNumber value="${files.fileSize}" pattern=".00"/>MB
					</c:otherwise>
				</c:choose>
				</a>
				<br>
			</c:forEach>
		</div>
		<hr>
		<!-- 버튼 기능 -->
		<div id="mainButtonRow" class="row justify-content-left">
			<input type="button" class="btn btn-secondary" value="목록" onclick="location.href='http://localhost:8080/cat/${post.categoryId}'"/>
			<!-- 작성자 본인만 사용하는 기능 -->
			<c:if test="${member.userId eq post.userId}">
				<input type="button" class="btn btn-secondary" style="margin-left:5px" value="수정" onclick="location.href='/editor/${post.boardId}/${post.categoryId}'"/>
				<input type="button" class="btn btn-secondary" style="margin-left:5px" value="삭제" onclick="deletePost()"/>
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
					<input type="hidden" name="userNum" id="userNum" value="${member.userNum}"/>
					<input type="hidden" name="nickname" id="nickname" value="${member.nickname}"/>
					<input type="hidden" name="categoryId" id="categoryId" value="${post.categoryId}"/>
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
	
	<!-- Footer -->
	<div id="footer-placeholder"></div>
	
	<script>
	    document.querySelectorAll( 'oembed[url]' ).forEach( element => {
	        // Create the <a href="..." class="embedly-card"></a> element that Embedly uses
	        // to discover the media.
	        const anchor = document.createElement( 'a' );
	
	        anchor.setAttribute( 'href', element.getAttribute( 'url' ) );
	        anchor.className = 'embedly-card';
	
	        element.appendChild( anchor );
	    } );
	</script>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
	
</body>
</html>