/**
 * 
 */

jQuery(document).ready(function($) {
	$("#nav-placeholder").load("http://localhost:8080/navbar");
	$("#footer-placeholder").load("http://localhost:8080/footer");
	
});

setInterval(function(){
	var predImg = document.getElementById("predictedImg");
	var srcStr = document.getElementById("predictedSrc").value;
	
	predImg.src = srcStr;
	predImg.style.width = '416px';
}, 4000)