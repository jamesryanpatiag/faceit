package com.group3.faceit.servelet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group3.faceit.model.user.*;
import com.group3.faceit.services.*;
import com.group3.faceit.services.user.UserServices;
import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;
/**
 * Servlet implementation class AccountSettings
 */
@WebServlet("/AccountSettings")
public class AccountSettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int sessionUserId = 0;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountSettingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("userid").toString() != "" && session.getAttribute("userid") != null){
			sessionUserId = Integer.parseInt(session.getAttribute("userid").toString());
			UserServices userServ = new UserServices();
			UserModel loginModel = userServ.getUserByUserId(sessionUserId);
			UserModel regModel = userServ.getUserProfileByUserId(sessionUserId);
			
			req.setAttribute("Title", "Account Settings");
			req.setAttribute("firstname", regModel.getFirstname());
			req.setAttribute("middlename", regModel.getMiddlename());
			req.setAttribute("lastname", regModel.getLastname());
			req.setAttribute("address", regModel.getAddress());
			req.setAttribute("birthdate", regModel.getBirthdate());
			req.setAttribute("gender", regModel.getGender());
			req.setAttribute("mobile", regModel.getMobile());
			req.setAttribute("username", loginModel.getUsername());
			req.setAttribute("password", loginModel.getPassword());
			req.getRequestDispatcher("/AccountSettings.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("/Home.jsp").forward(req, resp);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
