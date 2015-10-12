<%@ include file="includes/header.jsp" %>
<div class="wrapper">
	<div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
			<%@ include file="includes/side_menu.jsp" %>
			<div class="column col-sm-10 col-xs-11" id="main">
				<%@ include file="includes/header_menu.jsp" %>
				<div class="column col-sm-9 col-xs-9" style="padding-top:70px;background:#fff">
				
				
				<form action="Newsfeed" method="POST">
					<textarea name="post" placeholder="What's on your mind?" class="" value=""></textarea>
					<input type="hidden" name="hidden" value="hpost"/>
					<br>
					<input type="submit" class="btn btn-success" value="Post"/>
				</form>
				
				<br><br>
				<table>
				    <tbody>
				        <c:forEach items="${posts}" var="p">
				            <tr>
				                <td>
				                	<b><c:out value="${p.getFullname()}"/></b><br>
				                	<c:out value="${p.description}"/><br>
				                	<font size=2 color="grey"><c:out value="${p.datecreated}"/></font><br>
				                	<form action="Newsfeed" method="POST">
										<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
										<input type="hidden" name="hidden" value="hlike"/>
										<input type="submit" class="btn btn-success" value="Like"/>
									</form><font size=2><c:out value="${postdao.countLikes(p.postid)}"/> like this</font>
				                	<div style="padding-left:1em">
					                	<table>
										    <tbody>
										        <c:forEach items="${postdao.getComments(p.postid)}" var="c">
										            <tr>
										                <td>
										                	<b><c:out value="${c.getFullname()}"/></b> <c:out value="${c.description}"/><br>
										                	<font size=2 color="grey"><c:out value="${c.datecreated}"/></font><br>       
										                </td>
										            </tr>
										        </c:forEach>
										    </tbody>
										</table>
										<form action="Newsfeed" method="POST">
											<input type="text" name="comment" placeholder="" class="" value=""/>
											<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
											<input type="hidden" name="hidden" value="hcomment"/>
											<input type="submit" class="btn btn-success" style="display:none" value="Comment"/>
										</form><br><br>     
									</div>  
				                </td>
				            </tr>
				        </c:forEach>
				    </tbody>
				</table>
				
					
				</div>
			</div>        
        </div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>