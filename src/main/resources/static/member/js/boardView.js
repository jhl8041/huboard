/**
 * 
 */
 
 $(function(){
    
    showAllComment();
    
});
 
function addComment(){
    var boardIdStr = document.getElementById("boardId").value;
    var userIdStr = document.getElementById("userId").value;
    var commentContentStr = document.getElementById("commentContent").value;
    
    $.ajax({
        url : "/doComment",
        type : "POST",
        data : JSON.stringify({userId:userIdStr, commentContent: commentContentStr,  boardId:boardIdStr}),
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

function showAllComment(){
	var boardIdStr = document.getElementById("boardId").value;

    $.ajax({
        url : "/getComment",
        type : "POST",
        data : JSON.stringify({boardId:boardIdStr}),
        contentType: 'application/json',
        success : function(data){
        	showHtml(data);    	
        },
		error:function(xhr,status,error){
			console.log('error:'+error);
		}
    });
}

function showHtml(data) {
        var html = "<table class='table table-hover table-fixed'><tbody style='text-align:center'>";
        $.each(data, function(i) {
            html += "<tr>";
            html += "<td style='width:600px;text-align:left'>" 
            html += data[i].userId + '<br>' + data[i].commentContent;
            html += "</td>";
            html += "</tr>";
        });
        html += "</tbody></table>";
		
		$("#showComment").html(html);
        $("#commentContent").val("");
        $("#commentContent").focus();
}
