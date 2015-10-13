/**
 * 
 */

function enableCommentInput(textBoxId){
	console.log(textBoxId);
	document.getElementById(textBoxId).readOnly = false;
	document.getElementById(textBoxId).focus();
}