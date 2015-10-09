package com.group3.faceit.services.registration;

import java.sql.Connection;
import java.sql.SQLException;

import com.group3.faceit.common.AbstractDAO;
import com.group3.faceit.dao.*;
import com.group3.faceit.model.registration.RegistrationModel;

public class RegistrationServices extends AbstractDAO {

	private RegistrationDAO reg = null;

	public RegistrationServices() {
		reg = new RegistrationDAO();
	}

	public Boolean registerAccount(RegistrationModel regData) {
		Boolean isValid = false;
		try {
			Connection con = getConnection();
			isValid = reg.registerAccount(regData, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return isValid;
	}

}
