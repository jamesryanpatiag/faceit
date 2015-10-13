<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8"> 
		<link href="css/style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!--webfonts-->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text.css'/>
		<!--//webfonts-->
</head>
<body>
	<div class="main">
		<div class="header" >
			<h1>Welcome to FaceIT!</h1>
		</div>
		<p>Connect with friends and the world around you on FaceIT.</p>
			<form id="regsitration" action="Registration" method="POST">
				<ul class="left-form">
					<h2>New Account:</h2>
					<li>
						<input type="text" id="firstname" name="firstname" placeholder="First Name"/>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text" id="middlename" name="middlename" placeholder="Middle Name"/>
						<div class="clear"> </div>
					</li>
					<li>
						<input type="text" id="lastname" name="lastname" placeholder="Last Name"/>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text" id="email" name="email" placeholder="Email"/>
						<div class="clear"> </div>
					</li>
					<li>
						<input type="password" id="password" name="password" placeholder="Password"/>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="password" id="confirmpassword" name="confirmpassword" placeholder="Confirm Password"/>
						<div class="clear"> </div>
					</li> 
					<li><label style="font-size:80%">Date of Birth</label></li>
					<li>
						<select aria-label="Month" name="month" id="month">
=======
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
		    				<hr/>
		    				<label>Date of Birth: </label>
		    			</div>
		    			<div class="column col-sm-4 col-xs-3">
		    				<select class="form-control input-sm" aria-label="Month" name="month" id="month">
>>>>>>> origin/branch_james
							<option value="" >Month</option>
							<option value="1">January</option>
							<option value="2">February</option>
							<option value="3">March</option>
							<option value="4">April</option>
							<option value="5">May</option>
							<option value="6">June</option>
							<option value="7">July</option>
							<option value="8">August</option>
							<option value="9">September</option>
							<option value="10">October</option>
							<option value="11">November</option>
							<option value="12">December</option>
							</select>
		    			</div>
		    			<div class="column col-sm-4 col-xs-3">
		    				<select aria-label="Day" name="day" id="day"  class="form-control input-sm" >
								<option value="">Day</option>
								<c:forEach var="x" begin="1" end="31">
									<option value='<c:out value="${x}"/>'><c:out value="${x}" /></option>
								</c:forEach>	
							</select>
		    			</div>
		    			<div class="column col-sm-4 col-xs-3">
							<select aria-label="Year" name="year" id="year" class="form-control input-sm" >
								<option value="">Year</option>
								<c:forEach var="x" begin="1900" end="2015">
									<option value='<c:out value="${x}"/>'><c:out value="${x}" /></option>
								</c:forEach>	
							</select> 
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
<%@ include file="includes/footer.jsp" %>
