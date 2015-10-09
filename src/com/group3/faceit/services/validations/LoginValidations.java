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
			if(access.getEmail().length() == 0){
				err.setUsernameErr("Username is required.");
				failedValidation = true;
			}else if(!serv.checkUserExist(access.getEmail())){
				err.setUsernameErr("Invalid Email");
				failedValidation = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return err;
		
	}

}
