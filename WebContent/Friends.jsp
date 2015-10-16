<%@ include file="includes/header.jsp" %>
<div class="wrapper">
	<div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
			<%@ include file="includes/side_menu.jsp" %>
			<div class="column col-sm-10 col-xs-11" id="main">
				<%@ include file="includes/header_menu.jsp" %>
				<div class="column col-sm-9 col-xs-9" style="padding-top:70px;background:#fff">
				
				<c:forEach items="${friends}" var="f">
				<div class="row">
					  <div class="col-xs-6 col-md-3">
					    <a href="Profile?profile=<c:out value="${f.id}"/>" class="thumbnail">
					    	<b><c:out value="${f.fullname}"/><br></b>
					  		<font size=2><c:out value="${f.address}"/></font>
					    </a>
					  </div>
				</div>
				</c:forEach>
				
				</div>
			</div>        
        </div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>