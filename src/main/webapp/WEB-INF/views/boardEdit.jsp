<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="proceed_edit" method="post" name="form">
		<input style="display: none"  name="boardId" value="${post.boardId}"/>
		글제목: <input name="subject" value="${post.subject}"/><br/>
		내용: <input name="content" value="${post.content}"/><br/>
		아이디: <input name="userId" value="${post.userId}"/><br/>
		이름: <input name="userName" value="${post.userName}"/><br/>
		<input type="submit" value="수정"/>	
	</form>
</body>
</html>