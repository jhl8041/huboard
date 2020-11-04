<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	${post.boardId}<br/>
	${post.subject}<br/>
	${post.content}<br/>
	${post.date }<br/>
	${post.userId }<br/>
	${post.view }<br/>
	
	<input type="button" value="수정" onclick="location.href='http://localhost:8080/edit?id=${post.boardId}'"/>
	<input type="button" value="목록" onclick="location.href='http://localhost:8080/'"/>
	<input type="button" value="삭제" onclick="location.href='http://localhost:8080/delete?id=${post.boardId}'"/>
</body>
</html>