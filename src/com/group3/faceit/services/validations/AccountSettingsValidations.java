package com.group3.faceit.services.validations;

import com.group3.faceit.model.user.*;
import com.group3.faceit.services.user.UserServices;

public class AccountSettingsValidations {

	public static boolean failedValidation;
	
	public static UserErrModel validateAccountSettings(UserModel user)
	{
		failedValidation = false;
		
		UserErrModel err = new UserErrModel();
		UserServices serv = new UserServices();
		
		try{
			
			/**First Name Validations*/
			if(user.getFirstname() != null){
				if(user.getFirstname().trim().isEmpty()){
					err.setFnameerr("First Name is required.");
					failedValidation = true;
				}else if(!user.getFirstname().matches("^[a-zA-Z ]*")){
					err.setFnameerr("Invalid Input");
					failedValidation = true;
				}
			}
			
			/**Middle Name Validations*/
			if(!user.getMiddlename().matches("^[a-zA-Z ]*")){
				err.setMnamerrr("Invalid Input");
				failedValidation = true;
			}
			
			/**Last Name Validations*/
			if(user.getLastname() != null){
				if(user.getLastname().trim().isEmpty()){
					err.setLnameerr("Last Name is required.");
					failedValidation = true;
				}else if(!user.getLastname().matches("^[a-zA-Z ]*")){
					err.setLnameerr("Invalid Input");
					failedValidation = true;
				}
			}
			
			/**Email Validations*/
			if(user.getUsername() != null){
				if(user.getUsername().trim().isEmpty()){
					err.setUnameerr("Email is required.");
					failedValidation = true;
				}else if(!user.getUsername().matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")){
					err.setUnameerr("Email Format is invalid.");
					failedValidation = true;
				}else if(serv.checkUserExist(user.getUsername())){
					err.setUnameerr("Email already exists.");
					failedValidation = true;
				}
			}
		
			/**Password Validations*/
			/*if(user.getPassword() != null){
				if(user.getPassword().trim().isEmpty()){
					err.setPassworderr("Password is required.");
					failedValidation = true;
				}else if(user.getPassword().length() < 8 || user.getPassword().length() > 20){
					err.setPassworderr("Password must be 8-20 characters ");
					failedValidation = true;
				}else if(!user.getPassword().matches("^.*(?=.*[0-9])(?=.*[!@#$%^&+=]).*$")){
					err.setPassworderr("Password must consists of alphanumeric and special character.");
					failedValidation = true;
				}
			}*/
			
			/**Birthdate Validations*/
			if(user.getBirthdate() != null){
				if(user.getBirthdate().trim().isEmpty()){
					err.setBdateerr("Birthdate is required.");
					failedValidation = true;
				}else if(serv.validateBirthdateIfEqualsOrGreaterToCurrentDate(user.getBirthdate())){
					err.setBdateerr("Birthdate is should not be equal or greater than the current date.");
					failedValidation = true;
				}else if(!serv.validateAgeByBirthdate(user.getBirthdate())){
					err.setBdateerr("User should not be less than 18 years old.");
					failedValidation = true;
				}
			}
			
			/**Gender Validations*/
			if(user.getGender() != null){
				if(user.getGender().trim().isEmpty()){
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
