<%@ include file="includes/header.jsp" %>
<div class="wrapper">
	<div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
			<%@ include file="includes/side_menu.jsp" %>
			<div class="column col-sm-10 col-xs-11" id="main">
				<%@ include file="includes/header_menu.jsp" %>
				<div class="column col-sm-9 col-xs-9" style="padding-top:70px;background:#fff">
				
				<div class="panel panel-default">
				<c:forEach items="${friends}" var="f">
				
					  <div class="panel-body">
					  <a href="Profile/<c:out value="${f.id}"/>"><c:out value="${f.fullname}"/></a><br>
					  <font size=2><c:out value="${f.address}"/></font>
					  </div>
				  
				 </c:forEach>
				</div>
				
				</div>
			</div>        
        </div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>