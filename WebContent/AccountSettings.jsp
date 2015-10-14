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
										
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-primary">
						  <div class="panel-heading">
						    <h3 class="panel-title">Personal Information</h3>
						  </div>
						  <div class="panel-body">
						  	<form class="form-horizontal">
							  <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Firstname:</label>
							    <div class="col-sm-7">
							      <input type="email" class="form-control input-sm" id="inputEmail3" placeholder="Firstname" value="<c:out value="${firstname}"></c:out>" />
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">Middlename</label>
							    <div class="col-sm-2 col-md-1"">
							      <input type="text" class="form-control input-sm" id="inputPassword3" placeholder="M" maxlength="1" value="<c:out value="${middlename}"></c:out>" />
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">Lastname</label>
							    <div class="col-sm-7">
							      <input type="text" class="form-control input-sm" id="inputPassword3" placeholder="Lastname" value="<c:out value="${lastname}"></c:out>" />
							    </div>
							  </div>
							</form>
						  </div>
						</div>
					</div>
					
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-primary">
					  <div class="panel-heading">
					    <h3 class="panel-title">Account Settings</h3>
					  </div>
					  <div class="panel-body">
					  	<form class="form-horizontal">
						  <div class="form-group">
						    <label for="inputEmail3" class="col-sm-2 control-label">Email:</label>
						    <div class="col-sm-7">
						      <label><c:out value="${username}"></c:out></label>
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="inputPassword3" class="col-sm-2 control-label">Password:</label>
						    <div class="col-sm-7">
						      <input type="password" class="form-control input-sm" id="inputPassword3" placeholder="Password" value="<c:out value="${password}"></c:out>">
						    </div>
						  </div>
						</form>
					  </div>
					</div>
					</div>
					
					<div class="col-md-10 col-sm-12 col-xs-12">
						<div class="form-group">
						<input type="submit" class="btn btn-success" name="btnSubmit" id="btnSubmit" value="Save Changes" />
						</div>
					</div>
					
				</div>
			</div>        
        </div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>