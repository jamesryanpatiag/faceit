package com.group3.faceit.services.validations;

import com.group3.faceit.model.registration.*;
import com.group3.faceit.services.registration.*;


public class RegistrationValidations {
	
	public static boolean failedValidation;
	
	public static RegistrationErrModel validadateRegistration(RegistrationModel register)
	{
		failedValidation = false;
		
		RegistrationErrModel err = new RegistrationErrModel();
		RegistrationServices serv = new RegistrationServices();
		
		try{
			
			/**First Name Validations*/
			if(register.getFirstname().length() == 0){
				err.setFnameerror("First Name is required.");
				failedValidation = true;
			}
			
			/**Middle Name Validations*/
			
			/**Last Name Validations*/
			if(register.getLastname().length() == 0){
				err.setLnameerror("Last Name is required.");
				failedValidation = true;
			}
			
			/**Email Validations*/
			if(register.getUsername().length() == 0){
				err.setUsernameerror("Email is required.");
				failedValidation = true;
			}
			
			/**Password Validations*/
			if(register.getPassword().length() == 0){
				err.setPassworderror("Password is required.");
				failedValidation = true;
			}
			
			/**Birthdate Validations*/
			if(register.getBirthdate().length() == 0){
				err.setBirthdateerror("Birthdate is required.");
				failedValidation = true;
			}
			
			/**Gender Validations*/
			if(register.getGender().length() == 0){
				err.setGendererror("Gender is required.");
				failedValidation = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return err;
	}
}
