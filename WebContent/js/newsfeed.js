/**
 * 
 */
$( document ).ready(function() {
	$(function() {
        $('textarea').each(function() {
            $(this).height($(this).prop('scrollHeight'));
        });
        $('input[type=text]').each(function() {
            $(this).height($(this).prop('scrollHeight'));
        });
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

function adjustHeight(o) {
	console.log(o);
	var height = document.getElementById(o).innerHTML.offsetHeight;
	console.log(height);
	document.getElementById(o).cols = height;
}