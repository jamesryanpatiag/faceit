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
import com.group3.faceit.model.registration.RegistrationModel;
import com.group3.faceit.services.registration.RegistrationServices;

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
		String fname = req.getParameter("firstname");
		String mname = req.getParameter("middlename");
		String lname = req.getParameter("lastname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirmpassword = req.getParameter("confirmpassword");
		String month = req.getParameter("month");
		String day = req.getParameter("day");
		String year = req.getParameter("year");
		String gender = req.getParameter("gender");
		
		RegistrationModel regData = new RegistrationModel();
		regData.setFirstname(fname);
		regData.setMiddlename(mname);
		regData.setLastname(lname);
		regData.setUsername(email);
		regData.setPassword(password);
		regData.setBirthdate(month + "/" + day + "/" + year);
		regData.setGender(gender);
		
		RegistrationServices regServ = new RegistrationServices();
		regServ.registerAccount(regData);
		req.getRequestDispatcher("/Home.jsp").forward(req, resp);
		
		
		
	}
}
