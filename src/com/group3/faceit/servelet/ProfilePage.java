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

import com.group3.faceit.services.newsfeed.NewsfeedServices;

/**
 * Servlet implementation class ProfilePage
 */
@WebServlet("/ProfilePage")
public class ProfilePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final JDialog dialog = new JDialog();
	RequestDispatcher rd = null;
	NewsfeedServices newsfeedservice;	
	public int sessionUserId = 2; //SESSION USER ID
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilePage() {
        super();
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
			request.setAttribute("Title", "Profile");
//			request.setAttribute("user", user);
			//request.setAttribute("posts", newsfeedservice.getPosts(sessionUserId));	//1 - //MUST BE SESSION.USERID
			//request.setAttribute("postdao", newsfeedservice);
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
		doGet(request, response);
	}

}
