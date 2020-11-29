/**
 * 
 */
 
jQuery(document).ready(function($) {
	$("#nav-placeholder").load("http://localhost:8080/navbar");
	$("#footer-placeholder").load("http://localhost:8080/footer");

    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });
});