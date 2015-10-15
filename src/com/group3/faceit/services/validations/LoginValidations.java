package com.group3.faceit.services.validations;

import com.group3.faceit.model.user.*;
import com.group3.faceit.services.user.*;

public class LoginValidations {
	
	public static boolean failedValidation;
	
	public static UserErrModel validateAccess(UserModel access)
	{
		failedValidation = false;
		
		UserErrModel err = new UserErrModel();
		UserServices serv = new UserServices();
		
		try{			
			if(access.getUsername().length() == 0 && access.getPassword().length() == 0){
				err.setUnameerr("Username and Password is required.");
				failedValidation = true;
			}else if(access.getUsername() != null){
				if(access.getUsername().trim().isEmpty()){
					err.setUnameerr("Email is required.");
					failedValidation = true;
				}else if(!serv.checkUserExist(access.getUsername())){
					err.setUnameerr("Email is invalid.");
					failedValidation = true;
				}else if(access.getPassword().length() == 0){
					err.setPassworderr("Password is required.");
					failedValidation = true;
				}else if(!serv.doAuthentication(access.getUsername(), access.getPassword())){
				err.setPassworderr("Password is invalid.");
				failedValidation = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return err;
		
	}

}
