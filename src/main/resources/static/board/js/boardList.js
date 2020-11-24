/**
 * 
 */

jQuery(document).ready(function($) {
	$("#nav-placeholder").load("http://localhost:8080/navbar");

    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });
    
    var pageSizeStr = $("#pageSize").val();
    if (pageSizeStr){
    	$("#pageSelect").val(pageSizeStr).prop("selected", true);
    }
    else{
    	$("#pageSelect").val("10").prop("selected", true);
    }
});

function boardSearch(){
	var keywordStr = document.getElementById("keyword").value;
	var searchTypeStr = document.getElementById("searchType").value;
	
	if (keywordStr == ""){
    	$("#keyword").focus();
 		return alert("검색어를 입력해주세요");
 	}
	
	window.location.href = "http://localhost:8080/search/"+keywordStr+"/"+searchTypeStr;
}

function changePagePer(){
	var sizeStr = document.getElementById("pageSelect").value;
	window.location.href = "http://localhost:8080/?size=" + sizeStr;
}