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
import com.group3.faceit.services.validations.*;
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
			req.setAttribute("birthdate", regModel.getBirthdate());
			req.setAttribute("gender", regModel.getGender());
			req.setAttribute("address", regModel.getAddress());
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
		
		UserModel upData = new UserModel();
		upData.setFirstname(req.getParameter("txtFirstname").toString());
		upData.setMiddlename(req.getParameter("txtMiddlename").toString());
		upData.setLastname(req.getParameter("txtLastname").toString());
		upData.setBirthdate(req.getParameter("txtBirthdate").toString());
		upData.setAddress(req.getParameter("txtAddress").toString());
		upData.setGender(req.getParameter("txtGender").toString());
		upData.setMobile(req.getParameter("txtMobile").toString());
		upData.setUsername(req.getParameter("txtEmail").toString());
		upData.setPassword(req.getParameter("txtCurrentPassword").toString());
		upData.setNewpassword(req.getParameter("txtNewPassword").toString());
		upData.setConfirmpassword(req.getParameter("txtConfirmPassword").toString());
		
		
		UserErrModel err = AccountSettingsValidations.validateAccountSettings(upData);
		
		req.setAttribute("Title", "Account Settings");
		req.setAttribute("firstname", upData.getFirstname());
		req.setAttribute("middlename", upData.getMiddlename());
		req.setAttribute("lastname", upData.getLastname());
		req.setAttribute("birthdate", upData.getBirthdate());
		req.setAttribute("gender", upData.getGender());
		req.setAttribute("address", upData.getAddress());
		req.setAttribute("mobile", upData.getMobile());
		req.setAttribute("username", upData.getUsername());
		req.setAttribute("password", upData.getPassword());
		req.setAttribute("newpassword", upData.getNewpassword());
		req.setAttribute("confirmpassword", upData.getConfirmpassword());
		
		if(AccountSettingsValidations.failedValidation)
		{
			req.setAttribute("fnameerr", err.getFnameerr());
			req.setAttribute("mnameerr", err.getMnamerrr());
			req.setAttribute("lnameerr", err.getLnameerr());
			req.setAttribute("birthderr", err.getBdateerr());
			req.setAttribute("adderr", err.getAddresserr());
			req.setAttribute("generr", err.getGendererr());
			req.setAttribute("moberr", err.getMobileerr());
			req.setAttribute("passerr", err.getPassworderr());
			req.setAttribute("newpasserr", err.getNewpasserr());
			
			req.getRequestDispatcher("/AccountSettings.jsp").forward(req, resp);
			
		}else{	
			UserServices regServ = new UserServices();
			HttpSession session = req.getSession();
			if(session.getAttribute("userid").toString() != "" && session.getAttribute("userid") != null){
				sessionUserId = Integer.parseInt(session.getAttribute("userid").toString());
				session.setAttribute("userfullname", regServ.getUserProfileByUserId(sessionUserId).getFirstname() + " " + regServ.getUserProfileByUserId(sessionUserId).getLastname());
			}
			if(regServ.updateUserInformation(upData, sessionUserId))
			{
				if(!upData.getPassword().equals("")){
					 regServ.updatePasswordByUserId(upData, sessionUserId);
				}
				req.setAttribute("isSuccess", true);
				req.getRequestDispatcher("/AccountSettings.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("/NewsFeed.jsp").forward(req, resp);
			}
		}
	}

}
