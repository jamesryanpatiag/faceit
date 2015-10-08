package com.group3.faceit.validations.registration;

public class RegistrationValidations {
	
	public Boolean validateRegistration(String fname, String lname, String username, String password, String confirmpassword, String birthdate){
		
		boolean reg = false;
		
		if(fname == null || fname.length() == 0)
		{
			
			reg = true;
			System.out.println("First Name is Required");
		}
		
		return reg;
		
	}

}
