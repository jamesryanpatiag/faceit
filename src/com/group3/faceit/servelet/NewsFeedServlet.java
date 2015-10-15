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

/**
 * Servlet implementation class NewsFeed
 */
@WebServlet("/Newsfeed")
public class NewsFeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final JDialog dialog = new JDialog();
	RequestDispatcher rd = null;
	NewsfeedServices newsfeedservice;	
	public int sessionUserId; //SESSION USER ID
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsFeedServlet() {
        super();
        newsfeedservice = new NewsfeedServices();
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
			request.setAttribute("posts", newsfeedservice.getPosts(sessionUserId));	//1 - //MUST BE SESSION.USERID
			request.setAttribute("postdao", newsfeedservice);
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
		String postid = request.getParameter("postId");
		String commentid = request.getParameter("commentId");
		String post = request.getParameter("post");
		String comment = request.getParameter("comment");
		
		
		//LOVELY try using SWITCH statement. 
		if (action.equals("hcomment")){
			if (comment.equals("")){
				
			} else{
				newsfeedservice.saveComment(Integer.parseInt(postid), sessionUserId, comment);
			}			
		}
		else if (action.equals("hupdateComment")){
			//instead of using if(comment.equals("")) use if(!comment.equals(""))
			if (comment.equals("")){
				
			} else{
				newsfeedservice.updateComment(Integer.parseInt(commentid), sessionUserId, comment);
			}			
		}
		else if (action.equals("hupdatePost")){
			if (post.equals("")){
				
			} else{
				newsfeedservice.updatePost(Integer.parseInt(postid), sessionUserId, post);
			}			
		}
		else if (action.equals("hlikePost")){
			newsfeedservice.saveLikePost(Integer.parseInt(postid), sessionUserId);
		}
		else if (action.equals("hlikeComment")){
			newsfeedservice.saveLikeComment(Integer.parseInt(commentid), sessionUserId);
		}
		else if (action.equals("hdeletePost")){
			dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			int confirm = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to delete post?");
			switch(confirm){
				case 0:
					newsfeedservice.deletePost(Integer.parseInt(postid), sessionUserId);
					break;
			}
		}
		else if (action.equals("hdeleteComment")){
			dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			int confirm = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to delete comment?");
			switch(confirm){
				case 0:
					newsfeedservice.deleteComment(Integer.parseInt(commentid), sessionUserId);
					break;
			}
		}
		else{
			if (post.equals("")){
				
			} else{
				newsfeedservice.savePost(post, sessionUserId);
			}
		}
		response.sendRedirect("Newsfeed");
	}

}
