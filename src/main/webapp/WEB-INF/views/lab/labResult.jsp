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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/lab/css/labResult.css"></link>
	
	<!-- Script -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lab/js/labResult.js" charset="utf-8"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
</head>
<body>
    <!-- 네비게이션 바 -->
	<div id="nav-placeholder"></div>
	
    <div class="container" style="min-height:560px">
		<c:if test="${originalName}">
         <div class="row mt-4">
             <p>
                 <span>Image: </span><span>${originalName}</span><span> has been successfully uploaded!</span>
             </p>
         </div>
        </c:if>
        <div class="row">
        	<input type="hidden" id="predictedSrc" value="${predictedImage}"/>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>업로드한 이미지</th>
                        <th>결과 이미지</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td width="416"><img src="${originalImage}" title="${originalImage}" alt="${originalImage}" width="416" /></td>
                        <td width="416" style="text-align: center; vertical-align: middle;"><img id="predictedImg" src="" onerror="this.src='/resources/images/loading.gif'" width="200"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align:center">
                            <ul>
                                <c:forEach var="recognition" items="${recognitions}">
                                    <li><span>${recognition.title}</span><span> - </span><span>${recognition.confidence}</span></li>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="row justify-content-center">
	        <div class="form-group">	
				<input class="btn btn-primary" style="width:330px; margin-top:100px" type=button value=다시하기 onclick="location.href='/lab'">
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