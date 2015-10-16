package com.group3.faceit.services.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public Boolean validateAgeByBirthdate(String birthdate){
		Boolean isValid = false;
		try{
			Connection con = getConnection();
			isValid = userDao.validateAgeByBirthdate(changeStringDateFormat(birthdate), con);
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return isValid;
	}
	
	public static String changeStringDateFormat(String strDate){
		String result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat truesdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date convertedCurrentDate;
		
		try {
		   convertedCurrentDate = sdf.parse(strDate);
		   result = truesdf.format(convertedCurrentDate );
		} catch (ParseException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		}
		  
		return result;
	}
	
	public Boolean validateBirthdateIfEqualsOrGreaterToCurrentDate(String birthdate){
		Boolean isValid = false;
		try{
			Connection con = getConnection();
			isValid = userDao.validateBirthdateIfEqualsOrGreaterToCurrentDate(changeStringDateFormat(birthdate), con);
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return isValid;
	}
	
	public Boolean updateUserInformation(UserModel updateData){
		Boolean isValid = false;
		try {
			Connection con = getConnection();
			isValid = userDao.updateUserInformation(updateData, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return isValid;
	}
}
