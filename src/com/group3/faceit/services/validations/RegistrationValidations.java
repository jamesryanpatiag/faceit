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
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return err;
	}
}
