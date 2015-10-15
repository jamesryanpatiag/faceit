package com.group3.faceit.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group3.faceit.model.connections.ConnectionsModel;

public class ConnectionsDAO {
	public static PreparedStatement pst;
	public static CallableStatement cst;
	public static ResultSet rs;
	public static String query = "";
	
	public List<ConnectionsModel> getFriends(int sessionUserId, Connection conn){
		List<ConnectionsModel> connect = new ArrayList<ConnectionsModel>();
		
		query = "{CALL getConnections(?)}"; //SHOULD HAVE PARAMETER SESSION.USERID
		
		try {
			cst = conn.prepareCall(query);
			cst.setInt(1, sessionUserId);
			rs = cst.executeQuery();
			while (rs.next()){
				ConnectionsModel modelconn = new ConnectionsModel();
				modelconn.setAddress(rs.getString("address"));
				modelconn.setFullname(rs.getString("fullname"));
				modelconn.setId(rs.getInt("id"));
				connect.add(modelconn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	
	
	public List<ConnectionsModel> getAllUsers(String search, Connection conn){
		List<ConnectionsModel> connect = new ArrayList<ConnectionsModel>();
		
		query = "{CALL getAllUsers(?)}"; //SHOULD HAVE PARAMETER SESSION.USERID
		
		try {
			cst = conn.prepareCall(query);
			cst.setString(1, search);
			rs = cst.executeQuery();
			while (rs.next()){
				ConnectionsModel modelconn = new ConnectionsModel();
				modelconn.setAddress(rs.getString("address"));
				modelconn.setFullname(rs.getString("fullname"));
				modelconn.setId(rs.getInt("id"));
				connect.add(modelconn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}

}
