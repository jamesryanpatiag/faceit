package com.group3.faceit.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.group3.faceit.model.login.*;
import com.group3.faceit.services.user.*;
import com.group3.faceit.services.validations.*;
import com.mysql.fabric.Response;

@WebServlet({"/Login"})
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		req.setAttribute("Title", "Login");
		req.getRequestDispatcher("/Home.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		LoginModel logData = new LoginModel();
		logData.setEmail(req.getParameter("email"));
		logData.setPassword(req.getParameter("password"));
		
		LoginErrModel err = LoginValidations.validateAccess(logData);
		System.out.println(req.getParameter("email").concat(" " + req.getParameter("password")));
		
		if(LoginValidations.failedValidation){
			System.out.println(err.getUsernameErr());
			System.out.println(err.getPasswordErr());
			req.setAttribute("emailerr", err.getUsernameErr());
			req.setAttribute("passerr", err.getPasswordErr());
			req.getRequestDispatcher("/Home.jsp").forward(req, resp);
		}else{
			UserServices regServ = new UserServices();
			if(regServ.loginAccount(logData))
			{
				resp.sendRedirect("Newsfeed");
			}else{
				resp.sendRedirect("Login");
			}
		}
	}

}