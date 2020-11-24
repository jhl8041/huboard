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
		
		<!-- Script -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lab/js/labUpload.js" charset="utf-8"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
    </head>
    <body>
        <!-- 네비게이션 바 -->
		<div id="nav-placeholder"></div>
		
        <div class="container">
            <div class="row mt-4">
                <h2>인공지능 사물인식</h2>
                <p>
                    아래 명시된 20가지의 물체를 한 사진에서 파악할 수 있습니다.
                    이미지는 .jpg 형식이어야합니다.
                </p>
            </div>
            <div class="row">
                <form method="POST" enctype="multipart/form-data" action="/lab">
                    <table class="table table-bordered">
                        <tbody>
                            <tr>
                                <td>사진 업로드:</td>
                                <td><input type="file" name="file"/></td>
                                <td><input type="submit" value="분석하기"/></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div class="row">
                <p>
                    인식가능한 물체
                </p>
                <table class="table table-bordered">
                    <tbody>
                     <tr>
                         <td>
                             <ul>
                                 <li>비행기</li>
                                 <li>자전거</li>
                                 <li>새</li>
                                 <li>보트</li>
                                 <li>물병</li>
                                 <li>버스</li>
                                 <li>차</li>
                                 <li>고양이</li>
                                 <li>의자</li>
                                 <li>소</li>
                             </ul>
                         </td>
                         <td>
                             <ul>
                                 <li>식탁</li>
                                 <li>개</li>
                                 <li>말</li>
                                 <li>오토바이</li>
                                 <li>사람</li>
                                 <li>화분</li>
                                 <li>양</li>
                                 <li>소파</li>
                                 <li>기차</li>
                                 <li>모니터</li>
                             </ul>
                         </td>
                     </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
        <!-- 스트립트 -->
   		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>