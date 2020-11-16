<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<title>게시글 작성</title>
	<style>
		.ck-editor__editable{
			min-height: 500px;
		}
		.progress{
			position:relative;
			width: 100%; 
			border: 1px solid #ddd; 
			padding: 1px;
			border-radius: 3px;
		}
		.bar{
			background-color: #337ab7;
			width:0%;
			height:20px;
			border-radius: 3px;
		}
		.percent{
			position:absolute;
			display:inline-block;
			top:1px;
			left:48%;
		}
		
	</style>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script type="text/javascript" src="/resources/board/js/boardWrite.js" charset="utf-8"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/23.1.0/classic/ckeditor.js"></script>
</head>

<body>
	<form action="doCreate" method="post" name="formm" id="formm" enctype="multipart/form-data" onsubmit="uploadFile()">
		<div class="form-group">
			<label for="subject">제목</label>
			<input type="text" class="form-control" name="subject" id="subject"/>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control" id="content" name="content"></textarea>
			<script>
			    ClassicEditor
			        .create( document.querySelector( '#content' ) )
			        .catch( error => {
			            console.error( error );
			        });
			</script>
		</div>
		
		<div class="hidden">
			<input type="hidden" name="userId" id="userId" value="${member.getUserId()}"/><br/>	
		</div>
		
		<div>
			<table class="table" style="width: 100%;border:1px">
				<tbody id="fileTableTbody">
					<tr>
						<td id="dropZone">
							파일을 드래그 하세요
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="form-group">
			<input class="btn btn-primary" type="submit" value="글쓰기"/>
		</div>
		
	</form>
	
	<div class="progress">
		<div class="bar"></div>
		<div class="percent">0%</div>
	</div>
	<div id="status"></div>
	
	
	
	<!-- 스트립트 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</body>

</html>