function disableBodyScroll() {
	$(document.body).css('position', 'absolute');
	$(document.body).css('overflow', 'hidden');
	console.log("hi")
}


function enableBodyScroll() {
	$(document.body).css('position', 'absolute');
	$(document.body).css('overflow', 'visible');
	console.log("Bye")
}

//var $html = $(document.documentElement);
//$html.css('overflow', '');