package com.group3.faceit.services.user;

import java.sql.Connection;
import java.sql.SQLException;

import com.group3.faceit.common.AbstractDAO;
import com.group3.faceit.dao.UserDAO;
import com.group3.faceit.model.login.LoginModel;
import com.group3.faceit.model.registration.RegistrationModel;

public class UserServices extends AbstractDAO {
	
	private UserDAO reg = null;
	
	public UserServices() {
		reg = new UserDAO();
	}

	public Boolean loginAccount(LoginModel regData){
		Boolean isValid = false;
		try {
			Connection con = getConnection();
			isValid = reg.loginAccount(regData,con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return isValid;
	}
	
	public Boolean checkUserExist(String email){
		Boolean isValid = false;
		try {
			Connection con = getConnection();
			isValid = reg.checkUserExist(email,con);
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
			isValid = reg.doAuthentication(email, password, con);
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
			isValid = reg.registerAccount(regData, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return isValid;
	}
}
