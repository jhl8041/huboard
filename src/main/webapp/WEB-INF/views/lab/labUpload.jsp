<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>휴보드 랩</title>
        
        <!-- JQuery -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		
		<!-- CSS -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/lab/css/labUpload.css"></link>
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.css"/>
		
		<!-- Script -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lab/js/labUpload.js" charset="utf-8"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>   
    </head>
    <body>
        <!-- 네비게이션 바 -->
		<div id="nav-placeholder"></div>
		
		<div class="fadeIn glow">강아지</div>
		<div class="fadeIn glow">고양이</div>
		<div class="fadeIn glow">비행기</div>
		<div class="fadeIn glow">새</div>
		<div class="fadeIn glow">보트</div>
		
		<div class="fadeIn glow">물병</div>
		<div class="fadeIn glow">버스</div>
		<div class="fadeIn glow">차</div>
		<div class="fadeIn glow">의자</div>
		<div class="fadeIn glow">소</div>
		
		<div class="fadeIn glow">식탁</div>
		<div class="fadeIn glow">말</div>
		<div class="fadeIn glow">오토바이</div>
		<div class="fadeIn glow">사람</div>
		<div class="fadeIn glow">화분</div>
		
		<div class="fadeIn glow">양</div>
		<div class="fadeIn glow">소파</div>
		<div class="fadeIn glow">기차</div>
		<div class="fadeIn glow">모니터</div>
		<div class="fadeIn glow">자전거</div>
		
        <div class="container" style="min-height:560px">
            <div class="row" style="margin-top:40%">
            	<div class="col">
	                <form method="POST" enctype="multipart/form-data" action="/lab" onsubmit="loading()">
	                	<h2>인공지능 TAG 추출</h2>
		                <p>
		                    20가지의 물체를 한 사진에서 파악할 수 있습니다. <br>
		                    이미지는 .jpg 형식이어야합니다.
		                </p>
		                <div class="form-group">
							<input type="file" class="form-control-file" name="file" id="exampleFormControlFile1" required>
						</div>
			                
			            <input class="btn btn-light btn-lg" type="submit" value="분석하기" style="margin-top:30px"/>
	                </form>
                </div>
                <div class="col">
					<img id="loading" style="display: none;" src="/resources/images/labloading.gif"/>
                </div>
            </div>
            <div class="row" style="min-height:300px">
            	
            </div>
          
            
        </div>
        
        <!-- Footer -->
		<div id="footer-placeholder"></div>
        
        <!-- 스트립트 -->
   		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>