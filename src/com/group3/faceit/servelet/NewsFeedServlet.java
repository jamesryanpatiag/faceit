package com.group3.faceit.servelet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.*;

import com.group3.faceit.model.newsfeed.*;
import com.group3.faceit.services.newsfeed.*;
import com.group3.faceit.services.connection.*;

/**
 * Servlet implementation class NewsFeed
 */
@WebServlet("/Newsfeed")
public class NewsFeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final JDialog dialog = new JDialog();
	RequestDispatcher rd = null;
	NewsfeedServices newsfeedService;	
	ConnectionsServices connService;
	public int sessionUserId; //SESSION USER ID
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsFeedServlet() {
        super();
        newsfeedService = new NewsfeedServices();
        connService = new ConnectionsServices();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("userid").toString() + ":session");
		if(session.getAttribute("userid").toString() != "" && session.getAttribute("userid") != null)
		{
			sessionUserId = Integer.parseInt(session.getAttribute("userid").toString());
			// TODO Auto-generated method stub
			request.setAttribute("Title", "News Feed");
			request.setAttribute("posts", newsfeedService.getPosts(sessionUserId));
			request.setAttribute("postdao", newsfeedService);
			request.setAttribute("connectiondao", connService);
			request.setAttribute("sessionuserid", session.getAttribute("userid"));
			request.getRequestDispatcher("/NewsFeed.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/Home.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		sessionUserId = Integer.parseInt(session.getAttribute("userid").toString());
		
		String action = request.getParameter("hidden");
		String postId = request.getParameter("postId");
		String commentId = request.getParameter("commentId");
		String post = request.getParameter("post");
		String comment = request.getParameter("comment");
		
		
		//LOVELY try using SWITCH statement. 
		if (action.equals("hcomment")){
			if (comment.equals("")){
				
			} else{
				newsfeedService.saveComment(Integer.parseInt(postId), sessionUserId, comment);
			}			
		}
		else if (action.equals("hupdateComment")){
			//instead of using if(comment.equals("")) use if(!comment.equals(""))
			if (comment.equals("")){
				
			} else{
				newsfeedService.updateComment(Integer.parseInt(commentId), sessionUserId, comment);
			}			
		}
		else if (action.equals("hupdatePost")){
			if (post.equals("")){
				
			} else{
				newsfeedService.updatePost(Integer.parseInt(postId), sessionUserId, post);
			}			
		}
		else if (action.equals("hlikePost")){
			newsfeedService.saveLikePost(Integer.parseInt(postId), sessionUserId);
		}
		else if (action.equals("hlikeComment")){
			newsfeedService.saveLikeComment(Integer.parseInt(commentId), sessionUserId);
		}
		else if (action.equals("hdeletePost")){
			dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			int confirm = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to delete post?");
			switch(confirm){
				case 0:
					newsfeedService.deletePost(Integer.parseInt(postId), sessionUserId);
					break;
			}
		}
		else if (action.equals("hdeleteComment")){
			dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			int confirm = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to delete comment?");
			switch(confirm){
				case 0:
					newsfeedService.deleteComment(Integer.parseInt(commentId), sessionUserId);
					break;
			}
		}
		else{
			if (post.equals("")){
				
			} else{
				newsfeedService.savePost(post, sessionUserId);
			}
		}
		response.sendRedirect("Newsfeed");
	}

}
