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
                <h2>Upload and detect objects on your image</h2>
                <p>
                    It supports only images with .jpg extension. The uploaded file cannot be larger than 300KB. The optimal resolution
                    is 416x416, because the neural network resize the image in the pre-processing phase.
                </p>
            </div>
            <div class="row">
                <form method="POST" enctype="multipart/form-data" action="/lab">
                    <table class="table table-bordered">
                        <tbody>
                            <tr>
                                <td>File to upload:</td>
                                <td><input type="file" name="file"/></td>
                                <td><input type="submit" value="Upload and recognize"/></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div class="row">
                <div class="alert alert-info" role="alert">
                    <strong>Important!</strong> The upload directory is cleaned up in each hour.
                </div>
            </div>
            <div class="row">
                <p>
                    This program recognizes the following objects:
                </p>
                <table class="table table-bordered">
                    <tbody>
                     <tr>
                         <td>
                             <ul>
                                 <li>aeroplane</li>
                                 <li>bicycle</li>
                                 <li>bird</li>
                                 <li>boat</li>
                                 <li>bottle</li>
                                 <li>bus</li>
                                 <li>car</li>
                                 <li>cat</li>
                                 <li>chair</li>
                                 <li>cow</li>
                             </ul>
                         </td>
                         <td>
                             <ul>
                                 <li>diningtable</li>
                                 <li>dog</li>
                                 <li>horse</li>
                                 <li>motorbike</li>
                                 <li>person</li>
                                 <li>pottedplant</li>
                                 <li>sheep</li>
                                 <li>sofa</li>
                                 <li>train</li>
                                 <li>tvmonitor</li>
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