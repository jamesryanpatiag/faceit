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
								  		<form method="POST" action="Friends">
									 		<p><input class="btn btn-primary" id="btnSubmit" name="btnSubmit" value="Confirm" type="submit"  /></p>
									 		<input type="hidden" value="confirmFriend" name="action" />
									 		<input type="hidden" value="<c:out value="${p.id}"/>" name="connectionId" />
									  	</form>
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
								  		<form method="POST" action="Friends">
									 		<p><input class="btn btn-primary" id="btnSubmit" name="btnSubmit" value="Unfriend" type="submit"  /></p>
									 		<input type="hidden" value="unFriend" name="action" />
									 		<input type="hidden" value="<c:out value="${f.id}"/>" name="connectionId" />
									  	</form>
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