<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script type="text/javascript" src="/resources/member/js/memberForm.js" charset="utf-8"></script>
	
	<title>Insert title here</title>
</head>
<body>
	<div class="d-flex justify-content-center">
		<form action="${pageContext.request.contextPath}/JoinController" method=post name=form id=form  onsubmit="return formCheck()">
			<div class="form-group">
				<h1>회원가입</h1>
			</div>
			<div class="form-group">
				<label>아이디</label>
				<input type=text class="form-control" style="width:300px" placeholder="아이디를 입력하세요" name=id id=id oninput='idCheck()'>				
				<div class="alert alert-danger collapse" role="alert" style=width:300px id=alertid>
					<span id="idcheck"></span>
				</div>
		  	</div>
		 </form>
	</div> 
	
	<!-- 스트립트 -->
	<!--  <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</body>
</html>