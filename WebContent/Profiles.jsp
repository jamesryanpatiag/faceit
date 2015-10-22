<%@ include file="includes/header.jsp" %>
<div class="wrapper">
	<div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
			<%@ include file="includes/side_menu.jsp" %>
			<div class="column col-sm-10 col-xs-11" id="main">
				<%@ include file="includes/header_menu.jsp" %>
				<div class="column col-sm-9 col-xs-9 " style="background-image:url('images/bg4.jpg');background-size: cover;height:300px;hidden;overflow: hidden;">
					<div class="jumbotron">
					<div class="container" style="padding-top:20px">
						<img class="thumbnail pull-left" src="images/avtr_c1105b9ff651139650aa3914ee0a14a2_original.jpg" width="200" height="200" style="margin-right:20px">
					  	<h2 style="color:#fff;padding-top:"><c:out value="${profile.firstname}"/> <c:out value="${profile.middlename}"/> <c:out value="${profile.lastname}"/></h2>
					  	<c:choose>
							<c:when test= "${profileid != sessionuserid && connectiondao.checkIfConnected(sessionuserid, profileid) == 0}">
							  	<form method="POST" action="Profile">
							 		<p><input class="btn btn-primary btn-lg" id="btnSubmit" name="btnSubmit" value="Add Friend" type="submit"  /></p>
							 		<input type="hidden" value="addFriend" id="hdnOperation" name="hidden" />
							 		<input type="hidden" value="<c:out value="${profileid}"/>" name="profile" />
							  	</form>
							</c:when>
							<c:when test= "${profileid != sessionuserid && connectiondao.getConnectionStatus(sessionuserid, profileid) == 'PENDING'}">
							  	<form method="POST" action="Profile">
							 		<p><input class="btn btn-primary btn-lg" id="btnSubmit" name="btnSubmit" value="Confirm" type="submit"  /></p>
							 		<input type="hidden" value="confirmFriend" id="hdnOperation" name="hidden" />
							 		<input type="hidden" value="<c:out value="${profileid}"/>" name="profile" />
							 		<input type="hidden" value="<c:out value="${connectiondao.getConnectionId(sessionuserid, profileid)}"/>" name="connectionId" />
							  	</form>
							</c:when>
							<c:when test= "${profileid != sessionuserid && connectiondao.getConnectionStatus(sessionuserid, profileid) == 'ACTIVE'}">
							  	<form method="POST" action="Profile">
							 		<p><input class="btn btn-primary btn-lg" id="btnSubmit" name="btnSubmit" value="Unfriend" type="submit"  /></p>
							 		<input type="hidden" value="unFriend" id="hdnOperation" name="hidden" />
							 		<input type="hidden" value="<c:out value="${profileid}"/>" name="profile" />
							 		<input type="hidden" value="<c:out value="${connectiondao.getConnectionId(sessionuserid, profileid)}"/>" name="connectionId" />
							  	</form>
							</c:when>
							<c:when test= "${profileid == sessionuserid}">
							  	
							</c:when>
							<c:otherwise>
								(Friend request sent)
							</c:otherwise>
						</c:choose>
					  	
					</div>	
				</div>
				</div>
				<div class="column col-sm-9 col-xs-9" style="padding-top:30px;background:#fff">
				
				<form action="Profile" method="POST">
				<div class="panel panel-default">
				  <div class="panel-body">
				    <textarea name="post" placeholder="What's on your mind?" class="form-control post-input"></textarea>
				  </div>
				  <div class="panel-footer"> 
						 <input type="hidden" name="profile" value="<c:out value="${profileid}"/>"/>
						<input type="hidden" name="hidden" value="hpost"/>
						<input type="submit" class="btn btn-success" value="Post"/>			  
				  </div>
				</div>
				</form>

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
										    	<form action="Profile" method="POST">
													&nbsp;&nbsp;<a  href="#" onclick="enablePostInput(document.getElementById('p<c:out value="${p.postid}"/>').id);return false;">Edit</a>
												</form>
										    </li>
										    <li>
										    	<form action="Profile" method="POST">
													<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
													<input type="hidden" name="profile" value="<c:out value="${profileid}"/>"/>
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
							      <img class="img-rounded pull-left thumbnail" src="images/avtr_c1105b9ff651139650aa3914ee0a14a2_original.jpg" width="45" height="40"  alt="..." >
							    </a>
							    &nbsp;<b><c:out value="${p.getFullname()}"/></b><br>
								&nbsp;<font size=1><c:out value="${p.datecreated}"/></font>
							 </div>
							 
						</div>
						
				        <div class="panel-body">
							<form action="Profile" method="POST">
			                	<textarea name="post" id="p<c:out value="${p.postid}"/>" class="post-input" readonly><c:out value="${p.description}"/></textarea>
			                	<input type="hidden" name="profile" value="<c:out value="${profileid}"/>"/>
			                	<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
								<input type="hidden" name="hidden" value="hupdatePost"/>
								<input type="submit" id="p<c:out value="${p.postid}"/>btn" class="" style="display:none"/>
			                </form>
			                <br>	 	
		                	<form action="Profile" method="POST">
								<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
								<input type="hidden" name="profile" value="<c:out value="${profileid}"/>"/>
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
						                		<form action="Profile" class="pull-right" method="POST">
							            			<a href="#" class="btn btn-link" onclick="enableCommentInput(document.getElementById('c<c:out value="${c.commentid}"/>').id);return false;"><font size=1><span class="glyphicon glyphicon-pencil"></span></font></a>
								            		<input type="hidden" name="commentId" value="<c:out value="${c.commentid}"/>"/>
								            		<input type="hidden" name="profile" value="<c:out value="${profileid}"/>"/>
													<input type="hidden" name="hidden" value="hdeleteComment"/>
													<button type="submit" class="btn btn-link"><font size=1><span class="glyphicon glyphicon-trash" ></span></font></button>
												</form>
											</c:when>
										</c:choose>	
												
					                	<form action="Profile" method="POST">	
					                		<b><c:out value="${c.getFullname()}"/></b>				                		
						                	<textarea name="comment" id="c<c:out value="${c.commentid}"/>" class="comment-input" readonly><c:out value="${c.description}"/></textarea>
						                	<input type="hidden" name="commentId" value="<c:out value="${c.commentid}"/>"/>
						                	<input type="hidden" name="profile" value="<c:out value="${profileid}"/>"/>
											<input type="hidden" name="hidden" value="hupdateComment"/>
											<input type="submit" class="btn btn-success" style="display:none"/>
						                </form>	
					                	<form action="Profile" method="POST">
											<input type="hidden" name="commentId" value="<c:out value="${c.commentid}"/>"/>
											<input type="hidden" name="profile" value="<c:out value="${profileid}"/>"/>
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
							<form action="Profile" method="POST">
								<input type="text" name="comment" class="form-control comment-input" placeholder="Add comment"/>
								<input type="hidden" name="postId" value="<c:out value="${p.postid}"/>"/>
								<input type="hidden" name="profile" value="<c:out value="${profileid}"/>"/>
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