<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>관리자 대시보드</title>
	
	<!-- AJAX -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/css/adminDashboard.css">
	
	<!-- Script -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
	
	<script type="text/javascript" src="/resources/admin/js/adminDashboard.js" charset="utf-8"></script>
	
</head>
<body>
	<!-- 네비게이션 바 -->
	<div id="nav-placeholder"></div>
	
	<div class="container" style="min-height:560px">
		<div id="boardTitle" class="row justify-content-left">
			<h3>
				통계 대시보드 <br>
			</h3>
			<hr>
		</div>
		<div class="row justify-content-center" style="min-height:300px; margin-top:30px;">
			<div class="col">
				<div class="card" style="width:100%;">
				  	<h5 class="card-header">회원수 현황</h5>
				  	<div class="card-body">
					    <canvas id="memberCntLineChart"></canvas>	
				  	</div>
				</div>
			</div>
			<div class="col-3">
				<div class="card" style="width:100%;">
				  	<h5 class="card-header">회원성별 비율</h5>
				  	<div class="card-body">
					    <canvas id="memberGenderPieChart"></canvas>	
				  	</div>
				</div>
			</div>
		</div>
		<div class="row justify-content-center" style="min-height:300px; margin-top:30px;">
			<div class="col">
				<div class="card" style="width:100%;">
				  	<h5 class="card-header">카테고리별 게시글 수 현황</h5>
				  	<div class="card-body">
					    <canvas id="boardBarChart"></canvas>	
				  	</div>
				</div>
			</div>
			<div class="col">
				<div class="card" style="width:100%;">
				  	<h5 class="card-header">카테고리별 조회수 현황</h5>
				  	<div class="card-body">
					    <canvas id="viewBarChart"></canvas>	
				  	</div>
				</div>
			</div>
		</div>
		
	</div> <!--End of Container  -->
	
	<!-- Footer -->
	<div id="footer-placeholder"></div>
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>