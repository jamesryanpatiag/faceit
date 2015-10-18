package com.group3.faceit.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group3.faceit.services.connection.ConnectionsServices;

/**
 * Servlet implementation class FriendsServlet
 */
@WebServlet("/Friends")
public class FriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionsServices connService;
	public int sessionUserId;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendsServlet() {
        super();
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
			request.setAttribute("Title", "Friends");
			request.setAttribute("connectiondao", connService);
			request.setAttribute("friends", connService.getFriends(sessionUserId));
			request.setAttribute("pendingfriends", connService.getPendingConnections(sessionUserId));
			request.getRequestDispatcher("/Friends.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/Home.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getAttribute("hdnOperation"));
	}

}
