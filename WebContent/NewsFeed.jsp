<%@ include file="includes/header.jsp" %>
<div class="wrapper">
	<div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
			<%@ include file="includes/side_menu.jsp" %>
			<div class="column col-sm-10 col-xs-11" id="main">
				<%@ include file="includes/header_menu.jsp" %>
				<div class="column col-sm-9 col-xs-9" style="padding-top:70px;background:#fff">
				
				<div style="width:70%">
				<form action="Newsfeed" method="POST">
					<textarea name="post" placeholder="What's on your mind?" class="form-control" value=""></textarea>
					<input type="hidden" name="hidden" value="hpost"/>
					<input type="submit" class="btn btn-success pull-right" value="Post"/>
				</form>
				</div>
				
				<br><br><br>
				
				<div style="width:70%">
				<c:forEach items="${posts}" var="p">
					<div class="panel panel-default">
				        <div class="panel-body">
		                	<b><c:out value="${p.getFullname()}"/></b>
		                	<div class="btn-group pull-right">
								  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								  <font size=1><span class="caret"></span></font>
								  </button>
								  <ul class="dropdown-menu">
								    <li>
								    	<form action="Newsfeed" method="POST">
											&nbsp;&nbsp;<a  href="#" onclick="enablePostInput(document.getElementById('p<c:out value="${p.postid}"/>').id);return false;">Edit</a>
										</form>
								    </li>
								    <li>
								    	<form action="Newsfeed" method="POST">
											<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
											<input type="hidden" name="hidden" value="hdeletePost"/>
											&nbsp;&nbsp;<input type="submit" class="btn btn-link" value="Delete"/>
										</form>
								    </li>
								  </ul>
							</div><br>
							<font size=1 color="grey"><c:out value="${p.datecreated}"/></font><br>
							<form action="Newsfeed" method="POST">
			                	<input name="post" id="p<c:out value="${p.postid}"/>" class="post-input" value="<c:out value="${p.description}"/>" readonly/>
			                	<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
								<input type="hidden" name="hidden" value="hupdatePost"/>
								<input type="submit" id="p<c:out value="${p.postid}"/>btn" class="" style="display:none"/>
			                </form>	 	
		                	<form action="Newsfeed" method="POST">
								<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
								<input type="hidden" name="hidden" value="hlikePost"/>
								<input type="submit" class="btn btn-primary" value="Like"/>
								<a href="#" class="glyphicon glyphicon-thumbs-up"><c:out value="${postdao.countLikePost(p.postid)}"/></a>
								<a href="#" class="glyphicon glyphicon-comment"><c:out value="${postdao.countComments(p.postid)}"/></a>
							</form>
				                	
		                	<div style="padding-left:2em;padding-top:5px;width:100%;">
			                	<table class="table">
								    <tbody>
								        <c:forEach items="${postdao.getComments(p.postid)}" var="c">
								            <tr>
								            	<td>
								            		<form action="Newsfeed" class="pull-right" method="POST">
								            			<a href="#" class="btn btn-link" onclick="enableCommentInput(document.getElementById('c<c:out value="${c.commentid}"/>').id);return false;"><font size=1><span class="glyphicon glyphicon-pencil"></span></font></a>
									            		<input type="hidden" name="commentId" value="<c:out value="${c.commentid}"/>"/>
														<input type="hidden" name="hidden" value="hdeleteComment"/>
														<button type="submit" class="btn btn-link" value=""><font size=1><span class="glyphicon glyphicon-trash" ></span></font></button>
													</form>
								                	<form action="Newsfeed" method="POST">
								                		<b><c:out value="${c.getFullname()}"/></b>
									                	<input name="comment" id="c<c:out value="${c.commentid}"/>" class="comment-input" value="<c:out value="${c.description}"/>" readonly/>
									                	<input type="hidden" name="commentId" value="<c:out value="${c.commentid}"/>"/>
														<input type="hidden" name="hidden" value="hupdateComment"/>
														<input type="submit" class="btn btn-success" style="display:none"/>
									                </form>	
								                	<form action="Newsfeed" method="POST">
														<input type="hidden" name="commentId" value="<c:out value="${c.commentid}"/>"/>
														<input type="hidden" name="hidden" value="hlikeComment"/>
														<input type="submit" class="btn btn-link" value="Like"/>&nbsp;
														<a href="#" class="glyphicon glyphicon-thumbs-up"><c:out value="${postdao.countLikeComment(c.commentid)}"/></a>&nbsp;&nbsp;
														<font size=1 color="grey"><c:out value="${c.datecreated}"/></font><br>
													</form>	
												</td>  	       
								            </tr>
								        </c:forEach>
								    </tbody>
								</table>
								<form action="Newsfeed" method="POST">
									<input type="text" name="comment" class="form-control" placeholder="Add comment"/>
									<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
									<input type="hidden" name="hidden" value="hcomment"/>
									<input type="submit" class="btn btn-success" style="display:none"/>
								</form><br><br>     
							</div>  
							
				        </div>
				      </div>        
				 </c:forEach>
				 </div>
				
					
				</div>
			</div>        
        </div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>