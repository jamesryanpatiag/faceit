<%@ include file="includes/header.jsp" %>
<style type="text/css">
	.form-group > label{
		font-size:12px;
	}
</style>
<div class="wrapper">
	<div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
			<%@ include file="includes/side_menu.jsp" %>
			<div class="column col-sm-10 col-xs-11" id="main">
				<%@ include file="includes/header_menu.jsp" %>
				<div class="column col-sm-8 col-xs-8" style="padding-top:70px;background:#fff">
				
					<form class="form-horizontal" action="AccountSettings" method="POST">						
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-primary">
						  <div class="panel-heading">
						    <h3 class="panel-title">Personal Information</h3>
						  </div>
						  
						  <div class="panel-body">
						  	
							  <div class="form-group">
							    <label for="txtFirstname" class="col-sm-2 control-label">Firstname:</label>
							    <div class="col-sm-7">
							      <input type="text" class="form-control input-sm" id="txtFirstname" name="txtFirstname"  placeholder="Firstname" value="<c:out value="${firstname}"></c:out>" />
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="txtMiddlename" class="col-sm-2 control-label">Middlename</label>
							    <div class="col-sm-2 col-md-1"">
							      <input type="text" class="form-control input-sm" id="txtMiddlename" name="txtMiddlename" placeholder="M" maxlength="1" value="<c:out value="${middlename}"></c:out>" />
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="txtLastname" class="col-sm-2 control-label">Lastname</label>
							    <div class="col-sm-7">
							      <input type="text" class="form-control input-sm" id="txtLastname" name="txtLastname" placeholder="Lastname" value="<c:out value="${lastname}"></c:out>" />
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="txtBirthdate" class="col-sm-2 control-label">Birthdate</label>
							    <div class="col-sm-7">
							      <input class="form-control input-sm" value="<c:out value="${birthdate}"></c:out>" type="text" readonly="" id="txtBirthdate" name="txtBirthdate" placeholder="Click to choose birthdate.">
							    </div>
							  </div>
						  </div>
						</div>
					</div>
					
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-primary">
					  <div class="panel-heading">
					    <h3 class="panel-title">Contact Information</h3>
					  </div>
					  <div class="panel-body">
						   <div class="form-group">
							    <label for="txtAddress" class="col-sm-2 control-label">Address:</label>
							    <div class="col-sm-7">
							      <input type="text" class="form-control input-sm" id="txtAddress" name="txtAddress"  placeholder="Address" value="<c:out value="${address}"></c:out>" />
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="txtMobile" class="col-sm-2 control-label">Mobile</label>
							    <div class="col-sm-5 col-md-5"">
							      <input type="text" class="form-control input-sm" id="txtMobile" name="txtMobile" placeholder="Mobile" value="<c:out value="${mobile}"></c:out>" />
							    </div>
							  </div>
					  </div>
					</div>
					</div>
					
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-primary">
					  <div class="panel-heading">
					    <h3 class="panel-title">Account Settings</h3>
					  </div>
					  <div class="panel-body">
						  <div class="form-group">
						    <label for="inputEmail3" class="col-sm-2 control-label">Email:</label>
						    <div class="col-sm-7">
						      <label><c:out value="${username}"></c:out></label>
						    	<input type="hidden" name="txtEmail" id="txtEmail" value="<c:out value="${username}"></c:out>" />
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="inputPassword3" class="col-sm-2 control-label">Password:</label>
						    <div class="col-sm-7">
						      <input type="password" class="form-control input-sm" id="txtPassword" name="txtPassword" placeholder="Password" value="<c:out value="${password}"></c:out>">
						    </div>
						  </div>
					  </div>
					</div>
					</div>
					
					<div class="col-md-10 col-sm-12 col-xs-12">
						<div class="form-group">
						<input type="submit" class="btn btn-success" name="btnSubmit" id="btnSubmit" value="Save Changes" />
						</div>
					</div>
					</form>
				</div>
			</div>        
        </div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		
		$("#txtBirthdate").datepicker({
			changeMonth: true,
      		changeYear: true,
      		yearRange: "-100:+0",
		});
	})
</script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<%@ include file="includes/footer.jsp" %>