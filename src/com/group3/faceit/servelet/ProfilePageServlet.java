package com.group3.faceit.servelet;

import java.awt.Dialog;
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

import com.group3.faceit.services.profile.ProfilePageServices;
import com.group3.faceit.services.connection.ConnectionsServices;
import com.group3.faceit.services.newsfeed.NewsfeedServices;

/**
 * Servlet implementation class ProfilePage
 */
@WebServlet("/Profile")
public class ProfilePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final JDialog dialog = new JDialog();
	ProfilePageServices profilePageService;
	ConnectionsServices connService;
	public int sessionUserId; //SESSION USER ID
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilePageServlet() {
        super();
        profilePageService = new ProfilePageServices();
        connService = new ConnectionsServices();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String profileid = request.getParameter("profile");
		
		if(session.getAttribute("userid").toString() != "" && session.getAttribute("userid") != null)
		{
			sessionUserId = Integer.parseInt(session.getAttribute("userid").toString());
			request.setAttribute("Title", "Profile");
			request.setAttribute("posts", profilePageService.getPosts(Integer.parseInt(profileid)));
			request.setAttribute("profile", profilePageService.getUser(Integer.parseInt(profileid)));
			request.setAttribute("profileid", profileid);
			request.setAttribute("sessionuserid", session.getAttribute("userid"));
			request.setAttribute("postdao", profilePageService);
			request.setAttribute("connectiondao", connService);
			request.getRequestDispatcher("/Profiles.jsp").forward(request, response);
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
		String profileid = request.getParameter("profile");
		
		String action = request.getParameter("hidden");
		String postid = request.getParameter("postId");
		String commentid = request.getParameter("commentId");
		String post = request.getParameter("post");
		String comment = request.getParameter("comment");
		
		
		//LOVELY try using SWITCH statement. 
		if (action.equals("hcomment")){
			if (comment.equals("")){
				
			} else{
				profilePageService.saveComment(Integer.parseInt(postid), sessionUserId, comment);
			}			
		}
		else if (action.equals("hupdateComment")){
			//instead of using if(comment.equals("")) use if(!comment.equals(""))
			if (comment.equals("")){
				
			} else{
				profilePageService.updateComment(Integer.parseInt(commentid), sessionUserId, comment);
			}			
		}
		else if (action.equals("hupdatePost")){
			if (post.equals("")){
				
			} else{
				profilePageService.updatePost(Integer.parseInt(postid), sessionUserId, post);
			}			
		}
		else if (action.equals("hlikePost")){
			profilePageService.saveLikePost(Integer.parseInt(postid), sessionUserId);
		}
		else if (action.equals("hlikeComment")){
			profilePageService.saveLikeComment(Integer.parseInt(commentid), sessionUserId);
		}
		else if (action.equals("hdeletePost")){
			dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			int confirm = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to delete post?");
			switch(confirm){
				case 0:
					profilePageService.deletePost(Integer.parseInt(postid), sessionUserId);
					break;
			}
		}
		else if (action.equals("hdeleteComment")){
			dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			int confirm = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to delete comment?");
			switch(confirm){
				case 0:
					profilePageService.deleteComment(Integer.parseInt(commentid), sessionUserId);
					break;
			}
		}
		else{
			if (post.equals("")){
				
			} else{
				profilePageService.savePost(post, sessionUserId);
			}
		}
		response.sendRedirect("Profile?profile="+profileid);
	}

}
