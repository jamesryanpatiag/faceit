package com.group3.faceit.services.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group3.faceit.common.AbstractDAO;
import com.group3.faceit.dao.ConnectionsDAO;
import com.group3.faceit.model.connections.ConnectionsModel;

public class ConnectionsServices extends AbstractDAO {
	private ConnectionsDAO connDAO = null;

	public ConnectionsServices() {
		connDAO = new ConnectionsDAO();
	}
	
	public List<ConnectionsModel> getFriends(int sessionUserId) {
		List<ConnectionsModel> connect = new ArrayList<ConnectionsModel>();
		try {
			Connection con = getConnection();
			connect = connDAO.getFriends(sessionUserId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	
	public List<ConnectionsModel> getAllUsers(String search) {
		List<ConnectionsModel> connect = new ArrayList<ConnectionsModel>();
		try {
			Connection con = getConnection();
			connect = connDAO.getAllUsers(search, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	
	public int checkIfConnected(int sessionUserId, int userId) {
		int count = 0;
		try {
			Connection con = getConnection();
			count = connDAO.checkIfConnected(sessionUserId, userId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
