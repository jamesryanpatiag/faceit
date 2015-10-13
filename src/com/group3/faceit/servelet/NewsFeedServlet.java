package com.group3.faceit.servelet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group3.faceit.model.newsfeed.*;
import com.group3.faceit.services.newsfeed.*;

/**
 * Servlet implementation class NewsFeed
 */
@WebServlet("/Newsfeed")
public class NewsFeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd = null;
	NewsfeedServices newsfeedservice;	
       
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
			newsfeedservice.commentPost(Integer.parseInt(postid), comment);
		}
		else if (action.equals("hlike")){
			newsfeedservice.likePost(Integer.parseInt(postid));
		}
		else{
			String post = request.getParameter("post");
			newsfeedservice.savePost(post);
		}
		response.sendRedirect("Newsfeed");
	}

}
