/**
 * 
 */
 
 var fileIndex=0;
 var totalFileSize=0;
 var fileList=new Array();
 var fileSizeList = new Array();
 var uploadSize=50;
 var maxUploadSize=500;
 
 $(function(){
 	fileDropDown();
 });
 
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
 		dropZone.css('background-color','#FFFFFF');
 	});
 	
 	dropZone.on('dragover',function(e){
 		e.stopPropagation();
 		e.preventDefault();
 		dropZone.css('background-color','#E3F2FC');
 	});
 	
 	dropZone.on('drop',function(e){
 		e.preventDefault();
 		dropZone.css('background-color','#FFFFFF');
 		
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
 
// 업로드 파일 목록 생성
function addFileList(fIndex, fileName, fileSize){
    var html = "";
    html += "<tr id='fileTr_" + fIndex + "'>";
    html += "    <td class='left' >";
    html +=         fileName + " / " + fileSize + "MB "  + "<a href='#' onclick='deleteFile(" + fIndex + "); return false;' class='btn small bg_02'>삭제</a>"
    html += "    </td>"
    html += "</tr>"
 
    $('#fileTableTbody').append(html);
}
 
// 업로드 파일 삭제
function deleteFile(fIndex){
    totalFileSize -= fileSizeList[fIndex]; // 전체 파일 사이즈 수정
    delete fileList[fIndex]; // 파일 배열에서 삭제
    delete fileSizeList[fIndex]; // 파일 사이즈 배열 삭제
    $("#fileTr_" + fIndex).remove(); // 업로드 파일 테이블 목록에서 삭제
}
 
// 파일 등록
function uploadFile(){ 
    var uploadFileList = Object.keys(fileList); // 등록할 파일 리스트
 
    // 파일이 있는지 체크
    if(uploadFileList.length == 0){
        alert("파일이 없습니다.");
        return;
    }
    
    // 용량을 500MB를 넘을 경우 업로드 불가
    if(totalFileSize > maxUploadSize){
        alert("총 용량 초과\n총 업로드 가능 용량 : " + maxUploadSize + " MB"); // 파일 사이즈 초과 경고창
        return;
    }
            
    if(confirm("등록 하시겠습니까?")){
        // 등록할 파일 리스트를 formData로 데이터 입력
        var formData = new FormData($('formm2')[0]);
        for(var i = 0; i < uploadFileList.length; i++){
            formData.append('files', fileList[uploadFileList[i]]);
        }
            
        $.ajax({
            url:"/doUpload",
            data:formData,
            type:'POST',
            enctype:'multipart/form-data',
            processData:false,
            contentType:false,
            dataType:'json',
            cache:false,
            success:function(result){
                if(result.data.length > 0){
                    alert("성공");
                    location.reload();
                }else{
                    alert("실패");
                    location.reload();
                }
            }
        });
    }
}
