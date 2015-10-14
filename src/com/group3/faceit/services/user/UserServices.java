package com.group3.faceit.services.user;

import java.sql.Connection;
import java.sql.SQLException;

import com.group3.faceit.common.AbstractDAO;
import com.group3.faceit.dao.UserDAO;
import com.group3.faceit.model.login.LoginModel;
import com.group3.faceit.model.registration.RegistrationModel;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class UserServices extends AbstractDAO {
	
	private UserDAO userDao = null;
	
	public UserServices() {
		userDao = new UserDAO();
	}

	public int loginAccount(LoginModel regData){
		int userid = 0;
		try {
			Connection con = getConnection();
			userid = userDao.loginAccount(regData,con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return userid;
	}
	
	public Boolean checkUserExist(String email){
		Boolean isValid = false;
		try {
			Connection con = getConnection();
			isValid = userDao.checkUserExist(email,con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return isValid;
	}

	public Boolean doAuthentication(String email, String password){
		Boolean isValid = false;
		try {
			Connection con = getConnection();
			isValid = userDao.doAuthentication(email, password, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return isValid;
	}

	public Boolean registerAccount(RegistrationModel regData) {
		Boolean isValid = false;
		try {
			Connection con = getConnection();
			isValid = userDao.registerAccount(regData, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return isValid;
	}
	
	public Boolean doAgeValidation(String date){
		Boolean isValid = false;
		try {
			Connection con = getConnection();
			isValid = userDao.checkUserExist(date,con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return isValid;
	}
	
	public LoginModel getUserByUserId(int userid){
		LoginModel loginModel = new LoginModel();
		try {
			Connection con = getConnection();
			loginModel = userDao.getUserByUserId(userid, con);
			con.close();
		}catch(SQLException e){
			e.printStackTrace();			
		}
		return loginModel;
	}
	
	public RegistrationModel getUserProfileByUserId(int userid){
		RegistrationModel regModel = new RegistrationModel();
		try{
			Connection con = getConnection();
			regModel = userDao.getUserProfileByUserId(userid, con);
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return regModel;
	}
}
