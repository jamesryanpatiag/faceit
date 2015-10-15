package com.group3.faceit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.group3.faceit.model.newsfeed.PostModel;
import com.group3.faceit.model.user.UserModel;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class UserDAO {
	
	private String strQry = "";
	
	public Boolean registerAccount(UserModel regData, Connection con) throws SQLException{
		
		Boolean isValid = false;
		try{
			strQry = "INSERT INTO users (username, password) VALUES (?, sha1(?))";
			PreparedStatement stmt = con.prepareStatement(strQry, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, regData.getUsername());
			stmt.setString(2, regData.getPassword());
			stmt.executeUpdate();
			
			ResultSet result = stmt.getGeneratedKeys();
			int userId = 0;
			if (result.next()) {
					userId = result.getInt(1);
			}
			
			strQry = "INSERT INTO users_profile (user_id, firstname, middlename, lastname, email, birthdate, gender)"
					+ " VALUES (?, ?, ?, ?, ?, STR_TO_DATE(?, '%m/%d/%Y'), ?)";
			PreparedStatement stmt2 = con.prepareStatement(strQry);
			stmt2.setInt(1, userId);
			stmt2.setString(2, regData.getFirstname());
			stmt2.setString(3, regData.getMiddlename());
			stmt2.setString(4, regData.getLastname());
			stmt2.setString(5, regData.getUsername());
			stmt2.setString(6, regData.getBirthdate());
			stmt2.setString(7, regData.getGender());
			stmt2.executeUpdate();
			
			isValid = true;
			
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return isValid;
	}
	
	public int loginAccount(UserModel regData, Connection con) throws SQLException{
		
		int userid = 0;
		try{
			strQry = "SELECT id FROM users WHERE username= ? AND password = sha1(?)";
			PreparedStatement stmt = con.prepareStatement(strQry);
			stmt.setString(1, regData.getUsername());
			stmt.setString(2, regData.getPassword());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				userid = rs.getInt(1);
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return userid;
	}

	public Boolean doAuthentication(String email, String password, Connection con) throws SQLException{
		  Boolean doesMatch = false;
		  
		  try{
		   strQry = "SELECT * FROM users WHERE username = ? AND password = SHA1(?)";
		   PreparedStatement stmt = con.prepareStatement(strQry);
		   stmt.setString(1, email);;
		   stmt.setString(2,  password);
		   
		   ResultSet rs = stmt.executeQuery();
		   
		   if(rs.next())
		   {
		    doesMatch = true;
		   }
		  }catch(SQLException ex){
		   System.out.println(ex.getMessage());
		  }
		  System.out.println("does match? - " + doesMatch);
		  return doesMatch;
	}
	
	public Boolean checkUserExist(String email, Connection con) throws SQLException{
		Boolean doesExist = false;
		
		try{
			strQry = "SELECT * FROM users WHERE username = ?";
			PreparedStatement stmt = con.prepareStatement(strQry);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				doesExist = true;
			}
			
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		System.out.println("does exist? - " + doesExist);
		return doesExist;
	}
	
	public UserModel getUserByUserId(int userid, Connection con){
		UserModel loginModel = new UserModel();
		try{
			strQry = "SELECT username, password FROM users WHERE id = ?";
			
			PreparedStatement stmt = con.prepareStatement(strQry);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				loginModel.setUsername(rs.getString("username"));
				loginModel.setPassword(rs.getString("password"));
			}
			
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return loginModel;
	}
	
	public UserModel getUserProfileByUserId(int userid, Connection con){
		UserModel regModel = new UserModel();
		try{
			strQry = "SELECT firstname, middlename, lastname, birthdate, address, mobile FROM users_profile WHERE user_id = ?";
			
			PreparedStatement stmt = con.prepareStatement(strQry);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				regModel.setFirstname(rs.getString("firstname"));
				regModel.setMiddlename(rs.getString("middlename"));
				regModel.setLastname(rs.getString("lastname"));
				regModel.setBirthdate(rs.getString("birthdate"));
				regModel.setAddress(rs.getString("address"));
				regModel.setMobile(rs.getString("mobile"));
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return regModel;
		
	} 

}
