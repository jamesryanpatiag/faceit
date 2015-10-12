package com.group3.faceit.dao;

import java.nio.file.attribute.DosFileAttributes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.group3.faceit.model.login.LoginModel;

public class LoginDAO {
	
	private String strQry = "";
	
	public Boolean loginAccount(LoginModel regData, Connection con) throws SQLException{
		
		Boolean isValid = false;
		
		try{
			strQry = "SELECT password = sha1(?)  FROM users WHERE username= ?";
			PreparedStatement stmt = con.prepareStatement(strQry);
			stmt.setString(1, regData.getPassword());
			stmt.setString(2, regData.getEmail());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				isValid = rs.getBoolean(1);
			}		
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return isValid;
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
}
