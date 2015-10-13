package com.group3.faceit.services.validations;

import com.group3.faceit.model.login.*;
import com.group3.faceit.services.user.*;

public class LoginValidations {
	
	public static boolean failedValidation;
	
	public static LoginErrModel validateAccess(LoginModel access)
	{
		failedValidation = false;
		
		LoginErrModel err = new LoginErrModel();
		UserServices serv = new UserServices();
		
		try{			
			if(access.getEmail().length() == 0 && access.getPassword().length() == 0){
				err.setUsernameErr("Username and Password is required.");
				failedValidation = true;
			}else if(access.getEmail() != null){
				if(access.getEmail().trim().isEmpty()){
					err.setUsernameErr("Email is required.");
					failedValidation = true;
				}else if(!serv.checkUserExist(access.getEmail())){
					err.setUsernameErr("Email is invalid.");
					failedValidation = true;
				}else if(access.getPassword().length() == 0){
					err.setPasswordErr("Password is required.");
					failedValidation = true;
				}else if(!serv.doAuthentication(access.getEmail(), access.getPassword())){
				err.setPasswordErr("Password is invalid.");
				failedValidation = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return err;
		
	}

}
