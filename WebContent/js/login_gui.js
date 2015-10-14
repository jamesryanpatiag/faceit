$(document).ready(function(){
	
	$("tmpPassword").val($("#password"));
	
	$("#password, #tmpPassword").on("change",function(){
		if($(this).attr("id") == "password"){
			$("#tmpPassword").val($("#password").val());
		}else{
			$("#password").val($("#tmpPassword").val());
		}
	})
	
	$("#viewPass").mouseover(function(){
		$("#password").css("display","none");
		$("#tmpPassword").css("display","inline");
	});
	
	$("#viewPass").mouseout(function(){
		$("#password").css("display","inline");
		$("#tmpPassword").css("display","none");
	});
	
	
})