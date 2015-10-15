<%@ include file="includes/header.jsp" %>
<div class="wrapper">
	<div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
			<%@ include file="includes/side_menu.jsp" %>
			<div class="column col-sm-10 col-xs-11" id="main">
				<%@ include file="includes/header_menu.jsp" %>
				<div class="column col-sm-9 col-xs-9" style="padding-top:70px;background:#fff">
				<form action="ProfilePage" method="GET">
				<table>
				<thead>POSTS</thead>
				    <tbody>
				        <c:forEach items="${posts}" var="p">
				            <tr>
				                <td>
				                	<b><c:out value="${p.getFullname()}"/></b><br>
				                	<c:out value="${p.description}"/><br>
				                </td>
				            </tr>
				        </c:forEach>
				    </tbody>
				</table>
				</form>
					
				</div>
			</div>        
        </div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>