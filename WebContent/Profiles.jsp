<%@ include file="includes/header.jsp" %>
<div class="wrapper">
	<div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
			<%@ include file="includes/side_menu.jsp" %>
			<div class="column col-sm-10 col-xs-11" id="main">
				<%@ include file="includes/header_menu.jsp" %>
				<div class="column col-sm-9 col-xs-9" style="padding-top:70px;background:#fff">
	<table>
        <thead>
            <tr>POSTS</tr>
        </thead>
        <tbody>
            <c:forEach items="${posts}" var="posts">
                    <tr><c:out value="${posts.description}" /></tr>
            </c:forEach>
        </tbody>
    </table>
				

				
					
				</div>
			</div>        
        </div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>