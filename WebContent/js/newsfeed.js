/**
 * 
 */
$( document ).ready(function() {
	var posts = document.getElementsByClassName('.post-input');
	console.log(posts);
	$('.post-input').each(function () {
		textAreaAdjust(this.id);
		console.log(this.id);
	});
	$('.comment-input').each(function () {
		textAreaAdjust(this.id);
		console.log(this.id);
	});
});

function enableCommentInput(textBoxId){
	document.getElementById(textBoxId).readOnly = false;
	document.getElementById(textBoxId).focus();
}

function enablePostInput(textBoxId){
	window.postId = textBoxId;
	document.getElementById(textBoxId).readOnly = false;
	document.getElementById(textBoxId).focus();
}

function textAreaAdjust(o) {
    o.style.height = "1px";
    o.style.height = (25+o.scrollHeight)+"px";
}