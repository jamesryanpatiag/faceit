<%@ include file="includes/header.jsp" %>
<div class="wrapper">
	<div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
			<%@ include file="includes/side_menu.jsp" %>
			<div class="column col-sm-10 col-xs-11" id="main">
				<%@ include file="includes/header_menu.jsp" %>
				<div class="column col-sm-9 col-xs-9" style="padding-top:70px;background:#fff"><div>
				
				<form action="Newsfeed" method="POST" id="postNewsfeed">
				<div class="panel panel-default">
				  <div class="panel-body">
				     <textarea name="post" placeholder="What's on your mind?" class="form-control post-input"></textarea>
					<input type="hidden" name="hidden" value="hpost"/>
				  </div>
				  <div class="panel-footer"> 
						 <input type="submit" class="btn btn-success" value="Post"/>			  
				  </div>
				</div>
				</form>
				
				</div>
				<div>
					<c:choose>
					  	<c:when test= "${posts.isEmpty()}">
					  		No posts yet...
					  	</c:when>
				  	</c:choose>
				  	
					<c:forEach items="${posts}" var="p">
					<div class="panel panel-primary">
						<div class="panel-heading">
						
							<c:choose>
							    <c:when test= "${sessionuserid == p.userid}">
							    
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
									</div>
									
								</c:when>
							</c:choose>
							
							<div class="media-left">
							    <a href="#">
							      <img class="img-rounded pull-left" src="https://s3.amazonaws.com/thisismyjam/i/avtr_c1105b9ff651139650aa3914ee0a14a2_original.jpg" width="45" height="40" alt="...">
							    </a>
							    &nbsp;<b><c:out value="${p.getFullname()}"/></b><br>
								&nbsp;<font size=1><c:out value="${p.datecreated}"/></font>
							 </div>
						
						</div>
						
				        <div class="panel-body">
							<form action="Newsfeed" method="POST" id="updateNewsfeed">
			                	<textarea name="post" id="p<c:out value="${p.postid}"/>" class="post-input" readonly><c:out value="${p.description}"/></textarea>
			                	<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
								<input type="hidden" name="hidden" value="hupdatePost"/>
								<input type="submit" id="p<c:out value="${p.postid}"/>btn" class="" style="display:none"/>
			                </form>
			                <br>	 	
		                	<form action="Newsfeed" method="POST" id="likePost">
								<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
								<input type="hidden" name="hidden" value="hlikePost"/>
								<c:choose>
								    <c:when test= "${postdao.checkLikePost(p.postid, userid) == 0}">
								        <c:set var="likestr" value="Like" />
								    </c:when>
								    <c:otherwise>
								        <c:set var="likestr" value="Unike" />
								    </c:otherwise>
								</c:choose>
								<input type="submit" class="btn btn-primary" value="<c:out value="${likestr}"/>"/>
								<a href="#" class="glyphicon glyphicon-thumbs-up"><c:out value="${postdao.countLikePost(p.postid)}"/></a>
								<a href="#" class="glyphicon glyphicon-comment"><c:out value="${postdao.countComments(p.postid)}"/></a>
							</form>
				        </div>
				        
				        <c:forEach items="${postdao.getComments(p.postid)}" var="c">
				                <ul class="list-group">
				                
				                	<li class="list-group-item">
				                		
				                		<c:choose>
							    			<c:when test= "${sessionuserid == c.userid}">
						                		<form action="Newsfeed" class="pull-right" method="POST">
							            			<a href="#" class="btn btn-link" onclick="enableCommentInput(document.getElementById('c<c:out value="${c.commentid}"/>').id);return false;"><font size=1><span class="glyphicon glyphicon-pencil"></span></font></a>
								            		<input type="hidden" name="commentId" value="<c:out value="${c.commentid}"/>"/>
													<input type="hidden" name="hidden" value="hdeleteComment"/>
													<button type="submit" class="btn btn-link"><font size=1><span class="glyphicon glyphicon-trash" ></span></font></button>
												</form>
											</c:when>
										</c:choose>
										
					                	<form action="Newsfeed" method="POST">	
					                		<b><c:out value="${c.getFullname()}"/></b>				                		
						                	<textarea name="comment" id="c<c:out value="${c.commentid}"/>" class="comment-input" onkeydown="if(event.keyCode == 13) submit()" readonly><c:out value="${c.description}"/></textarea>
						                	<input type="hidden" name="commentId" value="<c:out value="${c.commentid}"/>"/>
											<input type="hidden" name="hidden" value="hupdateComment"/>
											<input type="submit" class="btn btn-success" style="display:none"/>
						                </form>	
					                	<form action="Newsfeed" method="POST">
											<input type="hidden" name="commentId" value="<c:out value="${c.commentid}"/>"/>
											<input type="hidden" name="hidden" value="hlikeComment"/>
											<c:choose>
											    <c:when test= "${postdao.checkLikeComment(c.commentid, userid) == 0}">
											        <c:set var="likestr" value="Like" />
											    </c:when>
											    <c:otherwise>
											        <c:set var="likestr" value="Unike" />
											    </c:otherwise>
											</c:choose>
											<input type="submit" class="btn btn-link" value="<c:out value="${likestr}"/>"/>&nbsp;
											<a href="#" class="glyphicon glyphicon-thumbs-up"><c:out value="${postdao.countLikeComment(c.commentid)}"/></a>&nbsp;&nbsp;
											<font size=1><c:out value="${c.datecreated}"/></font>
										</form>	
									</li>
									
								</ul>  
								</c:forEach>
				        
				        <div class="panel-footer">
							<form action="Newsfeed" method="POST">
								<input type="text" name="comment" class="form-control" placeholder="Add comment"/>
								<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
								<input type="hidden" name="hidden" value="hcomment"/>
								<input type="submit" class="btn btn-success" style="display:none"/>
							</form> 
						</div>
				 </div> 
				 <br>       
				 </c:forEach>
			</div>
				
					
				</div>
			</div>        
        </div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>