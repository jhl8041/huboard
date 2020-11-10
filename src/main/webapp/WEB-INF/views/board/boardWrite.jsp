<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<title>회원가입</title>
</head>

<body>
	<form action="add" method="post" name="form">
		글제목: <input name="subject"/><br/>
		내용: <input name="content"/><br/>
		<input type="hidden" name="userId" value="${member.getUserId()}"/><br/>
		<input type="submit" value="글쓰기"/>
	</form>
</body>

</html>