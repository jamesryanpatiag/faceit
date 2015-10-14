/**
 * 
 */
$( document ).ready(function() {
//	document.body.addEventListener('mouseup', function(){
//		if ($("#" + window.postId).is(':focus')) {
//			
//		}
//		else{
//			console.log(this.postId)
//			document.getElementById(window.postId).readOnly = false;
//			document.getElementById(window.postId).focus();
//			document.getElementById(window.postId + "btn").style.display = "none";
//		}
//	});
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
