<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
	
	<!-- AJAX -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/member/css/memberLogin.css">
	
	<!-- Script -->
	<script type="text/javascript" src="/resources/member/js/memberJoin.js" charset="utf-8"></script>
	<script type="text/javascript" src="/resources/member/js/memberLogin.js" charset="utf-8"></script>
	
</head>
<body>
	<!-- 네비게이션 바 -->
	<div id="nav-placeholder"></div>
	
	<div class="container" style="min-height:560px">
		<div class="row justify-content-center">
			<div class="col">
				<div id="tableArea" class="row justify-content-center">
					<!-- 게시글 리스트 -->
					<table class="table table-hover" style="table-layout: fixed">
						<thead>
							<tr style="text-align:center">
								<th style="width: 50%" scope="col">제목</th>
							</tr>
						</thead>
						<c:if test="${list.isEmpty() eq false}">
							<tbody>
								<c:forEach var="list" items="${list.getContent()}" >
									<tr class='clickable-row' id='boardlist' data-href='http://localhost:8080/board/${list.boardId}'>
										<td style="text-align:left">
											<fmt:parseNumber value="${list.updateDate.time}" integerOnly="true" var="postDate"></fmt:parseNumber>
											&nbsp;
											${fn:substring(list.subject,0,18)}..
											&nbsp; 
											[ ${list.commentCnt} ]
											&nbsp; 
											<c:if test="${currentDate/1000 - postDate/1000 lt 30}">
												<img alt="not found" src="/resources/images/new.png" style="width:15px; height:15px">
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:if>
						<c:if test="${list.isEmpty() eq true}">
							<tbody>
								<tr class="table-secondary" style="text-align:center">
									<td colspan="6">게시글이 존재하지 않습니다</td>
								<tr>
							</tbody>
						</c:if>
					</table>
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col">
				
				
			</div>
		</div>
	</div>
	
	<!-- Footer -->
	<div id="footer-placeholder"></div>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>