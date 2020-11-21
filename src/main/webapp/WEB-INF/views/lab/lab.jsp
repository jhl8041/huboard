<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
		
	<!-- JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/lab/css/lab.css"></link>
	
	<!-- Script -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lab/js/lab.js" charset="utf-8"></script>
	<!-- Load TensorFlow.js. This is required to use coco-ssd model. -->
	<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs"> </script>
	<!-- Load the coco-ssd model. -->
	<script src="https://cdn.jsdelivr.net/npm/@tensorflow-models/coco-ssd"> </script>
	<title>실험실</title>
</head>
<body>
	<!-- 네비게이션 바 -->
	<div id="nav-placeholder"></div>
	
	<div class="container">
		<div id="labTitle" class="row justify-content-left">
			<h3>
				실험실<br>
				<small class="text-muted">사진실험실입니다</small>
			</h3>
			<hr>
		</div>
		<div class="row justify-content-center">
			<img id="labImg" src="noimg.jpg" onerror="this.src='/resources/images/noimg.jpg';"/>
		</div>
		<div class="row justify-content-center">
			<input type="file" class="form-control-file" id="exampleFormControlFile1">
		</div>
		<div class="row justify-content-center">
			<textarea class="form-control" id="content" name="content"></textarea>
		</div>
	</div>

	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>