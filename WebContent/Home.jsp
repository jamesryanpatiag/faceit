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
	
	<div class="column col-sm-7 col-xs-7">
		
	</div>
	<div class="column col-sm-5 col-xs-5" style="padding-right:50px">
		<div style="height:90px"></div>
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">New Account:</h3>
		  </div>
		  <div class="panel-body">
		    <form id="regsitration" action="Registration" method="POST">
		    	<div class="container-fluid">
		    		<div class="row">
		    			<div class="column col-sm-5 col-xs-5">
		    				<input type="text" id="firstname" name="firstname" placeholder="First Name" class="form-control input-lg"/>
		    			</div>
		    			<div class="column col-sm-2 col-xs-2">
		    				<input type="text" id="middlename" name="middlename" placeholder="M" maxlength="1"  class="form-control input-lg"/>
		    			</div>
		    			<div class="column col-sm-5 col-xs-5">
		    				<input type="text" id="lastname" name="lastname" placeholder="Last Name"  class="form-control input-lg"/>
		    			</div>
		    			<div class="column col-sm-12 col-xs-12">
		    				<input type="text" id="email" name="email" placeholder="Email" class="form-control input-lg" />
		    			</div>
		    			<div class="column col-sm-12 col-xs-12">
		    				<input type="password" id="password" name="password" placeholder="Password"  class="form-control input-lg" />
		    			</div>
		    			<div class="column col-sm-12 col-xs-12">
		    				<div class="form-group">
		    					<input class="form-control input-lg" type="text" readonly="" id="birthdate" placeholder="Click to choose birthdate.">
		    				</div>
		    			</div>
		    			<div class="column col-sm-3 col-xs-3" >
		    				<select aria-label="Gender" name="gender" id="gender"  class="form-control input-sm">
								<option value="" >Gender</option>
								<option value="1">Male</option>
								<option value="2">Female</option>
							</select>
		    			</div>
		    			<div class="column col-sm-12 col-xs-12" >
		    				<input type="submit" value="Create Account" name="btnSubmit" id="btnSubmit" class="btn btn-success">
		    			</div>
		    		</div>
		    	</div>
				
				</form>
		  </div>
		</div>
	</div></div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#birthdate").datepicker({
			changeMonth: true,
      		changeYear: true,
      		yearRange: "-100:+0",
		});
	})
</script>

<script type="text/javascript" src="js/jquery-ui.js"></script>
<%@ include file="includes/footer.jsp" %>
