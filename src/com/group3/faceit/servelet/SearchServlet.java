package com.group3.faceit.servelet;
import com.group3.faceit.services.user.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group3.faceit.common.StringRefractor;
import com.group3.faceit.common.StringRefractor.*;

import com.google.gson.*;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserServices userServ = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        userServ = new UserServices();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringRefractor sr = new StringRefractor();
	    resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    resp.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    resp.getWriter().write(sr.convertUserModelToJSON(userServ.getAllUsers()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
