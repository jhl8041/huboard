/**
 * 
 */
 
var fileIndex=0;
var totalFileSize=0;
var fileList=new Array();
var fileSizeList = new Array();
var uploadSize=1000;
var maxUploadSize=2000;

var isUploading = false;

var checkUnload = true;

jQuery(document).ready(function($) {
	$(window).on("beforeunload", function(){
	 	if(checkUnload){
			return "";
	 	}	
	});
	
	$(window).on("unload", function(){
	 	deletePost();
	});
	
	$("#nav-placeholder").load("http://localhost:8080/navbar");
	$("#footer-placeholder").load("http://localhost:8080/footer");
	
	var curr = document.getElementById("currCategoryId").value;
	$('#categorySelect'+curr).val(curr).prop("selected", true);
    
	fileDropDown();
});

function upload(){
	$("#fileinput").click();
}

function deletePost(){
	var boardIdStr = $('#boardId').val();
	$.ajax({
        url : "/board/"+boardIdStr,
        type : "delete",
        success : function(data){
        },
		error:function(xhr,status,error){
			console.log('error:'+status);
		}
    });
}

function fileDropDown(){
 	var dropZone = $("#dropZone");
 	
 	dropZone.on('dragenter',function(e){
 		e.stopPropagation();
 		e.preventDefault();
 		dropZone.css('background-color','#E3F2FC');
 	});
 	
 	dropZone.on('dragleave',function(e){
 		e.stopPropagation();
 		e.preventDefault();
 		dropZone.css('background-color','#e6e6e6');
 	});
 	
 	dropZone.on('dragover',function(e){
 		e.stopPropagation();
 		e.preventDefault();
 		dropZone.css('background-color','#E3F2FC');
 	});
 	
 	dropZone.on('drop',function(e){
 		e.preventDefault();
 		dropZone.css('background-color','#e6e6e6');
 		
 		var files = e.originalEvent.dataTransfer.files;
 		if(files != null){
 			if(files.length<1){
 				alert("폴더 업로드 불가");
 				return;
 			}
 			selectFile(files);
 		}
 		else{
 			alert("에러");
 		}
 	});
}

function selectFile(files){
    // 다중파일 등록
    if(files != null){
    	for(var i = 0; i < files.length; i++){
            var fileName = files[i].name; // 파일 이름
            var fileNameArr = fileName.split("\."); // 확장자
            var ext = fileNameArr[fileNameArr.length - 1];
            var fileSize = files[i].size / 1024 / 1024; // 파일 사이즈(단위 :MB)
                
            if($.inArray(ext, ['exe', 'bat', 'sh', 'java', 'jsp', 'html', 'js', 'css', 'xml']) >= 0){
                alert("등록 불가 확장자"); // 확장자 체크
                break;
            }else if(fileSize > uploadSize){
                alert("용량 초과\n업로드 가능 용량 : " + uploadSize + " MB"); // 파일 사이즈 체크
                break;
            }else{ 
                totalFileSize += fileSize; // 전체 파일 사이즈
                fileList[fileIndex] = files[i];  // 파일 배열에 넣기      
                fileSizeList[fileIndex] = fileSize; // 파일 사이즈 배열에 넣기      
                addFileList(fileIndex, fileName, fileSize); // 업로드 파일 목록 생성
                fileIndex++; // 파일 번호 증가
            }
        }  //end for
    }else{
        alert("ERROR");
    }
}
 
function getFileId(fileName, fIndex){
	totalFileSize -= fileSizeList[fIndex]; // 전체 파일 사이즈 수정
    delete fileList[fIndex]; // 파일 배열에서 삭제
    delete fileSizeList[fIndex]; // 파일 사이즈 배열 삭제
    
    $("#fileTr_" + fIndex).remove(); // 업로드 파일 테이블 목록에서 삭제

	$.ajax({
        url : "/filename/"+fileName,
        type : "POST",
        success : function(data){
        	console.log(data);
       		deleteFile(data);
        },
		error:function(xhr,status,error){
			console.log('error:'+error);
		}
    });
} 
 
// 업로드 파일 목록 생성
function addFileList(fIndex, fileName, fileSize){
	var bottom = document.body.scrollHeight;
	window.scrollTo({top:bottom, behavior:'smooth'});
	var fileSizeStr;
	
	if (fileSize>1){
		fileSizeStr = fileSize.toPrecision(4) + "MB";
	}
	else {
		fileSizeStr = Math.floor(fileSize*1024) + "KB"
	}

    var html = "";
    html += "<tr id='fileTr_" + fIndex + "'>";
    html += "    <td id='uploaded' class='left' >";
    html +=         fileName + " / " + fileSizeStr ;
    html +=         "<a href='javascript:void(0)' onclick='getFileId(`" + fileName + "`, `" + fIndex + "`)' class='btn small bg_02'>삭제</a>";
    html += 		"<div id='progress' style='min-height:25px' class='progress_"+fIndex+" progress'>";
	html +=			"	<div id='bar' role='progressbar' style='height:100%' aria-valuemin='0' aria-valuemax='100' class='bar_" + fIndex + " progress-bar progress-bar-striped progress-bar-animated'>";
	html +=			"	<div id='percent' style='position:inherit' class='percent_"+fIndex+"'>0%</div></div>";
	html += 		"</div>";
	html += 		"<div id='status"+fIndex+"'></div>";
    html += "    </td>";
    html += "</tr>";
    
    var uuid = uuidv4();
    $('#fileTableTbody').append(html);
    
    uploadFile(fileName, fileSize, fIndex);
}

function uuidv4() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}

//업로드 파일 DB에 등록
function addFileToDB(fileName, fileSize, storedName){
	var boardIdStr = document.getElementById("boardId").value;
	var userIdStr = document.getElementById("userId").value;
	var userNumStr = document.getElementById("userNum").value;
	var fileNameStr = fileName;
	var fileSizeStr = fileSize;
	var storedFileNameStr = storedName;
	
	$.ajax({
        url : "/file-db",
        type : "POST",
        data : JSON.stringify({
        	boardId: boardIdStr,
        	userId:userIdStr,
        	userNum:userNumStr,
        	originFileName: fileNameStr,
        	fileSize: fileSizeStr,
        	storedFileName: storedFileNameStr
        }),
        contentType: 'application/json',
        success : function(data){
        	//alert("db에 등록되었습니다");   	
        },
		error:function(xhr,status,error){
			console.log('error:'+error);
		}
    });
}
 
 
//파일 등록
function uploadFile(fileName, fileSize, fIndex){
    var uploadFileList = Object.keys(fileList); // 등록할 파일 리스트
 
    // 파일이 있는지 체크
    if(uploadFileList.length == 0){
        //alert("파일이 없습니다.");
        return;
    }
    
    // 용량을 500MB를 넘을 경우 업로드 불가
    if(totalFileSize > maxUploadSize){
        alert("총 용량 초과\n총 업로드 가능 용량 : " + maxUploadSize + " MB"); // 파일 사이즈 초과 경고창
        return;
    }
     
    // 등록할 파일을 formData로 데이터 입력
    var formData = new FormData($('formm')[0]);
    formData.append('file', fileList[uploadFileList[uploadFileList.length-1]]);
    
    var bar = $('.bar_'+fIndex);
    var percent = $('.percent_'+fIndex);
    var status = $('status_'+fIndex);
    
    isUploading = true;
    
    $.ajax({
    	xhr: function(){
    		var xhr = new window.XMLHttpRequest();
    		xhr.upload.addEventListener("progress", function(evt) {
    			if (evt.lengthComputable) {
    				var percentComplete = Math.floor((evt.loaded/evt.total)*100);
    				var percentVal = percentComplete + '%';
    				bar.width(percentVal);
    				percent.html(percentVal);	
    			}
    		}, false);
    		return xhr;
    	},
        url:"/file-server",
        data:formData,
        type:'POST',
        enctype:'multipart/form-data',
        processData:false,
        contentType:false,
        dataType:'json',
        cache:false,
        beforeSend:function(){
        	status.empty();
        	var percentVal = '0%';
        	bar.width(percentVal);
        	percent.html(percentVal);
        },
        success:function(result){
            addFileToDB(fileName, fileSize, result);
            isUploading = false;
        },
        error:function(request,status,error){
        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
        
    });
}


function submitPost(){
	checkUnload = false;
	
	var deleteFileListArr = [];
	
	var deleteFileListStr = document.getElementsByClassName('deleteFileList');
	for(i=0;i<deleteFileListStr.length;i++){
		deleteFileListArr.push(deleteFileListStr[i].value);
	}
	
	$.ajax({
        url : "/file-db",
        type : "DELETE",
        data : JSON.stringify({
        	deletefileList: deleteFileListArr
        }),
        contentType: 'application/json',
        success:function(xhr){
        },
		error:function(xhr,status,error){
			console.log('error:'+error);
		}
    });
	
	
	var categoryIdStr = document.getElementById("categorySelect").value;
	var boardIdStr = document.getElementById("boardId").value;
    var userIdStr = document.getElementById("userId").value;
    var userNumStr = document.getElementById("userNum").value;
    var subjectStr = document.getElementById("subject").value;
    var contentStr = theEditor.getData();
    var nicknameStr = document.getElementById("nickname").value;
    var data = (document.getElementById("data").value == 'true');
 	
 	if (isUploading){
 		return alert("파일이 전부 업로드 될때까지 기다려주세요 :)");
 	}
 	
 	if (subjectStr == ""){
 		$("#subject").focus();
 		return alert("제목을 입력해주세요");
 	}
 	else if (contentStr == ""){
 		$("#content").focus();
 		return alert("내용을 입력해주세요");
 	}
 	
	var urlStr,
		typeStr,
		successUrlStr;
	
	var dataStr={
			userNum: userNumStr,
			categoryId: categoryIdStr,
			boardId: boardIdStr,
			userId: userIdStr, 
    		subject: subjectStr, 
    		content: contentStr,
    		nickname: nicknameStr
	};
	
	//글쓰기
	if (!(data)){
		console.log("글쓰기중!");
		urlStr="/board";
		typeStr="post";
		successUrlStr = "http://localhost:8080/"+categoryIdStr;
	}
	//수정하기
	else {
		console.log("수정중!");
		urlStr="/board/"+boardIdStr;
		typeStr="patch";
		successUrlStr = "http://localhost:8080/board/"+boardIdStr;
	}
	
    $.ajax({
        url :  urlStr,
        type : typeStr,
        data : JSON.stringify(dataStr),
        contentType: 'application/json',
        success : function(xhr){
        	window.location.href = successUrlStr;
        },
		error:function(xhr){
			console.log(xhr.status);
			console.log(xhr.responseText);
		}
    });
}

//업로드 파일 DB에서 삭제
function deleteFile(fileId){

	$.ajax({
        url : "/file-db/"+fileId,
        type : "DELETE",
        success:function(xhr){
        	$("#row"+fileId).remove();
        },
		error:function(xhr,status,error){
			console.log('error:'+error);
		}
    });
}

function addDeleteList(fileId){
	if (confirm("정말 삭제하시겠습니까?")){
		var html = $("#hiddenValues").html();
		html += "<input type='hidden' class='deleteFileList' id='fileId" + fileId + "' value='"+ fileId +"'>";
		$("#row"+fileId).hide();
		$("#hiddenValues").html(html);
	}
}


