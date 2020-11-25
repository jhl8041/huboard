<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
		
	<!-- JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/board/css/boardList.css"></link>
	
	<!-- Script -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/board/js/boardList.js" charset="utf-8"></script>
	
	<title>휴머스보드 홈</title>
</head>
<body>
	<!-- 네비게이션 바 -->
	<div id="nav-placeholder"></div>

	<div class="container">
		<div id="boardTitle" class="row justify-content-left">
			<h3>
				커뮤니티<br>
				<small class="text-muted">자유롭게 글을 쓰는 게시판입니다</small>
			</h3>
			<hr>
		</div>
		<div id="boardPageInfo" class="row justify-content-left">
			<div class="col" id="boardInfo">
				<p style="margin:0;padding-top:14px">
					<small>
						총 게시글 ${list.getTotalElements()}개,  페이지 (${list.getNumber()+1} / ${list.getTotalPages()})
					</small>
				</p>
			</div>
			<div class="col text-right" id="pageSelectCol">
				<select class="custom-select" id="pageSelect" onchange="changePagePer()">
				  	<option value="10">10개씩 보기</option>
				  	<option value="25">25개씩 보기</option>
				  	<option value="50">50개씩 보기</option>
				</select>
			</div>
			<input type="hidden" id="pageSize" value="${param.size}"/>
		</div>
		<jsp:useBean id="now" class="java.util.Date" />
		<fmt:parseNumber value="${now.time}" integerOnly="true" var="currentDate"></fmt:parseNumber>
		
		<div id="boardRest" class="row justify-content-center">
			<div id="tableArea" class="row justify-content-center">
				<!-- 게시글 리스트 -->
				<table class="table table-hover" style="table-layout: fixed">
					<thead>
						<tr style="text-align:center">
							<th style="width: 7%" scope="col">번호</th>
							<th style="width: 50%" scope="col">제목</th>
							<th style="width: 23%" scope="col">작성시간</th>
							<th style="width: 10%" scope="col">작성자</th>
							<th style="width: 10%" scope="col">조회수</th>
						</tr>
					</thead>
					<c:if test="${list.isEmpty() eq false}">
						<tbody>
							<c:forEach var="list" items="${list.getContent()}" >
								<tr class='clickable-row' id='boardlist' data-href='http://localhost:8080/board/${list.boardId}'>
									<td>${list.boardId}</td>
									<td style="text-align:left">
										<fmt:parseNumber value="${list.updateDate.time}" integerOnly="true" var="postDate"></fmt:parseNumber>
										&nbsp;
										${list.subject}
										&nbsp; 
										[ ${list.commentCnt} ]
										&nbsp; 
										<c:if test="${currentDate/1000 - postDate/1000 lt 30}">
											<img alt="not found" src="/resources/images/new.png" style="width:15px; height:15px">
										</c:if>
									</td>
									<td><fmt:formatDate value="${list.updateDate}" pattern="yyyy-MM-dd"/></td>
									<td>${list.userId}</td>
									<td>${list.view}</td>
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
			<div class="row text-right">
				<div id="buttonArea" class="container">
					<div class="text-right" style="width:100%">
						<input type="button" class="btn btn-primary" onclick="location.href='/editor/0'" value="글쓰기"/>
					</div>
				</div>
			</div>
			<div id="paginavArea" class="row justify-content-center">
				<!-- 페이징을 위한 변수 설정 -->
				<c:if test="${list.isEmpty() eq false}">
					<c:set var="paginav_len" value="5"/>
				   	<c:set var="paginav_section" value="${list.getNumber()/paginav_len - (list.getNumber()/paginav_len)%1}"/>
				   	
				   	<c:set var="start_page" value="${paginav_section * paginav_len}"/>
				   	<c:set var="end_page" value="${start_page + paginav_len - 1}"/>
				   	
				   	<c:if test="${end_page gt list.getTotalPages()}">
				   		<c:set var="end_page" value="${list.getTotalPages()-1}"/>
				   	</c:if>
				   	
				   	<c:choose>
				   		<c:when test="${start_page eq 0 }">
				   			<c:set var="disable_trig_prev" value="disabled"/>
				   		</c:when>
				   		<c:when test="${start_page ne 0}">
				   			<c:set var="disable_trig_prev" value=""/>
				   		</c:when>
				   	</c:choose>
				   	
				   	<c:choose>
				   		<c:when test="${end_page eq list.getTotalPages()-1}"> 
				   			<c:set var="disable_trig_next" value="disabled"/>
				   		</c:when>
				   		<c:when test="${end_page ne list.getTotalPages()-1}"> 
				   			<c:set var="disable_trig_next" value=""/>
				   		</c:when>
				   	</c:choose>
				   	
				   	<fmt:parseNumber var="previous" value="${start_page - 1}" integerOnly="true"/>
				   	<fmt:parseNumber var="next" value="${end_page + 1}" integerOnly="true"/>
				   	
				   	<!-- 페이징 구현 -->
					<nav aria-label="Page navigation example">
						<ul class="pagination">
					    	<li class="page-item ${disable_trig_prev}">
					      		<a class="page-link" href="?page=${previous}" tabindex="-1" aria-disabled="true">이전</a>
					    	</li>
					    	
					    	<c:forEach var="page" begin="${start_page}" end="${end_page}">
							    <li class="page-item"><a class="page-link" href="?page=${page}">${page+1}</a></li>
							</c:forEach>
					    
					    	<li class="page-item ${disable_trig_next}">
					      		<a class="page-link" href="?page=${next}">다음</a>
					    	</li>
					  	</ul>
					</nav>
				</c:if>
			</div>
			
			<!-- 검색창 -->
			<div id="searchArea" class="row justify-content-center">
		   		<div class="form-inline">
			    	<select class="custom-select" name="searchType" id="searchType">
						<option value="subject" selected>제목</option>
						<option value="userId">작성자</option>
						<option value="content">내용</option>
					</select>
			    	<input class="form-control mr-sm-2" name="keyword" id="keyword" type="search" placeholder="검색" aria-label="Search">
			    	<button class="btn btn-outline-success my-2 my-sm-0" onclick="boardSearch()">검색</button>
		    	</div>
	    	</div>
			
		</div>	
	</div> <!-- End container -->
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
   
</body>
</html>