package com.group3.faceit.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.group3.faceit.model.login.*;
import com.group3.faceit.services.user.*;
import com.group3.faceit.services.validations.*;
import com.mysql.fabric.Response;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		System.out.println("hello");
		HttpSession session = req.getSession();
		if(session.getAttribute("userid").toString() != "" && session.getAttribute("userid") != null){
			resp.sendRedirect("Newsfeed");
		}else{
			req.setAttribute("Title", "Login");
			resp.sendRedirect("Login");
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		LoginModel logData = new LoginModel();
		logData.setEmail(req.getParameter("lgnEmail"));
		logData.setPassword(req.getParameter("lgnPassword"));
		
		LoginErrModel err = LoginValidations.validateAccess(logData);
		System.out.println(req.getParameter("lgnEmail").concat(" " + req.getParameter("lgnPassword")));
		
		if(LoginValidations.failedValidation){
			System.out.println(err.getUsernameErr());
			System.out.println(err.getPasswordErr());
			req.setAttribute("lgnEmailerr", err.getUsernameErr());
			req.setAttribute("lgnPasserr", err.getPasswordErr());
			req.getRequestDispatcher("/Home.jsp").forward(req, resp);
		}else{
			UserServices regServ = new UserServices();
			if(regServ.loginAccount(logData) != 0)
			{
				HttpSession session = req.getSession(true);
				session.setAttribute("userid", regServ.loginAccount(logData));
				resp.sendRedirect("Newsfeed");
				
			}else{
				req.getRequestDispatcher("/Home.jsp").forward(req, resp);
			}
		}
	}

}
