package com.group3.faceit.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group3.faceit.model.login.LoginErrModel;
import com.group3.faceit.model.login.LoginModel;
import com.group3.faceit.services.login.LoginServices;
import com.group3.faceit.services.validations.LoginValidations;

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
		LoginModel regData = new LoginModel();
		regData.setEmail(req.getParameter("email"));
		regData.setPassword(req.getParameter("password"));
		
		LoginErrModel err = LoginValidations.validateAccess(regData);
		System.out.println(req.getParameter("email").concat(" " + req.getParameter("password")));
		if(LoginValidations.failedValidation){
			System.out.println(err.getUsernameErr());
			req.setAttribute("emailerr", err.getUsernameErr());
			req.getRequestDispatcher("/Home.jsp").forward(req, resp);
		}else{
			LoginServices regServ = new LoginServices();
			if(regServ.loginAccount(regData))
			{
				req.setAttribute("Title", "News Feed");
				req.getRequestDispatcher("/NewsFeed.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("/Home.jsp").forward(req, resp);
			}
		}
	}

}
