/**
 * 
 */
 
$(function(){
	$("#nav-placeholder").load("http://localhost:8080/navbar");
	var boardIdStr = document.getElementById("boardId").value;
	showAllComment(boardIdStr);
});

function deletePost(){
	var boardIdStr = document.getElementById("boardId").value;
	if (confirm('정말 삭제하시겠습니까?')){
	    $.ajax({
	        url : "/board/"+boardIdStr,
	        type : "delete",
	        success : function(data){
	  			window.location.href = "http://localhost:8080/"
	        },
			error:function(xhr,status,error){
				console.log('error:'+status);
			}
	    });
    }
}


function addComment(){
    var boardIdStr = document.getElementById("boardId").value;
    var userIdStr = document.getElementById("userId").value;
    var commentContentStr = document.getElementById("commentContent").value;
    
    $.ajax({
        url : "/comment",
        type : "POST",
        data : JSON.stringify({
	        		userId:userIdStr, 
	        		commentContent: commentContentStr, 
	        		boardId:boardIdStr
				}),
        contentType: 'application/json',
        success : function(data){
        	$("#commentContent").val("");
        	showHtml(data);    	
        },
		error:function(xhr,status,error){
			console.log('error:'+error);
		}
    });
}


function showAllComment(boardIdStr){
    $.ajax({
        url : "/comment/"+boardIdStr,
        success : function(data){
        	showHtml(data);    	
        },
		error:function(xhr,status,error){
			console.log('error:'+error);
		}
    });
}

function triggerBox(cocoid){
	$("#cocobox"+cocoid).toggle();
}

function addCoComment(cocoid){
	var boardIdStr = document.getElementById("boardId").value;
    var userIdStr = document.getElementById("userId").value;
	var commentContentStr = document.getElementById("commentContentOf"+cocoid).value;
	var commentIdStr = cocoid;
	var groupIdStr = document.getElementById("groupIdOf"+cocoid).value;
	var depthStr = document.getElementById("depthOf"+cocoid).value;
	var orderNoStr = document.getElementById("orderNoOf"+cocoid).value;
	
	console.log("commentId: " + commentIdStr + " ,groupId: " + groupIdStr + " ,depthId: " + depthStr + " ,orderNo: " + orderNoStr
					+ " ,commentContent: " + commentContentStr);
	
	$.ajax({
        url : "/cocomment",
        type : "POST",
        data : JSON.stringify({
        						boardId: boardIdStr,
        						userId: userIdStr,
        						commentContent: commentContentStr,
        						parentCommentId: commentIdStr,
        						groupId: groupIdStr,
        						depth: depthStr,
        						orderNo: orderNoStr
        						}),
        contentType: 'application/json',
        success : function(data){
        	showHtml(data);
        },
		error:function(xhr,status,error){
			console.log('error:'+error);
		}
    });
}

function editCommentShow(commentId, commentContent){
	$('#editCommentModal').modal('show');
	console.log(commentContent);
	document.getElementById("editedContent").value = commentContent;
	document.getElementById("editId").value = commentId;
	
}

function editComment(){
	var commentIdStr = document.getElementById("editId").value;
	var commentContentStr = document.getElementById("editedContent").value;
	var boardIdStr = document.getElementById("boardId").value;
	
	$.ajax({
        url : "/editComment",
        type : "POST",
        data : JSON.stringify({
        						commentId: commentIdStr,
        						commentContent: commentContentStr,
        						boardId: boardIdStr
        						}),
        contentType: 'application/json',
        success : function(data){
        	showHtml(data);
        	$('#editCommentModal').modal('hide');
        },
		error:function(xhr,status,error){
			console.log('error:'+error);
		}
    });
}

function showHtml(data) {
        var html = "<table class='table table-hover table-fixed'><tbody style='text-align:left'>";
        var userIdStr = document.getElementById("userId").value;
        
        $.each(data, function(i) {
            html += "<tr>";
            html += 	"<td style='width:900px;padding-left:" + data[i].depth*2 + "em'>";
            html += 		data[i].userId + '<br>';
            html +=			data[i].commentContent + '<br>';
            html +=			"<a href='javascript:void(0);' onclick='triggerBox("+ data[i].commentId +");'>+답글</a>";  
            
	        if (userIdStr == data[i].userId){
	        	html +=		"<a href='javascript:void(0);' onclick='editCommentShow("+ data[i].commentId + ", `"+ data[i].commentContent +"`)'>수정</a>";
	       		html +=		"<a href='javascript:void(0);' onclick='deleteCoComment()'>삭제</a>";
	        }
        
            html +=			"<div style='display:none' id='cocobox"+ data[i].commentId +"'>";
            html += 			"<input type='text' class='form-control' id='commentContentOf" + data[i].commentId + "'/>";
            html +=				"<input type='button' value='답글작성' onclick='addCoComment(" + data[i].commentId + ")'/>"
            html +=			"</div>";
            html +=			"<div style='display:none'>";
            html += 			"<input type='hidden' value='" + data[i].commentId + "' id='commentIdOf" + data[i].commentId + "' />";
            html += 			"<input type='hidden' value='" + data[i].groupId + "' id='groupIdOf" + data[i].commentId + "' />";
            html += 			"<input type='hidden' value='" + data[i].depth + "' id='depthOf" + data[i].commentId + "' />";
            html +=				"<input type='hidden' value='" + data[i].orderNo + "' id='orderNoOf" + data[i].commentId + "' />";
            html +=			"</div>";
            html += 	"</td>";
            html += "</tr>";
        });
        html += "</tbody></table>";
		
		$("#showComment").html(html);
}
