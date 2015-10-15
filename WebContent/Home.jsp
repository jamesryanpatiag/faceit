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
		    				<input type="text" value="<c:out value="${firstname}"></c:out>" id="firstname" name="firstname" placeholder="First Name" class="form-control input-lg"/>
		    				<label class="control-label" for="inputError1" id="error1"><c:out value="${fnameerr}"></c:out></label>
		    			</div>
		    			<div class="column col-sm-2 col-xs-2">
		    				<input type="text" value="<c:out value="${middlename}"></c:out>" id="middlename" name="middlename" placeholder="M" maxlength="1"  class="form-control input-lg"/>
		    				<label class="control-label" for="inputError1" id="error2"><c:out value="${mnameerr}"></c:out></label>
		    			</div>
		    			<div class="column col-sm-5 col-xs-5">
		    				<input type="text" value="<c:out value="${lastname}"></c:out>" id="lastname" name="lastname" placeholder="Last Name"  class="form-control input-lg"/>
		    				<label class="control-label" for="inputError1" id="error3"><c:out value="${lnameerr}"></c:out></label>
		    			</div>
		    			<div class="column col-sm-12 col-xs-12">
		    				<input type="text" value="<c:out value="${email}"></c:out>" id="email" name="email" placeholder="Email" class="form-control input-lg" />
		    				<label class="control-label" for="inputError1" id="error4"><c:out value="${emailerr}"></c:out></label>
		    			</div>
		    			<div class="column col-sm-12 col-xs-12">
		    				<div class="input-group">
						      <input type="password" id="password" name="password" placeholder="Password" class="form-control input-lg" />
						      <input type="text" id="tmpPassword" name="tmpPassword" placeholder="Password" class="form-control input-lg" style="display:none"/>
						      <div class="input-group-addon"><a href="#" id="viewPass" ><i class="glyphicon glyphicon-eye-open"></i></a></div>
						    </div>	
						    <label class="control-label" for="inputError1" id="error5"><c:out value="${passerr}"></c:out></label>
		    			</div>
		    			<div class="column col-sm-12 col-xs-12">
		    				<div class="form-group">
		    					<input class="form-control input-lg" value="<c:out value="${birthdate}"></c:out>" type="text" readonly="" id="birthdate" name="birthdate" placeholder="Click to choose birthdate.">
		    				</div>
		    				<label class="control-label" for="inputError1" id="error6"><c:out value="${birtherr}"></c:out></label>
		    			</div>
		    			<div class="column col-sm-3 col-xs-3" >
		    				<select aria-label="Gender" name="gender" id="gender"  class="form-control input-sm">
								<option value="" >Gender</option>
								<option value="1" <c:if test="${gender == 1}">selected="selected"</c:if> >Male</option>
								<option value="2" <c:if test="${gender == 2}">selected="selected"</c:if>>Female</option>
							</select>
							<label class="control-label" for="inputError1" id="error7"><c:out value="${generr}"></c:out></label>
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
		
		ShowErrorMessages();
		
		$("#birthdate").datepicker({
			changeMonth: true,
      		changeYear: true,
      		yearRange: "-100:+0",
		});
	})
	
	function ShowErrorMessages(){
		for(i = 1; i <= 7; i++){
			var err = $("#error" + i);
			err.css("display","none");
			err.parent("div").removeClass("has-error");
			if(err.text() != ""){
				err.css("display","inline");
				err.parent("div").addClass("has-error");
			}
			
		}
	}
</script>

<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/login_gui.js"></script>
<%@ include file="includes/footer.jsp" %>
