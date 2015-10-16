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
	
	$(".post-input").keypress(function(e) {
	    var textVal = $(this).val();
	    if(e.keyCode == 13 && e.shiftKey) {

	    }
	    else if (e.keyCode == 13) {
	       e.preventDefault(); 
	       this.form.submit();
	    }
	});
	
	$(".comment-input").keypress(function(e) {
	    var textVal = $(this).val();
	    if(e.keyCode == 13 && e.shiftKey) {

	    }
	    else if (e.keyCode == 13) {
	       e.preventDefault(); 
	       this.form.submit(); 
	    }
	});
	
});


function likePost(){
	var post = $("textarea[name=post]").val();
	var postId = $("input[name=postId]").val();
	var action = $("input[name=hidden]").val();
	
	$.ajax({
      url: 'Newsfeed',
      type: 'POST',
      data: {
    	post: post,
        postId: postId,
        hidden: action
      },
      dataType: "text",
      success: function(result){
    	  $('#likePost').html();
      },
      error: function(xhr,status,error){
          alert("Error Saving Transaction");
        }
      });
}


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
