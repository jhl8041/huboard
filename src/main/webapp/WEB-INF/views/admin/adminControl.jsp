<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
	
	<!-- AJAX -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/css/adminControl.css">
	
	<!-- Script -->
	<script type="text/javascript" src="/resources/admin/js/adminControl.js" charset="utf-8"></script>
	
</head>
<body>
	<!-- 네비게이션 바 -->
	<c:set var="categoryList" value="${category}" scope="session" />
	<div id="nav-placeholder"></div>
	
	<div class="container" style="min-height:560px">
		<div id="boardRest" class="row justify-content-center">
			<h3>게시판 카테고리 관리</h3>
			<hr>
			<!-- 게시글 리스트 -->
			<table class="table table-hover" style="table-layout: fixed">
				<thead>
					<tr style="text-align:center">
						<th style="width: 10%" scope="col">번호</th>
						<th style="width: 40%" scope="col">카테고리</th>
						<th style="width: 30%" scope="col">수정명</th>
						<th style="width: 10%" scope="col">수정</th>
						<th style="width: 10%" scope="col">삭제</th>
					</tr>
				</thead>
				<c:if test="${not empty category}">
					<tbody>
						<c:forEach var="category" items="${category}" >
							<tr style="text-align:center;">
								<td>${category.categoryId}</td>
								<td>${category.categoryName}</td>
								<td><input class="form-control" id="categoryNameEdit${category.categoryId}" type="text"></td>
								<td><input class="btn btn-primary" type="button" value="수정" onclick="editCategoryName(${category.categoryId})"></td>
								<td><input class="btn btn-danger" type="button" value="삭제" onclick="deleteCategory(${category.categoryId})"></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
				<c:if test="${empty category}">
					<tbody>
						<tr class="table-secondary" style="text-align:center">
							<td colspan="5">카테고리가 존재하지 않습니다</td>
						<tr>
					</tbody>
				</c:if>
			</table>
			<hr>
			<div class="justify-content-center" style="margin-top:20px">
				<div>
					<small>새로운 카테고리 등록</small>
				</div>
				<div class="form-inline">
					<form method="post" action="/admin/category" onsubmit="return confirm('등록하시겠습니까?')">
						<input class="form-control" type="text" name="categoryName" placeholder="등록할 카테고리명을 입력하세요" style="width:400px" required>
						<input class="btn btn-primary" type="submit" value="등록">
					</form>
				</div>	
			</div>
		</div> <!-- End of Row -->
		
		<div id="boardRest" class="row justify-content-center" style="margin-top:100px">
			<h3>잠긴회원 관리</h3>
			<hr>
			<!-- 게시글 리스트 -->
			<table class="table table-hover" style="table-layout: fixed">
				<thead>
					<tr style="text-align:center">
						<th style="width: 10%" scope="col">번호</th>
						<th style="width: 30%" scope="col">아이디</th>
						<th style="width: 25%" scope="col">이름</th>
						<th style="width: 20%" scope="col">닉네임</th>
						<th style="width: 15%" scope="col">잠금해제</th>
					</tr>
				</thead>
				<c:if test="${not empty lockedMember}">
					<tbody>
						<c:forEach var="member" items="${lockedMember}" >
							<tr style="text-align:center;">
								<td>${member.userNum}</td>
								<td>${member.userId}</td>
								<td>${member.userFullName}</td>
								<td>${member.nickname}</td>
								<td><input class="btn btn-danger" type="button" value="잠금해제" onclick="unlockMember(${member.userNum})"></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
				<c:if test="${empty lockedMember}">
					<tbody>
						<tr class="table-secondary" style="text-align:center">
							<td colspan="5">잠긴회원이 존재하지 않습니다</td>
						<tr>
					</tbody>
				</c:if>
			</table>
		</div> <!-- End of Row -->
		
	</div> <!-- End of Container -->
	
	<!-- Footer -->
	<div id="footer-placeholder"></div>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>