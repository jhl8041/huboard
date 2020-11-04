<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	
	<title>Insert title here</title>
	<script>
		
	</script>
	
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr style="text-align:center">
				<th scope="col">��ȣ</th>
				<th scope="col">����</th>
				<th scope="col">����</th>
				<th scope="col">�ۼ��ð�</th>
				<th scope="col">�ۼ���</th>
				<th scope="col">��ȸ��</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="list" items="${list}" >
				<tr style="text-align:center" class='clickable-row' data-href='http://localhost:8080/view?id=${list.boardId}'>
					<td>${list.boardId}</td>
					<td>${list.subject}</td>
					<td>${list.content}</td>
					<td>${list.date}</td>
					<td>${list.userId}</td>
					<td>${list.view}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/write'">�۾���</button>
	
	<!-- ��Ʈ��Ʈ -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
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