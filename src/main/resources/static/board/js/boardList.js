/**
 * 
 */

jQuery(document).ready(function($) {
	$("#nav-placeholder").load("http://localhost:8080/navbar");
	$("#footer-placeholder").load("http://localhost:8080/footer");

    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });
    
    var pageSizeStr = $("#currSize").val();
    if (pageSizeStr){
    	$("#pageSelect").val(pageSizeStr).prop("selected", true);
    }
    else{
    	$("#pageSelect").val("10").prop("selected", true);
    }
    
    var curr = document.getElementById("currPage").value;
    $('.page-item').removeClass('active');
    $('#page'+curr).addClass('active');
    
});

function boardSearch(){
	var keywordStr = document.getElementById("keyword").value;
	var searchTypeStr = document.getElementById("searchType").value;
	var categoryIdStr = document.getElementById("categoryId").value;
	var currSize = document.getElementById("currSize").value;
	
	if (keywordStr == ""){
    	$("#keyword").focus();
 		return alert("검색어를 입력해주세요");
 	}
	
	window.location.href = "http://localhost:8080/search/"+ categoryIdStr +"/"+keywordStr+"/"+searchTypeStr+"?size="+ currSize +"&page=0";
}

function changePagePer(){
	var url = location.protocol + '//' + location.host + location.pathname;
	var sizeStr = document.getElementById("pageSelect").value;
	window.location.href = url + "?size=" + sizeStr + "&page=0";
}