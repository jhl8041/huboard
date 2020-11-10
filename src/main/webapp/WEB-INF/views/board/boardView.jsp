<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>Insert title here</title>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
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
	<div class="form-group">
		<div class="form-inline">
			<label for="commentTextArea">댓글작성</label>
			<textarea class="form-control" id="commentTextArea" rows="3"></textarea>
			<input class="btn btn-primary" style="margin-top:10px" type=button value=회원가입 onclick="addComment()">
		</div>
	</div>
	<table class="table table-hover table-fixed">									
		<thead >
			<tr style="text-align:center">
				<th>작성자</th>
				<th>내용</th>
				<th>작성시간</th>
			</tr>
		</thead>
		<tbody style="text-align:center">
<%-- 			<c:forEach var="comment" items="${list_c}" > --%>
				<tr>
					<td>${post.userId}</td>
					<td style="width:600px;text-align:left"><b>${post.content}</b></td>
					<td>${post.date}</td>
				</tr>
			<%-- </c:forEach> --%>
		</tbody>
	</table>
	<input type="button" value="수정" onclick="location.href='http://localhost:8080/edit?id=${post.boardId}'"/>
	<input type="button" value="목록" onclick="location.href='http://localhost:8080/'"/>
	<input type="button" value="삭제" onclick="location.href='http://localhost:8080/delete?id=${post.boardId}'"/>
	
	
</body>
</html>