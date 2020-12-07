/**
 * 
 */

jQuery(document).ready(function($) {
	$("#nav-placeholder").load("http://localhost:8080/navbar");
	$("#footer-placeholder").load("http://localhost:8080/footer");
	
	(function fadeInDiv(){
	    var divs = $('.fadeIn');
	    var divsize = ((Math.random()*100) + 50).toFixed();
	    var posx = (Math.random() * ($(document).width() - divsize)).toFixed();
	    var posy = (Math.random() * ($(document).height() - divsize)).toFixed();
	    var maxSize = 50;
	    var minSize = 20;
	    var size = (Math.random()*maxSize+minSize)
	
	    var elem = divs.eq(Math.floor(Math.random()*divs.length));
	
	    if (!elem.is(':visible')){
	        elem.fadeIn(Math.floor(Math.random()*1000),fadeInDiv);
	        elem.css({
	            'position':'absolute',
	            'left':posx+'px',
	            'top':posy+'px',
	            'font-size': size+'px'
	        });
	    } else {
	        elem.fadeOut(Math.floor(Math.random()*1000),fadeInDiv); 
	    }
	})();
});

function loading(){
	
}