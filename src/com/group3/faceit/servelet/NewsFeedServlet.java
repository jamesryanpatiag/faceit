package com.group3.faceit.servelet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public int sessionUserId = 1; //SESSION USER ID
       
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
		// TODO Auto-generated method stub
		request.setAttribute("Title", "News Feed");
//		request.setAttribute("user", user);
		request.setAttribute("posts", newsfeedservice.getPosts(1));	//1 - //MUST BE SESSION.USERID
		request.setAttribute("postdao", newsfeedservice);
		rd = request.getRequestDispatcher("/NewsFeed.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("hidden");
		String postid = request.getParameter("postId");
		
		if (action.equals("hcomment")){
			String comment = request.getParameter("comment");
			if (comment.equals("")){
				
			} else{
				newsfeedservice.saveComment(Integer.parseInt(postid), sessionUserId, comment);
			}			
		}
		else if (action.equals("hlikePost")){
			newsfeedservice.saveLikePost(Integer.parseInt(postid), sessionUserId);
		}
		else if (action.equals("hlikeComment")){
			String commentid = request.getParameter("commentId");
			newsfeedservice.saveLikeComment(Integer.parseInt(commentid), sessionUserId);
		}
		else if (action.equals("hdeletePost")){
			dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			int confirm = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to delete post?");
			switch(confirm){
				case 0:
					newsfeedservice.deletePost(Integer.parseInt(postid));
					break;
			}
		}
		else{
			String post = request.getParameter("post");
			if (post.equals("")){
				
			} else{
				newsfeedservice.savePost(post, sessionUserId);
			}
		}
		response.sendRedirect("Newsfeed");
	}

}
