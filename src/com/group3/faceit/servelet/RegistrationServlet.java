package com.group3.faceit.servelet;
import com.group3.faceit.services.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group3.faceit.dao.RegistrationDAO;
import com.group3.faceit.model.registration.RegistrationErrModel;
import com.group3.faceit.model.registration.RegistrationModel;
import com.group3.faceit.services.registration.RegistrationServices;
import com.group3.faceit.services.validations.RegistrationValidations;

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
		RegistrationModel regData = new RegistrationModel();
		regData.setFirstname(req.getParameter("firstname").toString());
		regData.setMiddlename(req.getParameter("middlename").toString());
		regData.setLastname(req.getParameter("lastname").toString());
		regData.setUsername(req.getParameter("email").toString());
		regData.setPassword(req.getParameter("password").toString());
		regData.setBirthdate(req.getParameter("month") + "/" + req.getParameter("day") + "/" + req.getParameter("year"));
		regData.setGender(req.getParameter("gender"));
		
		RegistrationErrModel err = RegistrationValidations.validadateRegistration(regData);
				
		if(RegistrationValidations.failedValidation)
		{
			req.setAttribute("fnameerr", err.getFnameerror());
			req.setAttribute("mnameerr", err.getMnameerror());
			req.setAttribute("lnameerr", err.getLnameerror());
			req.setAttribute("emailerr", err.getUsernameerror());
			req.setAttribute("passerr", err.getPassworderror());
			req.setAttribute("birtherr", err.getBirthdateerror());
			req.setAttribute("generr", err.getGendererror());
		}else{
			RegistrationServices regServ = new RegistrationServices();
			if(regServ.registerAccount(regData))
			{
				resp.sendRedirect("Redirect");
			}else{
				resp.sendRedirect("Registration");
			}
		
		}
		
	}
}
