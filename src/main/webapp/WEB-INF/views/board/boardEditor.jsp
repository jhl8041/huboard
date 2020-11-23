<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<title>게시글 작성</title>

	<!-- JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/board/css/boardEditor.css"></link>
	
	<!-- Script -->
	<script type="text/javascript" src="/resources/board/js/boardEditor.js" charset="utf-8"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/23.1.0/classic/ckeditor.js"></script>
</head>
<body>
	<!-- 네비게이션 바 -->
	<div id="nav-placeholder"></div>
	
	<div class="container">
		<div id="boardTitle" class="row justify-content-left">
			<small class="text-muted">게시글 작성</small>
			<hr>
		</div>
		<div id="postTitle" class="row justify-content-left">
			<label for="subject">제목</label>
			<input type="text" class="form-control" name="subject" id="subject" value="${post.subject}"/>
		</div>
		<div id="postContent" class="row justify-content-left">
			<label for="content">내용</label>
			<textarea class="form-control" id="content" name="content">${post.content}</textarea>
			<script>
				ClassicEditor
				    .create(document.querySelector('#content'))
				    .then(editor => {
				    	theEditor = editor;
				    })
				    .catch(error => {
				        console.error( error );
				});
			</script>
		</div>
		
		<div class="hidden">
			<input type="hidden" name="userId" id="userId" value="${member.getUserId()}"/>
			<input type="hidden" name="userNum" id="userNum" value="${member.getUserNum()}"/>
			<input type="hidden" name="boardId" id="boardId" value="${post.boardId}"/>
			<input type="hidden" id="data" value="${data}"/>
		</div>
		
		<div id="postUpload" class="row justify-content-left">
			<label for="fileTable">첨부파일</label>
			<table class="table" id="fileTable" style="width: 100%;border:1px">
				<tbody id="fileTableTbody">
					<tr>
						<td id="dropZone">
							<div>
								파일을 드래그해서 업로드<br>
								<small>또는</small><br>
								<a href="javascript:void(0)" onclick="upload(); return false"><b>여기</b></a>를 눌러서 업로드
								<input name="upload" multiple="multiple" type="file" id="fileinput" onchange="handleFile(this.files)"/>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="form-group">
			<c:choose>
				<c:when test="${data}">
					<input class="btn btn-primary" type="button" value="수정하기" onclick="submitPost()"/>
				</c:when>
				<c:otherwise>
					<input class="btn btn-primary" type="button" value="글쓰기" onclick="submitPost()"/>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>

</html>