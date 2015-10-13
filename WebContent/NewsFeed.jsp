<%@ include file="includes/header.jsp" %>
<div class="wrapper">
	<div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
			<%@ include file="includes/side_menu.jsp" %>
			<div class="column col-sm-10 col-xs-11" id="main">
				<%@ include file="includes/header_menu.jsp" %>
				<div class="column col-sm-9 col-xs-9" style="padding-top:70px;background:#fff">
				
				
				<form action="Newsfeed" method="POST">
					<textarea name="post" placeholder="What's on your mind?" class="form-control" value=""></textarea>
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
				                	<b><c:out value="${p.getFullname()}"/></b>
				                	<div class="btn-group">
									  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									  <font size=1><span class="caret"></span></font>
									  </button>
									  <ul class="dropdown-menu">
									    <li>
									    	<form action="Newsfeed" method="POST">
												<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
												<input type="hidden" name="hidden" value="heditPost"/>
												<input type="submit" class="btn btn-link" value="Edit"/>
											</form>
									    </li>
									    <li>
									    	<form action="Newsfeed" method="POST">
												<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
												<input type="hidden" name="hidden" value="hdeletePost"/>
												<input type="submit" class="btn btn-link" value="Delete"/>
											</form>
									    </li>
									    <li role="separator" class="divider"></li>
									    <li><font size=2><a href="#">Edit privacy</a></font></li>
									  </ul>
									</div>
				                	<br>
				                	<font size=1 color="grey"><c:out value="${p.datecreated}"/></font><br>
				                	<c:out value="${p.description}"/><br>  	
				                	<form action="Newsfeed" method="POST">
										<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
										<input type="hidden" name="hidden" value="hlikePost"/>
										<input type="submit" class="btn btn-success" value="Like"/>
										<font size=2><a href="#" class="glyphicon glyphicon-thumbs-up"><c:out value="${postdao.countLikePost(p.postid)}"/></a>
										<a href="#" class="glyphicon glyphicon-comment"><c:out value="${postdao.countComments(p.postid)}"/></a></font>
									</form>
				                	<div style="padding-left:1em;padding-top:5px;">
					                	<table>
										    <tbody>
										        <c:forEach items="${postdao.getComments(p.postid)}" var="c">
										            <tr>
										                <td>
										                	<b><c:out value="${c.getFullname()}"/></b> 
											                	<input id="p<c:out value="${p.postid}"/>c<c:out value="${c.commentid}"/>" class="comment-input" value="<c:out value="${c.description}"/>" readonly/>
											                	<font size=1><a  href="#" onclick="enableCommentInput(document.getElementById('p<c:out value="${p.postid}"/>c<c:out value="${c.commentid}"/>').id);return false;"><span class="glyphicon glyphicon-pencil"></span></a>
											                	<a href="#"><span class="glyphicon glyphicon-trash"></span></a></font><br>
										                	<form action="Newsfeed" method="POST">
																<input type="hidden" name="commentId" value="<c:out value="${c.commentid}"/>"/>
																<input type="hidden" name="hidden" value="hlikeComment"/>
																<input type="submit" class="btn btn-link" value="Like"/>
																<font size=2><a href="#" class="glyphicon glyphicon-thumbs-up"><c:out value="${postdao.countLikeComment(c.commentid)}"/></a></font>
																	&nbsp;<font size=1 color="grey"><c:out value="${c.datecreated}"/></font><br>
															</form>  	       
										                </td>
										            </tr>
										        </c:forEach>
										    </tbody>
										</table>
										<form action="Newsfeed" method="POST">
											<input type="text" name="comment" placeholder="Add comment"/>
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