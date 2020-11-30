<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>휴머스보드 홈</title>
	
	<!-- AJAX -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/board/css/home.css">
	
	<!-- Script -->
	<script type="text/javascript" src="/resources/board/js/home.js" charset="utf-8"></script>
	
</head>
<body>
	<!-- 네비게이션 바 -->
	<c:set var="categoryList" value="${category}" scope="session" />
	<div id="nav-placeholder"></div>
	
	<c:set var="listCnt" value="0" scope="page"/>
	<jsp:useBean id="now" class="java.util.Date" />
	<fmt:parseNumber value="${now.time}" integerOnly="true" var="currentDate"></fmt:parseNumber>
	
	<div class="container" style="min-height:560px">
		<c:forEach var="row" begin="1" end="${catRowCol.getRow()}">
			<div class="row justify-content-center" style="min-height:300px; margin-top:30px">
			
			<c:forEach var="col" begin="1" end="${catRowCol.getCol()}" >
				<div class="col">
					<c:if test="${not empty category[listCnt]}">
					<div>
						<a href="/${category[listCnt].categoryId}">${category[listCnt].categoryName}</a>
					</div>
					<!-- 게시글 리스트 -->
					<table class="table table-hover" style="table-layout: fixed">
						<c:if test="${not empty catRowCol.catList[listCnt]}">
							<tbody>
								<c:forEach var="list" items="${catRowCol.catList[listCnt]}" >
									<tr class='clickable-row' id='boardlist' data-href='http://localhost:8080/board/${list.boardId}'>
										<td style="text-align:left; padding-top:8px; padding-bottom:8px">
											<fmt:parseNumber value="${list.updateDate.time}" integerOnly="true" var="postDate"></fmt:parseNumber>
											&nbsp;
											${fn:substring(list.subject,0,18)}
											<c:if test="${fn:length(list.subject) gt 18}">
											..
											</c:if>
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
						<c:if test="${empty catRowCol.catList[listCnt]}">
							<tbody>
								<tr class="table-secondary" style="text-align:center">
									<td>게시글이 존재하지 않습니다</td>
								<tr>
							</tbody>
						</c:if>
					</table> <!-- End of Table  -->
					
					<c:set var="listCnt" value="${listCnt+1}"/>
					</c:if>
				</div> <!-- End of Col  -->
			</c:forEach> <!-- End of Col loop  -->
			
			</div> <!-- End of Row  -->
		</c:forEach> <!-- End of Row loop  -->
	</div>
	
	<!-- Footer -->
	<div id="footer-placeholder"></div>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>