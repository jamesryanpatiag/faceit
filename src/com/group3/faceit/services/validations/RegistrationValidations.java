package com.group3.faceit.services.validations;

import java.util.Date;

import com.group3.faceit.model.registration.*;
import com.group3.faceit.services.user.*;


public class RegistrationValidations {
	
	public static boolean failedValidation;
	
	public static RegistrationErrModel validadateRegistration(RegistrationModel register)
	{
		failedValidation = false;

		RegistrationErrModel err = new RegistrationErrModel();
		UserServices serv = new UserServices();
		
		try{
			
			/**First Name Validations*/
			if(register.getFirstname() != null){
				if(register.getFirstname().trim().isEmpty()){
					err.setFnameerror("First Name is required.");
					failedValidation = true;
				}
			}
			
			/**Middle Name Validations*/
			
			/**Last Name Validations*/
			if(register.getLastname() != null){
				if(register.getLastname().trim().isEmpty()){
					err.setLnameerror("Last Name is required.");
					failedValidation = true;
				}

			}
			
			/**Email Validations*/
			if(register.getUsername() != null){
				if(register.getUsername().trim().isEmpty()){
					err.setUsernameerror("Email is required.");
					failedValidation = true;
				}else if(!register.getUsername().matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")){
					err.setUsernameerror("Email Format is invalid.");
					failedValidation = true;
				}else if(serv.checkUserExist(register.getUsername())){
					err.setUsernameerror("Email already exists.");
					failedValidation = true;
				}
			}
		
			/**Password Validations*/
			if(register.getPassword() != null){
				if(register.getPassword().trim().isEmpty()){
					err.setPassworderror("Password is required.");
					failedValidation = true;
				}else if(register.getPassword().length() < 8 || register.getPassword().length() > 20){
					err.setPassworderror("Password must be 8-20 characters ");
					failedValidation = true;
				}else if(!register.getPassword().matches("[^a-zA-Z0-9 ]*") && !register.getPassword().matches("\\d")){
					err.setPassworderror("Password must consists of alphanumberic and special character");
					failedValidation = true;
				}
			}
			
			/**Birthdate Validations*/
			if(register.getBirthdate() != null){
				if(register.getBirthdate().trim().isEmpty()){
					err.setBirthdateerror("Birthdate is required.");
					failedValidation = true;		
				}
			}
			
			/**Gender Validations*/
			if(register.getGender() != null){
				if(register.getGender().trim().isEmpty()){
					err.setGendererror("Gender is required.");
					failedValidation = true;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return err;
	}
}
