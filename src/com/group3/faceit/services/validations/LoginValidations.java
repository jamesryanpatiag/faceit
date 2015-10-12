package com.group3.faceit.services.validations;

import com.group3.faceit.model.login.LoginErrModel;
import com.group3.faceit.model.login.LoginModel;
import com.group3.faceit.services.login.LoginServices;

public class LoginValidations {
	
	public static boolean failedValidation;
	
	public static LoginErrModel validateAccess(LoginModel access)
	{
		failedValidation = false;
		
		LoginErrModel err = new LoginErrModel();
		LoginServices serv = new LoginServices();
		
		try{
			if(access.getEmail().length() == 0 && access.getPassword().length() == 0){
				err.setUsernameErr("Username and Password is required.");
				failedValidation = true;
			}else if(access.getEmail().length() == 0){
				err.setUsernameErr("Username is required.");
				failedValidation = true;
			}else if(!serv.checkUserExist(access.getEmail())){
				err.setUsernameErr("Invalid Email.");
				failedValidation = true;
			}else if(access.getPassword().length() == 0){
				err.setPasswordErr("Password is required.");
				failedValidation = true;
			}else if(!serv.doAuthentication(access.getEmail(), access.getPassword())){
				err.setPasswordErr("Password is required.");
				failedValidation = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return err;
		
	}

}
