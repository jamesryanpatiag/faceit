package com.group3.faceit.servelet;
import com.group3.faceit.services.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.group3.faceit.dao.*;
import com.group3.faceit.model.user.*;
import com.group3.faceit.services.user.*;
import com.group3.faceit.services.validations.*;

@WebServlet({"/Registration"})
public class RegistrationServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		req.getRequestDispatcher("/Home.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		UserModel regData = new UserModel();
		regData.setFirstname(req.getParameter("firstname").toString());
		regData.setMiddlename(req.getParameter("middlename").toString());
		regData.setLastname(req.getParameter("lastname").toString());
		regData.setUsername(req.getParameter("email").toString());
		regData.setPassword(req.getParameter("password").toString());
		regData.setBirthdate(req.getParameter("birthdate"));
		regData.setGender(req.getParameter("gender"));
		
		UserErrModel err = RegistrationValidations.validadateRegistration(regData);
				
		if(RegistrationValidations.failedValidation)
		{
			//RETURN VAL
			req.setAttribute("firstname", req.getParameter("firstname").toString());
			req.setAttribute("middlename", req.getParameter("middlename").toString());
			req.setAttribute("lastname", req.getParameter("lastname").toString());
			req.setAttribute("email", req.getParameter("email").toString());
			req.setAttribute("birthdate", req.getParameter("birthdate").toString());
			req.setAttribute("gender", req.getParameter("gender").toString());
			
			//ERROR MESSAGES
			req.setAttribute("fnameerr", err.getFnameerr());
			req.setAttribute("mnameerr", err.getMnamerrr());
			req.setAttribute("lnameerr", err.getLnameerr());
			req.setAttribute("emailerr", err.getUnameerr());
			req.setAttribute("passerr", err.getPassworderr());
			req.setAttribute("birtherr", err.getBdateerr());
			req.setAttribute("generr", err.getGendererr());
			req.getRequestDispatcher("/Home.jsp").forward(req, resp);
			
		}else{
			UserServices regServ = new UserServices();
			if(regServ.registerAccount(regData))
			{
				req.getRequestDispatcher("/Redirect.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("/Register.jsp").forward(req, resp);
			}
		
		}
		
	}
}
