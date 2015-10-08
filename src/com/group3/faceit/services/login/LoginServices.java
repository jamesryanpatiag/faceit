package com.group3.faceit.services.login;

import java.sql.Connection;
import java.sql.SQLException;

import com.group3.faceit.common.AbstractDAO;
import com.group3.faceit.dao.login.LoginDAO;
import com.group3.faceit.model.login.LoginModel;

public class LoginServices extends AbstractDAO{
	
	private LoginDAO reg = null;
	
	public LoginServices(){
		reg = new LoginDAO();
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

}
