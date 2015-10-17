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
				
			/**Current Password Validations*/
			if(!user.getPassword().equals("")){
				if(serv.validateCurrentPasswordByUsername(user)){
					if(!user.getNewpassword().equals("") && !user.getConfirmpassword().equals("")){
						if(user.getNewpassword().equals(user.getConfirmpassword())){
							if(user.getNewpassword().length() < 8 || user.getNewpassword().length() > 20){
								err.setNewpasserr("Password must be 8-20 characters ");
								failedValidation = true;
							}else if(!user.getNewpassword().matches("^.*(?=.*[0-9])(?=.*[!@#$%^&+=]).*$")){
								err.setNewpasserr("Password must consists of alphanumeric and special character.");
								failedValidation = true;
							}
						}else if(user.getNewpassword() != user.getConfirmpassword()){
							err.setNewpasserr("New and Confirm Password does not match.");
							failedValidation = true;
						}
					}else{
						err.setNewpasserr("New and Confirm should not be blank");
						failedValidation = true;
					}
				}else{
					err.setPassworderr("Invalid Password.");
					failedValidation = true;
				}
			}
		
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
