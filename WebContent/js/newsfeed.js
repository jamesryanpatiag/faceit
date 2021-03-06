/**
 * 
 */
$( document ).ready(function() {
window.globalid = 0;
	$(function() {
        $('textarea').each(function() {
            $(this).height($(this).prop('scrollHeight'));
        });
    });
	
	document.body.onmousedown = function (e) {
		 e = e || window.event;
		 var elementId = (e.target || e.srcElement).id;
		 if (elementId != globalid){
			 document.getElementById(globalid).className = "comment-input";
			 document.getElementById(globalid).readOnly = true;
			 document.getElementById(globalid).style.outline = "none";
		 }
	}
});

function enableCommentInput(textBoxId){
	window.globalid = textBoxId;
	document.getElementById(textBoxId).readOnly = false;	
	var element = $('#'+textBoxId);
	var strlen = element.val().length * 2;
	element.focus();
	element[0].setSelectionRange(strlen, strlen);
	element[0].style.outline = "1px solid rgba(81, 203, 238, 1)";
}

function enablePostInput(textBoxId){
	window.globalid = textBoxId;
	document.getElementById(textBoxId).readOnly = false;
	var element = $('#'+textBoxId);
	var strlen = element.val().length * 2;
	element.focus();
	element[0].setSelectionRange(strlen, strlen);
	element[0].style.outline = "1px solid rgba(81, 203, 238, 1)";
}
