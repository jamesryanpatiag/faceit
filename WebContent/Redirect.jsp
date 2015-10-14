<%@ include file="includes/header.jsp" %>
<style type="text/css">
	#main{
		background-image: url("images/social background.png");
	}
	
	input,select{
		margin-bottom:5px;
	}
	.column{
		padding:2px;
	}
</style>

<div class="wrapper">
	<div class="column col-sm-12 col-xs-12" id="main">
	<%@ include file="includes/header_login.jsp" %>
		<div class="header">
			<div class="column col-sm-4 col-xs-4">
			
			</div>
			<div class="column col-sm-4 col-xs-4">
				<div class="panel panel-success" style="width:100%;margin-top:100px">
				  <div class="panel-heading">
				    <h3 class="panel-title">Registration Success - Congratulations!</h3>
				  </div>
				  <div class="panel-body">
				  	<div class="row">
				  		<div class="column col-sm-9 col-xs-9">
				  			<p>You have successfully completed your registration.</p>
				    		<p>You can now login using your email address.</p>	
				  		</div>
				  		<div class="column col-sm-3 col-xs-3">
				  			<img src="images/check.png" style="width:70%">
				  		</div>
				  	</div>
				    
				  </div>
				</div>
			</div>
			<div class="column col-sm-4 col-xs-4">
			</div>
		</div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>