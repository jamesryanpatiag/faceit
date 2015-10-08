package com.group3.faceit.servelet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group3.faceit.model.login.LoginErrModel;
import com.group3.faceit.model.login.LoginModel;
import com.group3.faceit.services.login.LoginServices;
import com.group3.faceit.validations.login.LoginValidations;

@WebServlet({"/Login"})
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		req.getRequestDispatcher("/Home.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		LoginModel regData = new LoginModel();
		regData.setEmail(email);
		regData.setPassword(password);
		
		LoginErrModel err = LoginValidations.validateAccess(regData);
		
		System.out.println(LoginValidations.failedValidation);
		System.out.println(err.getUsernameErr());
		if(LoginValidations.failedValidation){
			req.setAttribute("emailerr", err.getUsernameErr());
			req.getRequestDispatcher("/Home.jsp").forward(req, resp);
		}else{
			LoginServices regServ = new LoginServices();
			if(regServ.loginAccount(regData)){
				req.getRequestDispatcher("/Redirect.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("/Home.jsp").forward(req, resp);
			}
		}
	}

}
