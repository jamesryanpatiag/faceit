package com.group3.faceit.services.validations;

import java.util.Date;

import com.group3.faceit.model.user.*;
import com.group3.faceit.services.user.*;


public class RegistrationValidations {
	
	public static boolean failedValidation;
	
	public static UserErrModel validadateRegistration(UserModel register)
	{
		failedValidation = false;
		
		UserErrModel err = new UserErrModel();
		UserServices serv = new UserServices();
		
		try{
			
			/**First Name Validations*/
			if(register.getFirstname() != null){
				if(register.getFirstname().trim().isEmpty()){
					err.setFnameerr("First Name is required.");
					failedValidation = true;
				}else if(!register.getFirstname().matches("^[a-zA-Z ]*")){
					err.setFnameerr("Invalid Input");
					failedValidation = true;
				}
			}
			
			/**Middle Name Validations*/
			if(!register.getMiddlename().matches("^[a-zA-Z ]*")){
				err.setMnamerrr("Invalid Input");
				failedValidation = true;
			}
			
			/**Last Name Validations*/
			if(register.getLastname() != null){
				if(register.getLastname().trim().isEmpty()){
					err.setLnameerr("Last Name is required.");
					failedValidation = true;
				}else if(!register.getLastname().matches("^[a-zA-Z ]*")){
					err.setLnameerr("Invalid Input");
					failedValidation = true;
				}
			}
			
			/**Email Validations*/
			if(register.getUsername() != null){
				if(register.getUsername().trim().isEmpty()){
					err.setUnameerr("Email is required.");
					failedValidation = true;
				}else if(!register.getUsername().matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")){
					err.setUnameerr("Email Format is invalid.");
					failedValidation = true;
				}else if(serv.checkUserExist(register.getUsername())){
					err.setUnameerr("Email already exists.");
					failedValidation = true;
				}
			}
		
			/**Password Validations*/
			if(register.getPassword() != null){
				if(register.getPassword().trim().isEmpty()){
					err.setPassworderr("Password is required.");
					failedValidation = true;
				}else if(register.getPassword().length() < 8 || register.getPassword().length() > 20){
					err.setPassworderr("Password must be 8-20 characters ");
					failedValidation = true;
				}else if(!register.getPassword().matches("^.*(?=.*[0-9])(?=.*[!@#$%^&+=]).*$")){
					err.setPassworderr("Password must consists of alphanumeric and special character.");
					failedValidation = true;
				}
			}
			
			/**Birthdate Validations*/
			if(register.getBirthdate() != null){
				if(register.getBirthdate().trim().isEmpty()){
					err.setBdateerr("Birthdate is required.");
					failedValidation = true;		
				}
			}
			
			/**Gender Validations*/
			if(register.getGender() != null){
				if(register.getGender().trim().isEmpty()){
					err.setGendererr("Gender is required.");
					failedValidation = true;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return err;
	}
}
