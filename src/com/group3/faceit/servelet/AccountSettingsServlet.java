package com.group3.faceit.servelet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group3.faceit.model.login.LoginModel;
import com.group3.faceit.model.registration.RegistrationModel;
import com.group3.faceit.services.*;
import com.group3.faceit.services.user.UserServices;
import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;
/**
 * Servlet implementation class AccountSettings
 */
@WebServlet("/AccountSettings")
public class AccountSettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		UserServices userServ = new UserServices();
		LoginModel loginModel = userServ.getUserByUserId(1);
		RegistrationModel regModel = userServ.getUserProfileByUserId(1);
		
		req.setAttribute("Title", "Account Settings");
		req.setAttribute("firstname", regModel.getFirstname());
		req.setAttribute("middlename", regModel.getMiddlename());
		req.setAttribute("lastname", regModel.getLastname());
		req.setAttribute("address", regModel.getAddress());
		req.setAttribute("mobile", regModel.getMobile());
		
		req.setAttribute("username", loginModel.getEmail());
		req.setAttribute("password", loginModel.getPassword());
		
		req.getRequestDispatcher("/AccountSettings.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
