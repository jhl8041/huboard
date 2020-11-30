/**
 * 
 */
 
 jQuery(document).ready(function($) {
 	$("#nav-placeholder").load("http://localhost:8080/navbaradmin");
 	$("#footer-placeholder").load("http://localhost:8080/footer");
 });
 
 function editCategoryName(categoryId){
 	var categoryNameEditStr = document.getElementById("categoryNameEdit"+categoryId).value;
    
    if (categoryNameEditStr ==""){
    	alert("카테고리명을 입력해주세요");
    	return false;
    }
    
    if (confirm(categoryNameEditStr+"로 수정하시겠습니까?")){
		$.ajax({
	        url : "/admin/category/"+categoryId,
	        type : "POST",
	        data : JSON.stringify({
	        			categoryName: categoryNameEditStr
					}),
	        contentType: 'application/json',
	        success : function(data){
	        	window.location = "http://localhost:8080/admin/admincontrol"; 	
	        },
			error:function(xhr,status,error){
				console.log('error:'+error);
			}
	    });
    }
}

function deleteCategory(categoryId){
	if (confirm("정말 삭제하시겠습니까?")){
		window.location = "http://localhost:8080/admin/category/"+categoryId; 
	}
}

function unlockMember(userNum){
	if (confirm("잠금을 해제하시겠습니까?")){
		window.location = "http://localhost:8080/admin/unlock/"+userNum; 
	}
}