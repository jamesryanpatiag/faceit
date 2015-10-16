<%@ include file="includes/header.jsp" %>
<div class="wrapper">
	<div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
			<%@ include file="includes/side_menu.jsp" %>
			<div class="column col-sm-10 col-xs-11" id="main">
				<%@ include file="includes/header_menu.jsp" %>
				<div class="column col-sm-9 col-xs-9" style="padding-top:70px;background:#fff">
				
				<c:choose>
					<c:when test= "${!pendingfriends.isEmpty()}">
						<h4>Pending requests</h4>
						<hr>
							<c:forEach items="${pendingfriends}" var="p">
							<div class="row">
								  <div class="col-xs-6 col-md-3">
								  	
								    <div class="thumbnail">
								    	<b><a href="Profile?profile=<c:out value="${p.userid}"/>"><c:out value="${p.fullname}"/></a></b><br>
								  		<font size=1 color="grey"><c:out value="${p.address}"/></font><br>
								  		<a class="btn btn-primary" href="Friends?user=<c:out value="${sessionuserid}"/>&profile=<c:out value="${p.userid}"/>&action=accept">Confirm</a>
								    </div>
								  </div>
							</div>
							</c:forEach>
						<br><br>
					</c:when>
			  </c:choose>
				
				
				<c:choose>
					<c:when test= "${!friends.isEmpty()}">
						<h4>Friends</h4>
						<hr>
							<c:forEach items="${friends}" var="f">
							<div class="row">
								  <div class="col-xs-6 col-md-3">
								  	
								    <div class="thumbnail">
								    	<b><a href="Profile?profile=<c:out value="${f.userid}"/>"><c:out value="${f.fullname}"/></a></b><br>
								  		<font size=1 color="grey"><c:out value="${f.address}"/></font><br>
								  		<a class="btn btn-primary" href="Friends?user=<c:out value="${sessionuserid}"/>&profile=<c:out value="${f.userid}"/>&action=unfried">Unfriend</a>
								    </div>
								  </div>
							</div>
							</c:forEach>
						<br><br>
					</c:when>
			  </c:choose>
				
				</div>
			</div>        
        </div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>