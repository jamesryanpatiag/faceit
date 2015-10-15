package com.group3.faceit.services.user;

import java.sql.Connection;
import java.sql.SQLException;

import com.group3.faceit.common.AbstractDAO;
import com.group3.faceit.dao.UserDAO;
import com.group3.faceit.model.user.UserModel;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class UserServices extends AbstractDAO {
	
	private UserDAO userDao = null;
	
	public UserServices() {
		userDao = new UserDAO();
	}

	public int loginAccount(UserModel regData){
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

	public Boolean registerAccount(UserModel regData) {
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
	
	public UserModel getUserByUserId(int userid){
		UserModel loginModel = new UserModel();
		try {
			Connection con = getConnection();
			loginModel = userDao.getUserByUserId(userid, con);
			con.close();
		}catch(SQLException e){
			e.printStackTrace();			
		}
		return loginModel;
	}
	
	public UserModel getUserProfileByUserId(int userid){
		UserModel regModel = new UserModel();
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
